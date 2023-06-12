package edts.base.android.core_data.source.remote.request

import com.google.gson.annotations.SerializedName

data class InvoiceRequest (
    @field:SerializedName("partner_id")
    val partnerId: Long,

    @field:SerializedName("status")
    val status: String?
)