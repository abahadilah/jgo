package edts.uco.android.feature_map.ui

import edts.base.android.core_domain.model.VehicleTypeData
import edts.base.android.core_domain.usecase.VehicleUseCase
import id.co.edtslib.uibase.BaseViewModel

class MapViewModel(private val mapUseCase: VehicleUseCase): BaseViewModel() {
    var location: VehicleTypeData?  = null
    //fun geoReverse(lat: Double, lng: Double) = mapUseCase.geoReverse(lat, lng).asLiveData()
}