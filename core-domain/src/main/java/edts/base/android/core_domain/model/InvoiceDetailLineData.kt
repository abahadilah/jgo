package edts.base.android.core_domain.model

data class InvoiceDetailLineData(
    val name: String? = null,
    val beforeTax: Double? = null,
    val discount: Double? = null,
    val total: Double? = null,
    var lines: List<InvoiceDetailLineData>?,

    )
