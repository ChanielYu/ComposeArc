package au.auxy.composearc.account.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import au.auxy.composearc.account.contract.AccountContract.AccountDetailContent.Shimmer
import au.auxy.composearc.account.contract.AccountContract.AccountDetailContent.ShowAccountDetail
import au.auxy.composearc.account.contract.AccountContract.AccountDetailContent.ShowError
import au.auxy.composearc.account.contract.AccountContract.AccountDetailViewState
import au.auxy.composearc.account.model.AccountDetailArg
import au.auxy.composearc.account.repository.AccountRepository
import au.auxy.composearc.account.ui.model.AccountDetailItem.AccountItem
import au.auxy.composearc.account.ui.model.AccountDetailItem.SectionItem
import au.auxy.composearc.account.ui.model.AccountDetailItem.SimpleItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
internal class AccountDetailViewModel @Inject constructor(
    acctDetailArg: AccountDetailArg,
    private val repository: AccountRepository
) : ViewModel() {
    private val _viewState = MutableStateFlow(DEFAULT_VIEW_STATE)
    val viewState = _viewState.asStateFlow()

    init {
        flowOf(acctDetailArg).map { arg ->
            arg.toAccount()
        }.map { account ->
            repository.readAccountDetail(account.name) to account
        }.map { (detail, account) ->
            buildList {
                add(SectionItem("Account"))
                add(AccountItem(account))
                add(SectionItem("Address"))
                addAll(
                    listOf(
                        SimpleItem(detail.buildingNumber),
                        SimpleItem(detail.streetAddress),
                        SimpleItem(detail.city),
                        SimpleItem(detail.state),
                        SimpleItem(detail.country)
                    )
                )
                add(SectionItem("Personal information"))
                addAll(
                    listOf(
                        SimpleItem(detail.dob),
                        SimpleItem(detail.mobilePhone),
                        SimpleItem(detail.email)
                    )
                )
            } to account.name
        }.map { (details, name) ->
            ShowAccountDetail(name, details)
        }.onEach { state ->
            _viewState.update { AccountDetailViewState(state) }
        }.catch { error ->
            error.printStackTrace()
            _viewState.update { AccountDetailViewState(ShowError) }
        }.launchIn(viewModelScope)
    }

    companion object {
        private val DEFAULT_VIEW_STATE = AccountDetailViewState(Shimmer)
    }
}
