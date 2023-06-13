package edts.uco.android.feature_order.ui.status

import edts.uco.android.feature_order.ui.OrderStatus
import id.co.edtslib.edtsds.list.radiobuttonlist.DataSelected

class OrderStatusFilterData(val type: OrderStatus): DataSelected() {
    override fun toString() = type.toString()
}