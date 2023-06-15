package edts.base.android.core_data.source.local

data class CreateOrderAddressEntity (
    val name: String,
    val city: String?,
    val latitude: Double,
    val longitude: Double,
)