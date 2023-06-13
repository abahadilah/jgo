package edts.uco.android.feature_invoice.ui.status

import edts.uco.android.feature_invoice.ui.InvoiceStatus
import id.co.edtslib.edtsds.list.radiobuttonlist.DataSelected

class InvoiceStatusFilterData(val type: InvoiceStatus): DataSelected() {
    override fun toString() = type.toString()
}