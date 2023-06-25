package edts.base.android.core_resource

import androidx.lifecycle.asLiveData
import edts.base.android.core_domain.usecase.ConfigurationUseCase
import edts.base.android.core_domain.usecase.NotificationUseCase
import id.co.edtslib.uibase.BaseViewModel

class JGoViewModel(private val configurationUseCase: ConfigurationUseCase,
                   private val notificationUseCase: NotificationUseCase): BaseViewModel() {
    fun getConfiguration() = configurationUseCase.get().asLiveData()
    fun skipVersion(version: String?) = configurationUseCase.skipVersion(version).asLiveData()
    fun bindFcm(fcm: String?) = notificationUseCase.bind(fcm).asLiveData()
}