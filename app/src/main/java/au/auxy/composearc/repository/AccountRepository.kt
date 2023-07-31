package au.auxy.composearc.repository

import kotlinx.coroutines.delay
import javax.inject.Inject

class AccountRepository @Inject constructor() {
    suspend fun readAccounts() = delay(2000).let {

    }
}