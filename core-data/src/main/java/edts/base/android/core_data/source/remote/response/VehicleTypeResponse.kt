package edts.base.android.core_data.source.remote.response

import com.google.gson.annotations.SerializedName

data class VehicleTypeResponse (
    @field:SerializedName("id")
    val id: Long,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("description")
    val description: String?,

    @field:SerializedName("panjang")
    val length: Double?,

    @field:SerializedName("lebar")
    val width: Double?,

    @field:SerializedName("tinggi")
    val height: Double?,

    @field:SerializedName("volume")
    val volume: Double?,

    @field:SerializedName("max_angkut")
    val maxWeight: Double?,

    @field:SerializedName("det_price")
    val price: List<VehiclePriceResponse>?,
)