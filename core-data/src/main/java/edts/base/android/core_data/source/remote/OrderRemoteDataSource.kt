package edts.base.android.core_data.source.remote

import edts.base.android.core_data.source.remote.network.OrderApiService
import edts.base.android.core_data.source.remote.request.CreateOrderDestinationRequest
import edts.base.android.core_data.source.remote.request.CreateOrderRequest
import edts.base.android.core_data.source.remote.request.OrderDetailRequest
import edts.base.android.core_data.source.remote.request.OrderRequest
import id.co.edtslib.data.BaseDataSource

class OrderRemoteDataSource(
    private val orderApiService: OrderApiService
) : BaseDataSource() {

    suspend fun get(id: Long, status: String) =
        getResult { orderApiService.get(
            OrderRequest(
            partnerId = id,
            status = status
        )
        ) }

    suspend fun getDetail(id: Long) =
        getResult { orderApiService.getDetail(
            OrderDetailRequest(
                orderId = id,
            )
        ) }

    suspend fun createOrder(customerId: Long, vehicleType: Long, date: String, product: String,
                            length: Int?, width: Int?, height: Int?, coli: Int?, weight: Int?,
                            description: String?, addresses: List<CreateOrderDestinationRequest> ) =
        getResult { orderApiService.createOrder(
            CreateOrderRequest(
                customerId = customerId,
                vehicleType = vehicleType,
                date = date,
                type = "truck",
                product = product,
                length = length ?: 0,
                width = width ?: 0,
                height = height ?: 0,
                coli = coli ?: 0,
                weight = weight ?: 0,
                ref = "",
                description = description ?: "",
                addresses = addresses
            )
        ) }

}