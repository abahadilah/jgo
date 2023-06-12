package edts.uco.android.feature_map.ui.price

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import com.google.android.libraries.places.api.model.Place
import edts.base.android.core_domain.model.VehicleTypeData
import edts.base.android.core_domain.usecase.VehicleUseCase
import id.co.edtslib.uibase.BaseViewModel

class CheckPriceViewModel(private val vehicleUseCase: VehicleUseCase): BaseViewModel() {
    var vehicleType = MutableLiveData<VehicleTypeData>()

    var originAddress = MutableLiveData<Place>()
    var destinationAddress1 = MutableLiveData<Place>()
    var destinationAddress2 = MutableLiveData<Place>()
    var destinationAddress3 = MutableLiveData<Place>()
    var destinationAddress4 = MutableLiveData<Place>()
    var destinationAddress5 = MutableLiveData<Place>()

    fun getVehicleType(isReload: Boolean) = vehicleUseCase.get(isReload).asLiveData()
    fun checkPrice() = vehicleUseCase.checkPrice(
        vehicle = vehicleType.value!!.id,
        originLat = originAddress.value!!.latLng!!.latitude,
        originLng = originAddress.value!!.latLng!!.longitude,
        destination1Lat = destinationAddress1.value!!.latLng!!.latitude,
        destination1Lng = destinationAddress1.value!!.latLng!!.longitude,
        destination2Lat = destinationAddress2.value?.latLng?.latitude,
        destination2Lng = destinationAddress2.value?.latLng?.longitude,
        destination3Lat = destinationAddress3.value?.latLng?.latitude,
        destination3Lng = destinationAddress3.value?.latLng?.longitude,
        destination4Lat = destinationAddress4.value?.latLng?.latitude,
        destination4Lng = destinationAddress5.value?.latLng?.longitude,
        destination5Lat = destinationAddress5.value?.latLng?.latitude,
        destination5Lng = destinationAddress5.value?.latLng?.longitude
    ).asLiveData()
}