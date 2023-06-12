package edts.base.android.core_domain.model

data class InvoiceDetailLineData(
    val name: String? = null,
    val beforeTax: Double? = null,
    val discount: String? = null,
    val total: String? = null,
    var lines: List<InvoiceDetailLineData>?,

    )
