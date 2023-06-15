package edts.uco.android.feature_order.ui.detail

import edts.base.android.core_domain.model.OrderDetailRecipientData
import edts.base.core_utils.formatDecimal
import edts.uco.android.feature_order.R
import edts.uco.android.feature_order.databinding.AdapterDestinationBinding
import id.co.edtslib.baserecyclerview.BaseRecyclerViewAdapterDelegate
import id.co.edtslib.baserecyclerview.BaseViewHolder

class OrderDestinationHolder(private val binding: AdapterDestinationBinding):
    BaseViewHolder<OrderDetailRecipientData>(binding) {

    override fun setData(
        list: MutableList<OrderDetailRecipientData>,
        position: Int,
        delegate: BaseRecyclerViewAdapterDelegate<OrderDetailRecipientData>?
    ) {
        val t = list[position]

        binding.tvPickupName.text = t.pickupName.name
        binding.tvPickupCity.text = t.pickupCity.name

        binding.tvRecipientName.text =
            t.recipientName.name
        binding.tvRecipientCity.text =
            t.recipientCity.name

        binding.tvDistance.text = itemView.context.getString(
            R.string.order_distance_unit,
            t.distance?.formatDecimal()
        )
    }
}