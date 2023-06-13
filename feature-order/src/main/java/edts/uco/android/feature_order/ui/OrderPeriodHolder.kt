package edts.uco.android.feature_order.ui

import edts.base.core_utils.money
import edts.uco.android.feature_order.R
import edts.uco.android.feature_order.databinding.AdapterOrderPeriodBinding
import id.co.edtslib.baserecyclerview2.BaseViewHolder
import java.text.SimpleDateFormat
import java.util.*

class OrderPeriodHolder(private val binding: AdapterOrderPeriodBinding): BaseViewHolder<OrderPeriodData?>(binding) {
    override fun setData(t: OrderPeriodData?) {
        val simpleDateFormat = SimpleDateFormat("MMMM yyyy", Locale("ID"))
        val now = simpleDateFormat.format(Date())

        binding.tvDate.text = if (now == t?.date) binding.root.context.getString(R.string.order_this_month)
            else t?.date
        binding.tvTotal.text = t?.total?.money(itemView.context)
    }
}