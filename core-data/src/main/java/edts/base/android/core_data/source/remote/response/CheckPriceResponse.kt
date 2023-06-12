package edts.base.android.core_data.source.remote.response

import com.google.gson.annotations.SerializedName

data class CheckPriceResponse (
    @field:SerializedName("tipe")
    val tipe: String,

    @field:SerializedName("km")
    val km: String?,

    @field:SerializedName("tarif")
    val cost: String?,

    @field:SerializedName("durasi")
    val duration: String?,

    @field:SerializedName("origin")
    val origin: List<String>?,

    @field:SerializedName("destination")
    val destination: List<String>?,
)