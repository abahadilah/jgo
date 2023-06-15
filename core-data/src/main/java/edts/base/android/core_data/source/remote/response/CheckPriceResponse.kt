package edts.base.android.core_data.source.remote.response

import com.google.gson.annotations.SerializedName

data class CheckPriceResponse (
    @field:SerializedName("tipe")
    val tipe: String,

    @field:SerializedName("km")
    val km: String?,

    @field:SerializedName("intkm")
    val kmValue: Double?,

    @field:SerializedName("tarif")
    val cost: Double?,

    @field:SerializedName("durasi")
    val duration: String?,

    @field:SerializedName("origin")
    val origin: List<String>?,

    @field:SerializedName("destination")
    val destination: List<String>?,
)