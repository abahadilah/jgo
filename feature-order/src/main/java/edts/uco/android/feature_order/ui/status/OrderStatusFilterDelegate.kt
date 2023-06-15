package edts.uco.android.feature_order.ui.status

import edts.base.android.core_data.source.local.OrderStatus

interface OrderStatusFilterDelegate {
    fun onSubmit(selected: OrderStatus)
}