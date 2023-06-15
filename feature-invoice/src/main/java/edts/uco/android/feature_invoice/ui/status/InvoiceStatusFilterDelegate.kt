package edts.uco.android.feature_invoice.ui.status

import edts.base.android.core_data.source.local.InvoiceStatus

interface InvoiceStatusFilterDelegate {
    fun onSubmit(selected: InvoiceStatus)
}