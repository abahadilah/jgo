package adilahsoft.jgo.android.feature_payment.ui

import adilahsoft.jgo.android.feature_payment.databinding.AdapterPaymentItemBinding
import edts.base.android.core_domain.model.PaymentData
import edts.base.core_utils.money
import id.co.edtslib.baserecyclerview2.BaseViewHolder

class PaymentHolder(private val binding: AdapterPaymentItemBinding): BaseViewHolder<PaymentData>(binding) {
    override fun setData(t: PaymentData?) {
        binding.tvKg.text = t?.name
        binding.tvPrice.text = t?.amount?.money(binding.root.context)
        binding.tvTime.text = t?.company
        binding.tvStatus.text = PaymentStatus.getStatus(t?.state)?.toString()
        binding.tvInvoiceNo.text = t?.invoice

    }
}