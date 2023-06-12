package edts.base.android.feature_auth.ui

import androidx.lifecycle.asLiveData
import edts.base.android.core_domain.usecase.CustomerUseCase
import edts.base.android.core_domain.usecase.VehicleUseCase
import id.co.edtslib.uibase.BaseViewModel

class LoginViewModel(private val customerUseCase: CustomerUseCase,
                     private val vehicleUseCase: VehicleUseCase): BaseViewModel() {
    var username: String? = null
    var password: String? = null

    fun login() = customerUseCase.login(username = username!!, password = password!!).asLiveData()
    fun getVehicle() = vehicleUseCase.get(true).asLiveData()
}