package edts.uco.android.feature_activity.ui.status

import edts.uco.android.feature_activity.ui.InvoiceStatus

interface InvoiceStatusFilterDelegate {
    fun onSubmit(selected: InvoiceStatus)
}