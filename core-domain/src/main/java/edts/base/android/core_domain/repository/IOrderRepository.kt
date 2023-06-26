package edts.base.android.core_domain.repository

import edts.base.android.core_domain.model.CreateOrderAddressData
import edts.base.android.core_domain.model.CreateOrderData
import edts.base.android.core_domain.model.CustomerData
import edts.base.android.core_domain.model.OrderData
import edts.base.android.core_domain.model.OrderDetailData
import kotlinx.coroutines.flow.Flow
import id.co.edtslib.data.Result


interface IOrderRepository {
    fun get(isReload: Boolean,
            status: String = "all",
            customer: CustomerData? = null): Flow<Result<List<OrderData>?>>
    fun getDetail(orderId: Long): Flow<Result<OrderDetailData?>>
    fun createOrder(vehicleType: Long, product: String,
                    length: Int?, width: Int?, height: Int?, coli: Int?, weight: Int?,
                    description: String?, originName: String, originCity: String?,
                    originLat: Double, originLng: Double, destinationName: String,
                    destinationCity: String?, destinationLat: Double, destinationLng: Double,
                    destinationName2: String?,
                    destinationCity2: String?,
                    destinationLat2: Double?,
                    destinationLng2: Double?,
                    destinationName3: String?,
                    destinationCity3: String?,
                    destinationLat3: Double?,
                    destinationLng3: Double?,
                    destinationName4: String?,
                    destinationCity4: String?,
                    destinationLat4: Double?,
                    destinationLng4: Double?,
                    destinationName5: String?,
                    destinationCity5: String?,
                    destinationLat5: Double?,
                    destinationLng5: Double?):
            Flow<Result<CreateOrderData?>>

    fun getOriginAddressHistory(): Flow<List<CreateOrderAddressData>?>
    fun getDestinationAddressHistory(): Flow<List<CreateOrderAddressData>?>
}