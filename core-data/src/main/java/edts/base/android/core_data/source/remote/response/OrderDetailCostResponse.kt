package edts.base.android.core_data.source.remote.response

import com.google.gson.annotations.SerializedName

data class OrderDetailCostResponse (
    @field:SerializedName("cost")
    val cost: IdNameResponse,

    @field:SerializedName("amount")
    val amount: Double

)