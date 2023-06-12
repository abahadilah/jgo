package edts.uco.android.feature_activity.ui

import edts.uco.android.feature_activity.R
import edts.uco.android.feature_activity.databinding.AdapterPeriodBinding
import id.co.edtslib.baserecyclerview2.BaseViewHolder
import java.text.SimpleDateFormat
import java.util.*

class InvoicePeriodHolder(private val binding: AdapterPeriodBinding): BaseViewHolder<String?>(binding) {
    override fun setData(t: String?) {
        val simpleDateFormat = SimpleDateFormat("MMMM yyyy", Locale("ID"))
        val now = simpleDateFormat.format(Date())

        binding.textView.text = if (now == t) binding.root.context.getString(R.string.invoice_this_month) else t
    }
}