package edts.uco.android.feature_pickup.ui

import edts.base.android.core_domain.model.OrderData

interface OrderDelegate {
    fun onOrderDetail(orderData: OrderData?)
    fun onInvoiceDetail(orderData: OrderData?)
}