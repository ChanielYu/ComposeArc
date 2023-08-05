package au.auxy.composearc.repository

import io.bloco.faker.Faker
import kotlinx.coroutines.delay
import javax.inject.Inject

class AccountRepository @Inject constructor(private val faker: Faker) {
    private var counter = 0
    suspend fun readAccounts() = delay(DEFAULT_DELAY).let {
        List(ACCOUNT_LIST_SIZE) {
            Account(
                faker.name.name(),
                faker.business.creditCardNumber(),
                counter.toString()
            )
        }.apply { counter++ }
    }

    companion object {
        private const val DEFAULT_DELAY = 1000L
        private const val ACCOUNT_LIST_SIZE = 20
    }
}
