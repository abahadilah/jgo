package edts.base.android.core_domain.model

data class ProfileData (
    val id: Long,
    val name: String,
    val latitude: Double?,
    val longitude: Double?,
    val street: String?,
    val street2: String?,
    val city: IdNameData?,
    val zip: String?,
    val state: IdNameData?,
    val country: IdNameData?,
)