package edts.uco.android.feature_pickup.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import edts.base.android.core_domain.model.OrderDetailCostData
import edts.uco.android.feature_pickup.databinding.AdapterDeliveryCostBinding
import id.co.edtslib.baserecyclerview.BaseRecyclerViewAdapter

class OrderDetailCostAdapter: BaseRecyclerViewAdapter<AdapterDeliveryCostBinding, OrderDetailCostData>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> AdapterDeliveryCostBinding
        get() = AdapterDeliveryCostBinding::inflate

    override fun createHolder() = OrderDetailCostHolder(binding)
}