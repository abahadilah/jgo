package edts.uco.android.feature_invoice.ui.status

import edts.uco.android.feature_invoice.ui.InvoiceStatus

interface InvoiceStatusFilterDelegate {
    fun onSubmit(selected: InvoiceStatus)
}