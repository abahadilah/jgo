package edts.base.android.core_data.source.remote.response

import com.google.gson.annotations.SerializedName

data class CreateOrderResponse(

    @field:SerializedName("result")
    val result: Boolean?,

    @field:SerializedName("id")
    val id: Long?,

    @field:SerializedName("name")
    val name: String?
)