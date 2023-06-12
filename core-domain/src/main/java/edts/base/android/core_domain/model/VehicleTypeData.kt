package edts.base.android.core_domain.model

data class VehicleTypeData(
    val id: Long,
    val name: String,
    val description: String?,
    val length: Double?,
    val width: Double?,
    val height: Double?,
    val volume: Double?,
    val maxWeight: Double?,
    val price: List<VehiclePriceData>?,
) {
    override fun toString() =
        if (description?.isNotEmpty() == true && description != "false")
        "$name (${description.trim()})" else name
}
