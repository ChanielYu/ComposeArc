package au.auxy.composearc.account.contract

import au.auxy.composearc.account.ui.model.AccountListItem

internal interface AccountContract {
    data class AccountListViewState(val accounts: List<AccountListItem>)
}
