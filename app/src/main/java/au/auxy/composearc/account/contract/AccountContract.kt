package au.auxy.composearc.account.contract

import au.auxy.composearc.account.ui.model.AccountDetailItem
import au.auxy.composearc.account.ui.model.AccountListItem

internal interface AccountContract {
    sealed class AccountListContent {
        object Shimmer : AccountListContent()
        data class ShowAccountList(val accounts: List<AccountListItem>) : AccountListContent()
        object ShowError : AccountListContent()
    }

    data class AccountListViewState(val accountListContent: AccountListContent)

    sealed class AccountDetailContent {
        object Shimmer : AccountDetailContent()

        data class ShowAccountDetail(
            val title: String, val details: List<AccountDetailItem>
        ) : AccountDetailContent()

        object ShowError : AccountDetailContent()
    }

    data class AccountDetailViewState(val accountDetailContent: AccountDetailContent)

    data class AccountEligibilityViewState(
        val title: String, val upStream: String, val downStream: String
    )

    sealed class AccountEligibilityIntent {
        object Refresh : AccountEligibilityIntent()
        object Navigate : AccountEligibilityIntent()
    }
}
