package au.auxy.composearc.account.repository

import au.auxy.composearc.account.model.Account
import au.auxy.composearc.account.model.AccountDetail
import io.bloco.faker.Faker
import kotlinx.coroutines.delay
import java.text.DateFormat
import javax.inject.Inject

internal class AccountRepository @Inject constructor(
    private val faker: Faker,
    private val dateFormat: DateFormat
) {
    private var listCounter = 0
    private var detailCounter = 0
    suspend fun readAccounts() = delay(DEFAULT_DELAY).let {
        List(ACCOUNT_LIST_SIZE) {
            Account(
                faker.name.name(),
                faker.business.creditCardNumber(),
                listCounter.toString()
            )
        }.apply { listCounter++ }
    }

    suspend fun readAccountDetail(name: String) = delay(DEFAULT_DELAY).let {
        detailCounter++
        AccountDetail(
            dateFormat.format(faker.date.birthday(18, 90)),
            faker.address.buildingNumber(),
            faker.address.streetAddress(),
            faker.address.city(),
            faker.address.state(),
            faker.address.country(),
            faker.phoneNumber.phoneNumber(),
            faker.internet.email(name)
        )
    }

    companion object {
        private const val DEFAULT_DELAY = 1000L
        private const val ACCOUNT_LIST_SIZE = 20
    }
}
