package edts.base.android.core_domain.model

data class InvoiceDetailData (
    val id: Long,
    val name: String,
    val created: String?,
    val dueDate: String?,
    val state: String?,
    val total: Double?,
    val tax: Double?,
    var userId: String?,
    var residual: Double?,
    var company: String?,
    var lines: List<InvoiceDetailLineData>?,

    )
