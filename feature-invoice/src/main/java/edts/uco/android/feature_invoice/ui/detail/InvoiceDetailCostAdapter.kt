package edts.uco.android.feature_invoice.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import edts.base.android.core_domain.model.InvoiceDetailLineData
import edts.uco.android.feature_invoice.databinding.AdapterInvoiceDetailCostBinding
import id.co.edtslib.baserecyclerview.BaseRecyclerViewAdapter

class InvoiceDetailCostAdapter: BaseRecyclerViewAdapter<AdapterInvoiceDetailCostBinding, InvoiceDetailLineData>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> AdapterInvoiceDetailCostBinding
        get() = AdapterInvoiceDetailCostBinding::inflate

    override fun createHolder() = InvoiceDetailCostHolder(binding)
}