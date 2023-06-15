package edts.uco.android.feature_invoice.ui.status

import edts.base.android.core_data.source.local.InvoiceStatus
import id.co.edtslib.edtsds.list.radiobuttonlist.DataSelected

class InvoiceStatusFilterData(val type: InvoiceStatus): DataSelected() {
    override fun toString() = type.toString()
}