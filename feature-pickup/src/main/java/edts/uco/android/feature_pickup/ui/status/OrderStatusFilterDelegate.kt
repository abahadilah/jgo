package edts.uco.android.feature_pickup.ui.status

import edts.uco.android.feature_pickup.ui.OrderStatus

interface OrderStatusFilterDelegate {
    fun onSubmit(selected: OrderStatus)
}