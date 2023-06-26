package edts.base.android.core_domain.model

data class CustomerData (
    val id: Long,
    val name: String,
    val email: String?,
    val mobile: String?,
    val lat: Double?,
    val lng: Double?,
    val street: String?,
    val street2: String?,
    val city: IdNameData?,
    val zip: String?,
    val state: IdNameData?,
    val country: IdNameData?,
    val type: String?,
    val token: String?
    )