package edts.base.android.core_data.source.remote.response

import com.google.gson.annotations.SerializedName

data class OrderDetailResponse (
    @field:SerializedName("detail_recipient")
    val recipient: List<OrderDetailRecipientResponse>?,

    @field:SerializedName("detail_cost")
    val cost: List<OrderDetailCostResponse>?,

    @field:SerializedName("detail_driver")
    val vehicle: List<OrderDetailDriverResponse>?,
    )