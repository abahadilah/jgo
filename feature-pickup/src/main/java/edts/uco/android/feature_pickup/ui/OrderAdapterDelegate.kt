package edts.uco.android.feature_pickup.ui

import edts.base.android.core_domain.model.OrderData
import id.co.edtslib.baserecyclerview2.BaseRecyclerView2AdapterDelegate

interface OrderAdapterDelegate: BaseRecyclerView2AdapterDelegate<OrderData> {
    fun onInvoiceDetail(t: OrderData?)
}