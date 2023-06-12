package edts.base.android

import androidx.lifecycle.asLiveData
import edts.base.android.core_domain.usecase.CustomerUseCase
import id.co.edtslib.uibase.BaseViewModel

class DeeplinkViewModel(private val customerUseCase: CustomerUseCase): BaseViewModel() {
    fun logout() = customerUseCase.logout().asLiveData()

}