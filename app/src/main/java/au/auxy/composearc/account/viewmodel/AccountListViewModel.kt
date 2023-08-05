package au.auxy.composearc.account.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import au.auxy.composearc.account.contract.AccountContract.AccountListViewState
import au.auxy.composearc.account.repository.AccountRepository
import au.auxy.composearc.account.ui.model.AccountListItem.AccountItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
internal class AccountListViewModel @Inject constructor(
    private val repository: AccountRepository
) : ViewModel() {
    private val _viewState = MutableStateFlow(DEFAULT_VIEW_STATE)
    val viewState = _viewState.asStateFlow()

    init {
        flow { emit(repository.readAccounts()) }.map { accounts ->
            accounts.map { account ->
                AccountItem(account)
            }
        }.map { listItems ->
            AccountListViewState(listItems)
        }.onEach { state ->
            _viewState.update { state }
        }.catch {
            TODO("Not yet implemented")
        }.launchIn(viewModelScope)
    }

    companion object {
        private val DEFAULT_VIEW_STATE = AccountListViewState(emptyList())
    }
}