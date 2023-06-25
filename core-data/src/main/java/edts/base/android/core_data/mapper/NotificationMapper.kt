package edts.base.android.core_data.mapper

import edts.base.android.core_data.source.local.NotificationEntity
import edts.base.android.core_data.source.remote.response.NotificationResponse
import edts.base.android.core_domain.model.NotificationData
import org.mapstruct.Mapper
import org.mapstruct.Mappings

@Mapper
interface NotificationMapper {
    @Mappings
    fun notificationEntityToModel(input: List<NotificationEntity>?): List<NotificationData>?

    @Mappings
    fun notificationResponseToEntity(input: List<NotificationResponse>?): List<NotificationEntity>?

}