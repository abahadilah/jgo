package edts.base.android.feature_home.splash

import androidx.lifecycle.asLiveData
import edts.base.android.core_domain.usecase.ConfigurationUseCase
import edts.base.android.core_domain.usecase.ProfileUseCase
import id.co.edtslib.uibase.BaseViewModel

class SplashViewModel(private val profileUseCase: ProfileUseCase,
    private val configurationUseCase: ConfigurationUseCase) : BaseViewModel() {
    fun getProfile() = profileUseCase.getProfile().asLiveData()
    fun getConfiguration() = configurationUseCase.get().asLiveData()

}