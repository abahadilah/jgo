package edts.base.android.core_data.source.remote.response

import com.google.gson.annotations.SerializedName

data class OrderDetailRecipientResponse (
    @field:SerializedName("pickup_name")
    val pickupName: List<Any>,

    @field:SerializedName("pickup_city")
    val pickupCity: List<Any>,

    @field:SerializedName("recipient_name")
    val recipientName: List<Any>,

    @field:SerializedName("recipient_city")
    val recipientCity: List<Any>,

    @field:SerializedName("jarak")
    val distance: Double?,

)