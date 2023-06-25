package edts.base.android.core_domain.usecase

import edts.base.android.core_domain.model.NotificationData
import id.co.edtslib.data.Result
import kotlinx.coroutines.flow.Flow

interface NotificationUseCase {
    fun get(isReload: Boolean): Flow<Result<List<NotificationData>?>>
    fun bind(fcm: String?): Flow<Any?>
}