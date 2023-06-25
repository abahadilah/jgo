package edts.base.android.core_data.source

import edts.base.android.core_data.mapper.NotificationMapper
import edts.base.android.core_data.source.local.NotificationLocalDataSource
import edts.base.android.core_data.source.local.ProfileLocalDataSource
import edts.base.android.core_data.source.remote.FcmRemoteDataSource
import edts.base.android.core_data.source.remote.response.NotificationResponse
import edts.base.android.core_domain.model.NotificationData
import edts.base.android.core_domain.repository.INotificationRepository
import id.co.edtslib.data.NetworkBoundGetResource
import id.co.edtslib.data.NetworkBoundProcessResource
import id.co.edtslib.data.Result
import id.co.edtslib.data.source.local.HttpHeaderLocalSource
import id.co.edtslib.data.source.remote.SessionRemoteDataSource
import kotlinx.coroutines.flow.flow
import org.mapstruct.factory.Mappers

class NotificationRepository(private val profileLocalDataSource: ProfileLocalDataSource,
                             private val fcmRemoteDataSource: FcmRemoteDataSource,
                             private val localDataSource: HttpHeaderLocalSource,
                             private val sessionRemoteDataSource: SessionRemoteDataSource,
                             private val notificationLocalDataSource: NotificationLocalDataSource
):
    INotificationRepository {
    override fun bind(fcm: String?) =
        object : NetworkBoundProcessResource<Any?, Any?>(
            localDataSource, sessionRemoteDataSource
        ) {
            override suspend fun callBackResult(data: Any?) = true
            override suspend fun createCall(): Result<Any?> {
                val cached = profileLocalDataSource.getCached()
                return if (cached?.id == null) {
                    Result.success(null)
                } else {
                    fcmRemoteDataSource.bind(id = cached.id, fcmId = fcm)
                }
            }


        }.asFlow()

    override fun get(isReload: Boolean) =
        object : NetworkBoundGetResource<List<NotificationData>?, List<NotificationResponse>>(
            localDataSource, sessionRemoteDataSource
        ) {
            override suspend fun createCall(): Result<List<NotificationResponse>> {
                val cached = profileLocalDataSource.getCached()

                return fcmRemoteDataSource.get(id = if (cached?.id == null) 0 else cached.id)
            }

            override fun getCached() = flow {
                val cached = notificationLocalDataSource.getCached()
                emit(
                    Mappers.getMapper(NotificationMapper::class.java)
                    .notificationEntityToModel(cached))
            }


            override suspend fun saveCallResult(data: List<NotificationResponse>) {
                data.let {
                    val mapper = Mappers.getMapper(NotificationMapper::class.java)
                        .notificationResponseToEntity(it)
                    notificationLocalDataSource.save(mapper)
                }
            }

            override fun shouldFetch(data: List<NotificationData>?) =
                isReload || data?.isNotEmpty() != true

        }.asFlow()
}
