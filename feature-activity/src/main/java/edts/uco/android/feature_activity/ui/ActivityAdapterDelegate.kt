package edts.uco.android.feature_activity.ui

import edts.base.android.core_domain.model.InvoiceData

interface ActivityAdapterDelegate {
    fun onDetail(invoiceData: InvoiceData?)
}