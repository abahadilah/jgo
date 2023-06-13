package edts.base.android.core_data.source.local

data class InvoiceEntity (
    val id: Long,
    val name: String,
    val created: String?,
    val state: String?,
    val total: Double?,
    val tax: String?,
    var userId: String?,
    var residual: String?,
    val dueDate: String?,
    var company: String?
    )