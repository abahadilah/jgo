package edts.base.android.core_data.source.local

data class PaymentEntity (
    val id: Long,
    val name: String,
    val partnerName: String?,
    val partnerType: String?,
    val type: String?,
    val company: String?,
    val amount: Double?,
    val date: String?,
    val invoice: String?,
    val journal: String?,
    val state: String?
)