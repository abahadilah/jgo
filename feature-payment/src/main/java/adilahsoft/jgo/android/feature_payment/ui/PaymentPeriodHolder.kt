package adilahsoft.jgo.android.feature_payment.ui

import adilahsoft.jgo.android.feature_payment.R
import adilahsoft.jgo.android.feature_payment.databinding.AdapterPaymentPeriodBinding
import edts.base.core_utils.money
import id.co.edtslib.baserecyclerview2.BaseViewHolder
import java.text.SimpleDateFormat
import java.util.*

class PaymentPeriodHolder(private val binding: AdapterPaymentPeriodBinding):
    BaseViewHolder<PaymentPeriodData?>(binding) {
    override fun setData(t: PaymentPeriodData?) {
        val simpleDateFormat = SimpleDateFormat("MMMM yyyy", Locale("ID"))
        val now = simpleDateFormat.format(Date())

        binding.tvDate.text = if (now == t?.date) binding.root.context.getString(R.string.payment_this_month) else t?.date
        binding.tvTotal.text = t?.total?.money(itemView.context)
    }
}