package edts.base.android.core_data.source.remote

import edts.base.android.core_data.source.remote.network.FcmApiApiService
import edts.base.android.core_data.source.remote.request.*
import id.co.edtslib.data.BaseDataSource

class FcmRemoteDataSource(
    private val fcmApiApiService: FcmApiApiService
) : BaseDataSource() {
    suspend fun bind(fcmId: String?, deviceId: String, phoneNo: String) =
        getResult { fcmApiApiService.bind(
            FcmBindRequest(
                fcmId = fcmId,
            deviceId = deviceId,
            osType = "ANDROID",
                phone = phoneNo
        )) }

    suspend fun unbind(fcmId: String?, deviceId: String) =
        getResult { fcmApiApiService.unbind(
            FcmBindRequest(
                fcmId = fcmId,
                deviceId = deviceId,
                osType = "ANDROID",
                phone = null
            )) }
}