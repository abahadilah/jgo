package edts.uco.android.feature_order.ui

import edts.base.android.core_domain.model.OrderData
import edts.base.core_utils.money
import edts.uco.android.feature_order.databinding.AdapterOrderItemBinding
import id.co.edtslib.baserecyclerview2.BaseViewHolder

class OrderItemHolder(private val binding: AdapterOrderItemBinding):
    BaseViewHolder<OrderData>(binding) {
    override fun setData(t: OrderData?) {
        binding.tvDeliveryOrder.text = t?.name
        binding.tvPrice.text = t?.totalAmount?.money(binding.root.context)
        binding.tvProduct.text = t?.productName
        binding.tvStatus.text = OrderStatus.getStatus(t?.state)?.toString()
        binding.tvInvoice.text = t?.invoice?.name

        binding.tvInvoice.setOnClickListener {
            if (delegate is OrderAdapterDelegate) {
                val orderAdapterDelegate = delegate as OrderAdapterDelegate
                orderAdapterDelegate.onInvoiceDetail(t)
            }
        }
    }
}