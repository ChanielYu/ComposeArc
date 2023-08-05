package au.auxy.composearc.account.model

internal data class AccountDetail(
    val dob: String,
    val buildingNumber: String,
    val streetAddress: String,
    val city: String,
    val state: String,
    val country: String,
    val mobilePhone: String,
    val email: String
)
