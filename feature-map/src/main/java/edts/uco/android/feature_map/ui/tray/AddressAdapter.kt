package edts.uco.android.feature_map.ui.tray

import android.view.LayoutInflater
import android.view.ViewGroup
import edts.base.android.core_domain.model.VehicleTypeData
import edts.uco.android.feature_map.databinding.AdapterSearchLocationBinding
import id.co.edtslib.baserecyclerview.BaseRecyclerViewAdapter

class AddressAdapter: BaseRecyclerViewAdapter<AdapterSearchLocationBinding, VehicleTypeData>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> AdapterSearchLocationBinding
        get() = AdapterSearchLocationBinding::inflate

    override fun createHolder() = AddressHolder(binding)
}