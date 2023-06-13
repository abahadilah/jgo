package edts.base.android.core_data.source.remote.response

import com.google.gson.annotations.SerializedName

data class OrderDetailRecipientResponse (
    @field:SerializedName("pickup_name")
    val pickupName: IdNameResponse,

    @field:SerializedName("pickup_city")
    val pickupCity: IdNameResponse,

    @field:SerializedName("recipient_name")
    val recipientName: IdNameResponse,

    @field:SerializedName("recipient_city")
    val recipientCity: IdNameResponse,

    @field:SerializedName("jarak")
    val distance: Double?,

)