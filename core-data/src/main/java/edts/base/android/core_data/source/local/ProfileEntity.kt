package edts.base.android.core_data.source.local

data class ProfileEntity (
    val id: Long,
    val name: String,
    val latitude: Double?,
    val longitude: Double?,
    val street: String?,
    val street2: String?,
    val city: IdNameEntity?,
    val zip: String?,
    val state: IdNameEntity?,
    val country: IdNameEntity?,
)