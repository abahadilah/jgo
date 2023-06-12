package edts.base.android.core_data.source.remote.request

import com.google.gson.annotations.SerializedName

data class OrderDetailRequest (
    @field:SerializedName("order_id")
    val orderId: Long
)