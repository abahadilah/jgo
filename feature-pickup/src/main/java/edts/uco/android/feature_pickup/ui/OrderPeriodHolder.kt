package edts.uco.android.feature_pickup.ui

import edts.uco.android.feature_pickup.R
import edts.uco.android.feature_pickup.databinding.AdapterPeriodBinding
import id.co.edtslib.baserecyclerview2.BaseViewHolder
import java.text.SimpleDateFormat
import java.util.*

class OrderPeriodHolder(private val binding: AdapterPeriodBinding): BaseViewHolder<String?>(binding) {
    override fun setData(t: String?) {
        val simpleDateFormat = SimpleDateFormat("MMMM yyyy", Locale("ID"))
        val now = simpleDateFormat.format(Date())

        binding.textView.text = if (now == t) binding.root.context.getString(R.string.order_this_month) else t
    }
}