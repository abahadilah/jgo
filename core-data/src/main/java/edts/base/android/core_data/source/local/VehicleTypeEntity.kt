package edts.base.android.core_data.source.local

data class VehicleTypeEntity (
    val id: Long,
    val name: String,
    val description: String?,
    val length: Double?,
    val width: Double?,
    val height: Double?,
    val volume: Double?,
    val maxWeight: Double?,
    val price: List<VehiclePriceEntity>?,
)