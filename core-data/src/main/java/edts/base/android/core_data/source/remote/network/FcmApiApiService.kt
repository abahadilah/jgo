package edts.base.android.core_data.source.remote.network

import edts.base.android.core_data.source.remote.request.FcmBindRequest
import id.co.edtslib.data.source.remote.response.ApiResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface FcmApiApiService {
    @POST("/api/mobile/notification/fcm-bind-supplier")
    suspend fun bind(@Body request: FcmBindRequest): Response<ApiResponse<Any>>

    @POST("/api/mobile/notification/fcm-unbind")
    suspend fun unbind(@Body request: FcmBindRequest): Response<ApiResponse<Any>>
}