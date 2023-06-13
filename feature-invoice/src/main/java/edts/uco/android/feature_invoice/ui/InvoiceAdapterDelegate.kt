package edts.uco.android.feature_invoice.ui

import edts.base.android.core_domain.model.InvoiceData

interface InvoiceAdapterDelegate {
    fun onDetail(invoiceData: InvoiceData?)
}