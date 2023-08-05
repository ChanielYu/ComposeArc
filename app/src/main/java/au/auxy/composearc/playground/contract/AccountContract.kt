package au.auxy.composearc.playground.contract

import au.auxy.composearc.repository.Account

interface AccountContract {
    data class AccountListViewState(val accounts:List<Account>)
}