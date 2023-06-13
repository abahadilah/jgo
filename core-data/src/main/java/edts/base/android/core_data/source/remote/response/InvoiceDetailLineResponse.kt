package edts.base.android.core_data.source.remote.response

import com.google.gson.annotations.SerializedName

data class InvoiceDetailLineResponse(

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("beforetax")
    val beforeTax: Double? = null,

    @field:SerializedName("amount_disc")
    val discount: Double? = null,

    @field:SerializedName("price_total")
    val total: Double? = null
)