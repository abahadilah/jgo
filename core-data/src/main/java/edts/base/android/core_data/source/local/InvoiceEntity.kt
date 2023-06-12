package edts.base.android.core_data.source.local

data class InvoiceEntity (
    val id: Long,
    val name: String,
    val created: String?,
    val state: String?,
    val total: String?,
    val tax: String?,
    var userId: String?,
    var residual: String?,
    val dueDate: String?,
    val created2: String?,
    val dueDate2: String?,
    var company: String?
    )