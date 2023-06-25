package edts.base.android.core_data.source.remote.network

import edts.base.android.core_data.source.remote.request.FcmBindRequest
import edts.base.android.core_data.source.remote.request.NotificationRequest
import edts.base.android.core_data.source.remote.response.NotificationResponse
import id.co.edtslib.data.source.remote.response.ApiResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface FcmApiApiService {
    @POST("/save_token")
    suspend fun bind(@Body request: FcmBindRequest): Response<ApiResponse<Any>>

    @POST("/notif_promo")
    suspend fun get(@Body request: NotificationRequest): Response<List<NotificationResponse>>

}