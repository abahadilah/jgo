package edts.base.android.core_domain.repository

import edts.base.android.core_domain.model.NotificationData
import id.co.edtslib.data.Result
import kotlinx.coroutines.flow.Flow

interface INotificationRepository {
    fun get(isReload: Boolean): Flow<Result<List<NotificationData>?>>
    fun bind(fcm: String?): Flow<Any?>
}