package au.auxy.composearc.account.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
internal data class AccountArg(
    val name: String?, val number: String?, val extra: String?
) : Parcelable {
    fun toAccount() = Account(name!!, number!!, extra!!)

    companion object {
        fun from(account: Account) = with(account) { AccountArg(name, number, extra) }
    }
}
