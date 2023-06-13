package edts.uco.android.feature_activity.ui

import edts.base.core_utils.money
import edts.uco.android.feature_activity.R
import edts.uco.android.feature_activity.databinding.AdapterInvoicePeriodBinding
import id.co.edtslib.baserecyclerview2.BaseViewHolder
import java.text.SimpleDateFormat
import java.util.*

class InvoicePeriodHolder(private val binding: AdapterInvoicePeriodBinding):
    BaseViewHolder<InvoicePeriodData?>(binding) {
    override fun setData(t: InvoicePeriodData?) {
        val simpleDateFormat = SimpleDateFormat("MMMM yyyy", Locale("ID"))
        val now = simpleDateFormat.format(Date())

        binding.tvDate.text = if (now == t?.date) binding.root.context.getString(R.string.invoice_this_month) else t?.date
        binding.tvTotal.text = t?.total?.money(itemView.context)
    }
}