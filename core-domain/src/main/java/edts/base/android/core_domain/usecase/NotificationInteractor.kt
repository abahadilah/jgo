package edts.base.android.core_domain.usecase

import edts.base.android.core_domain.repository.INotificationRepository

class NotificationInteractor(private val repository: INotificationRepository): NotificationUseCase {
    override fun get(isReload: Boolean) = repository.get(isReload)
    override fun bind(fcm: String?) = repository.bind(fcm)
}