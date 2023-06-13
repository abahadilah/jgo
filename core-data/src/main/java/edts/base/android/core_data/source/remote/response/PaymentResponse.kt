package edts.base.android.core_data.source.remote.response

import com.google.gson.annotations.SerializedName

data class PaymentResponse (
    @field:SerializedName("id")
    val id: Long,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("partner_id")
    val partnerName: String?,

    @field:SerializedName("partner_type")
    val partnerType: String?,

    @field:SerializedName("payment_type")
    val type: String?,

    @field:SerializedName("company_id")
    val company: String?,

    @field:SerializedName("amount")
    val amount: Double?,

    @field:SerializedName("date")
    val date: String?,

    @field:SerializedName("ref")
    val invoice: String?,

    @field:SerializedName("journal_id")
    val journal: String?,

    @field:SerializedName("state")
    val state: String?
)