package edts.base.android.core_data.source.remote.network

import edts.base.android.core_data.source.remote.request.OtpVerifyRequest
import id.co.edtslib.data.source.remote.response.ApiResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface OtpApiService {
    @POST("/api/mobile/registration/request-otp/{phone}")
    suspend fun requestRegistrationOtp(@Path("phone") phone: String): Response<ApiResponse<Any>>

    @POST("/api/mobile/registration/request-otp/{phone}")
    suspend fun resendRegistrationOtp(@Path("phone") phone: String): Response<ApiResponse<Any>>

    @POST("/api/mobile/registration/verify-code")
    suspend fun verifyRegistrationOtp(@Body request: OtpVerifyRequest): Response<ApiResponse<Any>>

}