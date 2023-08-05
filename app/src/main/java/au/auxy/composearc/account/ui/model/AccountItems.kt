package au.auxy.composearc.account.ui.model

import au.auxy.composearc.account.model.Account

internal sealed class AccountListItem(val uid: String) {
    data class AccountItem(val account: Account) : AccountListItem("${account.name} ${account.number}")
}
