package edts.uco.android.feature_map.ui.tray

import edts.base.android.core_domain.model.VehicleTypeData
import edts.uco.android.feature_map.databinding.AdapterSearchLocationBinding
import id.co.edtslib.baserecyclerview.BaseRecyclerViewAdapterDelegate
import id.co.edtslib.baserecyclerview.BaseViewHolder

class AddressHolder(private val binding: AdapterSearchLocationBinding):
    BaseViewHolder<VehicleTypeData>(binding) {
    override fun setData(
        list: MutableList<VehicleTypeData>,
        position: Int,
        delegate: BaseRecyclerViewAdapterDelegate<VehicleTypeData>?
    ) {
        binding.tvAddress.text = list[position].name
        //binding.tvAddressFull.text = list[position].address
    }
}