package edts.uco.android.feature_map.ui.price

import edts.base.android.core_domain.model.VehicleTypeData

interface VehicleTypeDelegate {
    fun onSelected(vehicleTypeData: VehicleTypeData)
}