package edts.base.android.core_data.source.remote.request

import com.google.gson.annotations.SerializedName

data class CheckPriceRequest (
    @field:SerializedName("tipe")
    val vehicle: Long,

    @field:SerializedName("origins")
    val origins: String,

    @field:SerializedName("destinations")
    val destinations: String,

    @field:SerializedName("destinations2")
    val destinations2: String,

    @field:SerializedName("destinations3")
    val destinations3: String,

    @field:SerializedName("destinations4")
    val destinations4: String,

    @field:SerializedName("destinations5")
    val destinations5: String
)