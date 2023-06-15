package edts.base.android.core_data.source.remote.request

import com.google.gson.annotations.SerializedName

data class CreateOrderDestinationRequest (
    @field:SerializedName("jenis_asal")
    val originType: String,

    @field:SerializedName("koordinat_asal_name")
    val originName: String,

    @field:SerializedName("koordinat_asal_city")
    val originCity: String?,

    @field:SerializedName("koordinat_asal")
    val originLatLng: String,

    @field:SerializedName("jenis_tujuan")
    val destinationType: String,

    @field:SerializedName("koordinat_tujuan_name")
    val destinationName: String,

    @field:SerializedName("koordinat_tujuan_city")
    val destinationCity: String?,

    @field:SerializedName("koordinat_tujuan")
    val destinationLatLng: String,

    @field:SerializedName("jarak")
    val distance: Double,

    @field:SerializedName("description")
    val description: String
)