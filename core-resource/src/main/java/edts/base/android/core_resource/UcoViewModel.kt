package edts.base.android.core_resource

import androidx.lifecycle.asLiveData
import edts.base.android.core_domain.usecase.ConfigurationUseCase
import id.co.edtslib.uibase.BaseViewModel

class UcoViewModel(private val configurationUseCase: ConfigurationUseCase): BaseViewModel() {
    fun getConfiguration() = configurationUseCase.get().asLiveData()
    fun skipVersion(version: String?) = configurationUseCase.skipVersion(version).asLiveData()
}