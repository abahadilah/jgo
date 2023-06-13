package edts.uco.android.feature_order.ui.status

import edts.uco.android.feature_order.ui.OrderStatus

interface OrderStatusFilterDelegate {
    fun onSubmit(selected: OrderStatus)
}