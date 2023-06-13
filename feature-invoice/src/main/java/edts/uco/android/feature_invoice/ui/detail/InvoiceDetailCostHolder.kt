package edts.uco.android.feature_invoice.ui.detail

import edts.base.android.core_domain.model.InvoiceDetailLineData
import edts.base.core_utils.money
import edts.uco.android.feature_invoice.databinding.AdapterInvoiceDetailCostBinding
import id.co.edtslib.baserecyclerview.BaseRecyclerViewAdapterDelegate
import id.co.edtslib.baserecyclerview.BaseViewHolder

class InvoiceDetailCostHolder(private val binding: AdapterInvoiceDetailCostBinding):
    BaseViewHolder<InvoiceDetailLineData>(binding) {

    override fun setData(
        list: MutableList<InvoiceDetailLineData>,
        position: Int,
        delegate: BaseRecyclerViewAdapterDelegate<InvoiceDetailLineData>?
    ) {
        val t = list[position]

        binding.tvName.text = t.name
        binding.tvBeforeTax.text = t.beforeTax?.money(itemView.context)
        binding.tvDiscount.text = t.discount?.money(itemView.context)
        binding.tvAmount.text = t.total?.money(itemView.context)
    }
}