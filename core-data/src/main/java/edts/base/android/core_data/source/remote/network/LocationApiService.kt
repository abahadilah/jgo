package edts.base.android.core_data.source.remote.network

import edts.base.android.core_data.source.remote.request.SearchLocationRequest
import edts.base.android.core_data.source.remote.response.CreateOrderResponse
import id.co.edtslib.data.source.remote.response.ApiContentResponse
import id.co.edtslib.data.source.remote.response.ApiResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface LocationApiService {
    @POST("/api/mobile/registration/gmap/place-autocomplete")
    suspend fun searchLocation(@Body request: SearchLocationRequest): Response<ApiContentResponse<List<CreateOrderResponse>>>

    @GET("/api/mobile/registration/nearest-area")
    suspend fun geoReverse(@Query("latitude") lat: Double,
        @Query("longitude") lng: Double): Response<ApiResponse<CreateOrderResponse>>



}