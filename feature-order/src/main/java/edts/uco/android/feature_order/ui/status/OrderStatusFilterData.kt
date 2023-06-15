package edts.uco.android.feature_order.ui.status

import edts.base.android.core_data.source.local.OrderStatus
import id.co.edtslib.edtsds.list.radiobuttonlist.DataSelected

class OrderStatusFilterData(val type: OrderStatus): DataSelected() {
    override fun toString() = type.toString()
}