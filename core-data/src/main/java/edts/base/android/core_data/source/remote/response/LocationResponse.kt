package edts.base.android.core_data.source.remote.response

import com.google.gson.annotations.SerializedName

data class LocationResponse(

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("address")
    val address: String? = null,

    @field:SerializedName("lon")
    val lng: Double? = null,

    @field:SerializedName("lat")
    val lat: Double? = null
)