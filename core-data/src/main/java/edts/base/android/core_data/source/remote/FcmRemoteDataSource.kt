package edts.base.android.core_data.source.remote

import edts.base.android.core_data.source.remote.network.FcmApiApiService
import edts.base.android.core_data.source.remote.request.*
import id.co.edtslib.data.BaseDataSource

class FcmRemoteDataSource(
    private val fcmApiApiService: FcmApiApiService
) : BaseDataSource() {
    suspend fun bind(id: Long?, fcmId: String?) =
        getResult { fcmApiApiService.bind(
            FcmBindRequest(
                id = id,
                token = fcmId)) }

    suspend fun get(id: Long) =
        getResult { fcmApiApiService.get(
            NotificationRequest(
                id = id,
                offset = 0,
                limit = 1000)) }

}