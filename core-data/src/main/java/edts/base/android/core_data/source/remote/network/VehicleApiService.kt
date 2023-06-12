package edts.base.android.core_data.source.remote.network

import edts.base.android.core_data.source.remote.request.CheckPriceRequest
import edts.base.android.core_data.source.remote.response.CheckPriceResponse
import edts.base.android.core_data.source.remote.response.VehicleTypeResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface VehicleApiService {
    @GET("/vehicle_type")
    suspend fun get(): Response<List<VehicleTypeResponse>>

    @POST("/get_distance")
    suspend fun checkPrice(@Body request: CheckPriceRequest): Response<List<CheckPriceResponse>>

}