package au.auxy.composearc.account.ui.model

import au.auxy.composearc.account.model.Account

internal sealed class AccountListItem(val uid: String) {
    data class AccountItem(val account: Account) :
        AccountListItem("${account.name} ${account.number}")
}

internal sealed class AccountDetailItem(val uid: String) {
    data class SectionItem(val sectionText: String) : AccountDetailItem("SectionItem: $sectionText")

    data class AccountItem(
        val account: Account
    ) : AccountDetailItem(account.run { "$name:$number" })

    data class SimpleItem(val body: String) : AccountDetailItem("SimpleItem: $body")
}
