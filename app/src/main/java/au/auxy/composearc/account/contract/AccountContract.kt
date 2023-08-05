package au.auxy.composearc.account.contract

import au.auxy.composearc.account.ui.model.AccountListItem

internal interface AccountContract {
    sealed class AccountListContent {
        object Shimmer : AccountListContent()
        data class ShowAccountList(val accounts: List<AccountListItem>) : AccountListContent()
        object ShowError : AccountListContent()
    }

    data class AccountListViewState(val accountListContent: AccountListContent)
}
