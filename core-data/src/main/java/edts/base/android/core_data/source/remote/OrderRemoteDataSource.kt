package edts.base.android.core_data.source.remote

import edts.base.android.core_data.source.remote.network.OrderApiService
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

}