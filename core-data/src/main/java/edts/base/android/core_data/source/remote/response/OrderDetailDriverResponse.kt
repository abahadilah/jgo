package edts.base.android.core_data.source.remote.response

import com.google.gson.annotations.SerializedName

data class OrderDetailDriverResponse (
    @field:SerializedName("driver_name")
    val name: List<Any>,

    @field:SerializedName("driver_phone")
    val phone: String,

    @field:SerializedName("vehicle_id")
    val vehicle: List<Any>


)