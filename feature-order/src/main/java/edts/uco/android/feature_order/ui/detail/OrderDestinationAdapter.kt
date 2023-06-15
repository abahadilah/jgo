package edts.uco.android.feature_order.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import edts.base.android.core_domain.model.OrderDetailRecipientData
import edts.uco.android.feature_order.databinding.AdapterDestinationBinding
import id.co.edtslib.baserecyclerview.BaseRecyclerViewAdapter

class OrderDestinationAdapter: BaseRecyclerViewAdapter<AdapterDestinationBinding, OrderDetailRecipientData>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> AdapterDestinationBinding
        get() = AdapterDestinationBinding::inflate

    override fun createHolder() = OrderDestinationHolder(binding)
}