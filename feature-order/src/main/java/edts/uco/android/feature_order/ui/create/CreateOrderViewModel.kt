package edts.uco.android.feature_order.ui.create

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asFlow
import androidx.lifecycle.asLiveData
import com.google.android.libraries.places.api.model.Place
import edts.base.android.core_domain.model.ProfileData
import edts.base.android.core_domain.model.VehicleTypeData
import edts.base.android.core_domain.usecase.OrderUseCase
import edts.base.android.core_domain.usecase.ProfileUseCase
import edts.base.android.core_domain.usecase.VehicleUseCase
import id.co.edtslib.uibase.BaseViewModel
import kotlinx.coroutines.flow.combine

class CreateOrderViewModel(private val vehicleUseCase: VehicleUseCase,
                           private val profileUseCase: ProfileUseCase,
                           private val orderUseCase: OrderUseCase): BaseViewModel() {
    var vehicleType = MutableLiveData<VehicleTypeData?>()
    var useOfficeAddress = MutableLiveData<Boolean>().apply { postValue(true) }
    var profile = MutableLiveData<ProfileData>()

    var originAddress = MutableLiveData<Place?>()
    var originName: String? = null
    var originCity: String? = null

    var destinationAddress = MutableLiveData<Place?>()
    var destinationName: String? = null
    var destinationCity: String? = null

    var destinationAddress2 = MutableLiveData<Place?>()
    var destinationName2: String? = null
    var destinationCity2: String? = null

    var destinationAddress3 = MutableLiveData<Place?>()
    var destinationName3: String? = null
    var destinationCity3: String? = null

    var destinationAddress4 = MutableLiveData<Place?>()
    var destinationName4: String? = null
    var destinationCity4: String? = null

    var destinationAddress5 = MutableLiveData<Place?>()
    var destinationName5: String? = null
    var destinationCity5: String? = null

    var isMultipleDestination = MutableLiveData<Boolean?>()

    var length: Int? = null
    var width: Int? = null
    var height: Int? = null

    var weight: Int? = null
    var coli: Int? = null

    var productName: String? = null
    var description: String? = null

    val isCheckPriseValid = combine(
        vehicleType.asFlow(),
        originAddress.asFlow(),
        destinationAddress.asFlow()
    ) { vehicleType, originAddress, destinationAddress ->
        return@combine vehicleType != null
                && originAddress != null
                && destinationAddress != null
    }

    val isCheckPriseValid2 = combine(
        vehicleType.asFlow(),
        originAddress.asFlow(),
        destinationAddress2.asFlow()
    ) { vehicleType, originAddress, destinationAddress ->
        return@combine vehicleType != null
                && originAddress != null
                && destinationAddress != null && this.destinationAddress.value != null
    }

    val isCheckPriseValid3 = combine(
        vehicleType.asFlow(),
        originAddress.asFlow(),
        destinationAddress3.asFlow()
    ) { vehicleType, originAddress, destinationAddress ->
        return@combine vehicleType != null
                && originAddress != null
                && destinationAddress != null && this.destinationAddress.value != null
    }

    val isCheckPriseValid4 = combine(
        vehicleType.asFlow(),
        originAddress.asFlow(),
        destinationAddress4.asFlow()
    ) { vehicleType, originAddress, destinationAddress ->
        return@combine vehicleType != null
                && originAddress != null
                && destinationAddress != null && this.destinationAddress.value != null
    }

    val isCheckPriseValid5 = combine(
        vehicleType.asFlow(),
        originAddress.asFlow(),
        destinationAddress5.asFlow()
    ) { vehicleType, originAddress, destinationAddress ->
        return@combine vehicleType != null
                && originAddress != null
                && destinationAddress != null && this.destinationAddress.value != null
    }

    fun getVehicleType(isReload: Boolean) = vehicleUseCase.get(isReload).asLiveData()

    fun checkPrice() = vehicleUseCase.checkPrice(
        vehicle = vehicleType.value!!.id,
        originLat = originAddress.value!!.latLng!!.latitude,
        originLng = originAddress.value!!.latLng!!.longitude,
        destination1Lat = destinationAddress.value!!.latLng!!.latitude,
        destination1Lng = destinationAddress.value!!.latLng!!.longitude,
        destination2Lat = destinationAddress2.value?.latLng?.latitude,
        destination2Lng = destinationAddress2.value?.latLng?.longitude,
        destination3Lat = destinationAddress3.value?.latLng?.latitude,
        destination3Lng = destinationAddress3.value?.latLng?.longitude,
        destination4Lat = destinationAddress4.value?.latLng?.latitude,
        destination4Lng = destinationAddress5.value?.latLng?.longitude,
        destination5Lat = destinationAddress5.value?.latLng?.latitude,
        destination5Lng = destinationAddress5.value?.latLng?.longitude
    ).asLiveData()

    fun getOriginAddressHistory() = orderUseCase.getOriginAddressHistory().asLiveData()
    fun getDestinationAddressHistory() = orderUseCase.getDestinationAddressHistory().asLiveData()

    fun getProfile() = profileUseCase.get().asLiveData()
    fun createOrder() =
        orderUseCase.createOrder(vehicleType = vehicleType.value!!.id,
            product = productName!!,
            length = length,
            width = width,
            height = height,
            weight = weight,
            coli = coli,
            description = if (description == null) "" else description,
            originName = originName!!,
            originCity = originCity,
            originLat = originAddress.value!!.latLng!!.latitude,
            originLng = originAddress.value!!.latLng!!.longitude,
            destinationName = destinationName!!,
            destinationCity = destinationCity,
            destinationLat = destinationAddress.value!!.latLng!!.latitude,
            destinationLng = destinationAddress.value!!.latLng!!.longitude,
            destinationName2 = destinationName2,
            destinationCity2 = destinationCity2,
            destinationLat2 = destinationAddress2.value?.latLng?.latitude,
            destinationLng2 = destinationAddress2.value?.latLng?.longitude,
            destinationName3 = destinationName3,
            destinationCity3 = destinationCity3,
            destinationLat3 = destinationAddress3.value?.latLng?.latitude,
            destinationLng3 = destinationAddress3.value?.latLng?.longitude,
            destinationName4 = destinationName4,
            destinationCity4 = destinationCity4,
            destinationLat4 = destinationAddress4.value?.latLng?.latitude,
            destinationLng4 = destinationAddress4.value?.latLng?.longitude,
            destinationName5 = destinationName5,
            destinationCity5 = destinationCity5,
            destinationLat5 = destinationAddress5.value?.latLng?.latitude,
            destinationLng5 = destinationAddress5.value?.latLng?.longitude
        ).asLiveData()
}