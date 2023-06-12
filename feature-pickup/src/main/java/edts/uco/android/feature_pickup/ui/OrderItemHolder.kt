package edts.uco.android.feature_pickup.ui

import edts.base.android.core_domain.model.OrderData
import edts.base.core_utils.money
import edts.uco.android.feature_pickup.databinding.AdapterItemBinding
import id.co.edtslib.baserecyclerview2.BaseViewHolder

class OrderItemHolder(private val binding: AdapterItemBinding):
    BaseViewHolder<OrderData>(binding) {
    override fun setData(t: OrderData?) {
        binding.tvKg.text = t?.name
        binding.tvPrice.text = t?.totalAmount?.money(binding.root.context)
        binding.tvTime.text = t?.productName
        binding.tvStatus.text = OrderStatus.getStatus(t?.state)?.toString()
    }
}