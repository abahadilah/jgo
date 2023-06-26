package edts.base.android.core_data.source.local

data class CustomerEntity (
    val id: Long,
    val name: String,
    val email: String?,
    val mobile: String?,
    val lat: Double?,
    val lng: Double?,
    val street: String?,
    val street2: String?,
    val city: IdNameEntity?,
    val zip: String?,
    val state: IdNameEntity?,
    val country: IdNameEntity?,
    val type: String?,
    val token: String?
    )