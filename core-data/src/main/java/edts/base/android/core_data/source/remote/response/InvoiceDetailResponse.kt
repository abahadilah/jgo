package edts.base.android.core_data.source.remote.response

import com.google.gson.annotations.SerializedName

data class InvoiceDetailResponse (
    @field:SerializedName("id")
    val id: Long,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("invoice_date")
    val created: String?,

    @field:SerializedName("invoice_date_due")
    val dueDate: String?,

    @field:SerializedName("payment_state")
    val state: String?,

    @field:SerializedName("amount_total")
    val total: Double?,

    @field:SerializedName("amount_untaxed")
    val tax: Double?,

    @field:SerializedName("invoice_user_id")
    var userId: String?,

    @field:SerializedName("amount_residual")
    var residual: Double?,

    @field:SerializedName("company_id")
    var company: String?,

    @field:SerializedName("move_line")
    var lines: List<InvoiceDetailLineResponse>?,
)