package edts.base.android.core_domain.model

data class InvoiceDetailData (
    val id: Long,
    val name: String,
    val created: String?,
    val dueDate: String?,
    val created2: String?,
    val dueDate2: String?,
    val state: String?,
    val total: String?,
    val tax: String?,
    var userId: String?,
    var residual: String?,
    var company: String?,
    var lines: List<InvoiceDetailLineData>?,

    )
