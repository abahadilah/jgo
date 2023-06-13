package edts.base.android.core_data.source.local

data class InvoiceEntity (
    val id: Long,
    val name: String,
    val created: String?,
    val state: String?,
    val total: Double?,
    val tax: Double?,
    var userId: String?,
    var residual: Double?,
    val dueDate: String?,
    var company: String?
    )