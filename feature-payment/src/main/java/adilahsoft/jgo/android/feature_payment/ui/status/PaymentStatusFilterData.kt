package adilahsoft.jgo.android.feature_payment.ui.status

import adilahsoft.jgo.android.feature_payment.ui.PaymentStatus
import id.co.edtslib.edtsds.list.radiobuttonlist.DataSelected

class PaymentStatusFilterData(val type: PaymentStatus): DataSelected() {
    override fun toString() = type.toString()
}