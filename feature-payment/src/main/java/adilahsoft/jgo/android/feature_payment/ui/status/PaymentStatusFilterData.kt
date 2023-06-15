package adilahsoft.jgo.android.feature_payment.ui.status

import edts.base.android.core_data.source.local.PaymentStatus
import id.co.edtslib.edtsds.list.radiobuttonlist.DataSelected

class PaymentStatusFilterData(val type: PaymentStatus): DataSelected() {
    override fun toString() = type.toString()
}