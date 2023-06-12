package edts.uco.android.feature_pickup.ui.detail

import edts.base.android.core_domain.model.OrderDetailCostData
import edts.base.core_utils.money
import edts.uco.android.feature_pickup.databinding.AdapterDeliveryCostBinding
import id.co.edtslib.baserecyclerview.BaseRecyclerViewAdapterDelegate
import id.co.edtslib.baserecyclerview.BaseViewHolder

class OrderDetailCostHolder(private val binding: AdapterDeliveryCostBinding):
    BaseViewHolder<OrderDetailCostData>(binding) {

    override fun setData(
        list: MutableList<OrderDetailCostData>,
        position: Int,
        delegate: BaseRecyclerViewAdapterDelegate<OrderDetailCostData>?
    ) {
        val t = list[position]

        binding.tvName.text = t.cost[1].toString()
        binding.tvValue.text = t.amount.money(itemView.context)
    }
}