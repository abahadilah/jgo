package edts.uco.android.feature_pickup.ui.status

import edts.uco.android.feature_pickup.ui.OrderStatus
import id.co.edtslib.edtsds.list.radiobuttonlist.DataSelected

class OrderStatusFilterData(val type: OrderStatus): DataSelected() {
    override fun toString() = type.toString()
}