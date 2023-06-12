package edts.uco.android.feature_pickup.ui

import edts.base.android.core_domain.model.OrderData

interface OrderAdapterDelegate {
    fun onDetail(orderData: OrderData?)
}