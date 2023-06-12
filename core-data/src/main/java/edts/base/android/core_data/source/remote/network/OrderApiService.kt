package edts.base.android.core_data.source.remote.network

import edts.base.android.core_data.source.remote.request.OrderDetailRequest
import edts.base.android.core_data.source.remote.request.OrderRequest
import edts.base.android.core_data.source.remote.response.OrderDetailResponse
import edts.base.android.core_data.source.remote.response.OrderResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface OrderApiService {
    @POST("/orders")
    suspend fun get(@Body orderRequest: OrderRequest): Response<List<OrderResponse>>

    @POST("/order_line")
    suspend fun getDetail(@Body orderDetailRequest: OrderDetailRequest): Response<OrderDetailResponse>

}