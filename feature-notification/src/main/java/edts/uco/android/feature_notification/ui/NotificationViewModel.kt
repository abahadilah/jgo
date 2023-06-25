package edts.uco.android.feature_notification.ui

import androidx.lifecycle.asLiveData
import edts.base.android.core_domain.usecase.NotificationUseCase
import id.co.edtslib.uibase.BaseViewModel

class NotificationViewModel(private val useCase: NotificationUseCase): BaseViewModel() {
    fun get(isReload: Boolean) = useCase.get(isReload).asLiveData()
}