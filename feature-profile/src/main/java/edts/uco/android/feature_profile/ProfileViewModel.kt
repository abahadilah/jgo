package edts.uco.android.feature_profile

import androidx.lifecycle.asLiveData
import edts.base.android.core_domain.usecase.CustomerUseCase
import edts.base.android.core_domain.usecase.ProfileUseCase
import id.co.edtslib.uibase.BaseViewModel

class ProfileViewModel(private val customerUseCase: CustomerUseCase,
                       private val profileUseCase: ProfileUseCase): BaseViewModel() {
    fun get() = profileUseCase.get().asLiveData()
    fun logout() = customerUseCase.logout().asLiveData()
}