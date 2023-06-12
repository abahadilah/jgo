package edts.base.android.core_data.source.remote.request

import com.google.gson.annotations.SerializedName

data class InvoiceDetailRequest(
    @field:SerializedName("move_id")
    val id: Long

)
