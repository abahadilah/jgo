package edts.base.android.core_data.source.remote

import edts.base.android.core_data.source.remote.network.CustomerApiService
import edts.base.android.core_data.source.remote.request.*
import id.co.edtslib.data.BaseDataSource

class CustomerRemoteDataSource(
    private val customerApiService: CustomerApiService
) : BaseDataSource() {


    suspend fun login(
        username: String,
        password: String,
        deviceId: String,
        deviceType: String,
        deviceOsName: String
    ) =
        getResult {
            customerApiService.login(
                LoginRequest(
                    username = username,
                    password = password,
                    deviceId = deviceId,
                    deviceType = deviceType,
                    deviceOsName = deviceOsName
                )
            )
        }
}