package edts.base.android.core_data.source.remote.response

import com.google.gson.annotations.SerializedName

data class VehiclePriceResponse (
    @field:SerializedName("mulai")
    val min: Double,

    @field:SerializedName("sampai")
    val max: Double,

    @field:SerializedName("tarif")
    val price: Double
)