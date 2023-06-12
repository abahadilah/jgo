package edts.base.android.core_data.source.remote.response

import com.google.gson.annotations.SerializedName

data class OrderDetailCostResponse (
    @field:SerializedName("cost")
    val cost: List<Any>,

    @field:SerializedName("amount")
    val amount: String

)