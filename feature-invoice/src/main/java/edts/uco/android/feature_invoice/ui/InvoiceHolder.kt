package edts.uco.android.feature_invoice.ui

import edts.base.android.core_domain.model.InvoiceData
import edts.base.core_utils.money
import edts.uco.android.feature_invoice.databinding.AdapterInvoiceItemBinding
import id.co.edtslib.baserecyclerview2.BaseViewHolder

class InvoiceHolder(private val binding: AdapterInvoiceItemBinding): BaseViewHolder<InvoiceData>(binding) {
    override fun setData(t: InvoiceData?) {
        binding.tvKg.text = t?.name
        binding.tvPrice.text = t?.total?.money(binding.root.context)
        binding.tvTime.text = t?.company
        binding.tvStatus.text = InvoiceStatus.getStatus(t?.state)?.toString()

    }
}