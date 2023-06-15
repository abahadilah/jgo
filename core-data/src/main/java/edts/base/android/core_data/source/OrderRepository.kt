package edts.base.android.core_data.source

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.SphericalUtil
import edts.base.android.core_data.mapper.OrderMapper
import edts.base.android.core_data.source.local.CreateOrderAddressEntity
import edts.base.android.core_data.source.local.CreateOrderDestinationLocalDataSource
import edts.base.android.core_data.source.local.CreateOrderOriginLocalDataSource
import edts.base.android.core_data.source.local.InvoiceLocalDataSource
import edts.base.android.core_data.source.local.ProfileLocalDataSource
import edts.base.android.core_data.source.local.OrderLocalDataSource
import edts.base.android.core_data.source.local.PaymentLocalDataSource
import edts.base.android.core_data.source.remote.OrderRemoteDataSource
import edts.base.android.core_data.source.remote.request.CreateOrderDestinationRequest
import edts.base.android.core_data.source.remote.response.CreateOrderResponse
import edts.base.android.core_data.source.remote.response.OrderDetailResponse
import edts.base.android.core_data.source.remote.response.OrderResponse
import edts.base.android.core_domain.model.CreateOrderData
import edts.base.android.core_domain.model.OrderData
import edts.base.android.core_domain.model.OrderDetailData
import edts.base.android.core_domain.repository.IOrderRepository
import id.co.edtslib.data.NetworkBoundGetResource
import id.co.edtslib.data.NetworkBoundProcessResource
import id.co.edtslib.data.Result
import id.co.edtslib.data.source.local.HttpHeaderLocalSource
import id.co.edtslib.data.source.remote.SessionRemoteDataSource
import kotlinx.coroutines.flow.flow
import org.mapstruct.factory.Mappers
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class OrderRepository(private val localDataSource: HttpHeaderLocalSource,
                      private val sessionRemoteDataSource: SessionRemoteDataSource,
                      private val orderRemoteDataSource: OrderRemoteDataSource,
                      private val orderLocalDataSource: OrderLocalDataSource,
                      private val profileLocalDataSource: ProfileLocalDataSource,
                      private val createOrderOriginLocalDataSource: CreateOrderOriginLocalDataSource,
                      private val createOrderDestinationLocalDataSource: CreateOrderDestinationLocalDataSource,
                      private val invoiceLocalDataSource: InvoiceLocalDataSource,
                      private val paymentLocalDataSource: PaymentLocalDataSource):
    IOrderRepository {

    override fun get(isReload: Boolean, status: String) =
        object : NetworkBoundGetResource<List<OrderData>?, List<OrderResponse>>(
            localDataSource, sessionRemoteDataSource
        ) {
            override suspend fun createCall(): Result<List<OrderResponse>> {
                val cached = profileLocalDataSource.getCached()
                orderLocalDataSource.key = status
                return orderRemoteDataSource.get(id = if (cached?.id == null) 0 else cached.id,
                    status = status
                )
            }

            override fun getCached() = flow {
                val cached = orderLocalDataSource.getCached(status)
                emit(Mappers.getMapper(OrderMapper::class.java)
                    .orderEntityToModel(cached))
            }


            override suspend fun saveCallResult(data: List<OrderResponse>) {
                data.let {
                    val mapper = Mappers.getMapper(OrderMapper::class.java)
                        .orderResponseToEntity(it)
                    orderLocalDataSource.save(status, mapper)
                }
            }

            override fun shouldFetch(data: List<OrderData>?) =
                isReload || data?.isNotEmpty() != true

        }.asFlow()

    override fun getDetail(orderId: Long) =
        object : NetworkBoundProcessResource<OrderDetailData?, OrderDetailResponse>(
            localDataSource, sessionRemoteDataSource
        ) {
            override suspend fun callBackResult(data: OrderDetailResponse) =
                Mappers.getMapper(OrderMapper::class.java)
                    .orderDetailResponseToModel(data)

            override suspend fun createCall(): Result<OrderDetailResponse> =
                orderRemoteDataSource.getDetail(orderId)


        }.asFlow()

    override fun createOrder(vehicleType: Long, product: String,
                             length: Int?, width: Int?, height: Int?, coli: Int?, weight: Int?,
                             description: String?, originName: String, originCity: String?,
                             originLat: Double, originLng: Double, destinationName: String,
                             destinationCity: String?, destinationLat: Double,
                             destinationLng: Double,
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
                             destinationLng5: Double?) =
        object : NetworkBoundProcessResource<CreateOrderData?, CreateOrderResponse>(
            localDataSource, sessionRemoteDataSource
        ) {
            override suspend fun callBackResult(data: CreateOrderResponse) =
                Mappers.getMapper(OrderMapper::class.java)
                    .createOrderResponseToModel(data)

            override suspend fun createCall(): Result<CreateOrderResponse> {
                val cached = profileLocalDataSource.getCached()
                val profileId = if (cached?.id == null) 0L else cached.id

                val format = SimpleDateFormat("yyyy-MM-dd", Locale("US"))

                val destinations = mutableListOf<CreateOrderDestinationRequest>()

                val distance = SphericalUtil.computeDistanceBetween(LatLng(originLat, originLng),
                    LatLng(destinationLat, destinationLng))/1000.0
                var distance2 = 0.0
                var distance3 = 0.0
                var distance4 = 0.0
                var distance5 = 0.0

                if (destinationLat2 != null && destinationLng2 != null) {
                    distance2 = SphericalUtil.computeDistanceBetween(LatLng(originLat, originLng),
                        LatLng(destinationLat2, destinationLng2))/1000.0
                }

                if (destinationLat3 != null && destinationLng3 != null) {
                    distance3 = SphericalUtil.computeDistanceBetween(LatLng(originLat, originLng),
                        LatLng(destinationLat3, destinationLng3))/1000.0
                }

                if (destinationLat4 != null && destinationLng4 != null) {
                    distance4 = SphericalUtil.computeDistanceBetween(LatLng(originLat, originLng),
                        LatLng(destinationLat4, destinationLng4))/1000.0
                }

                if (destinationLat5 != null && destinationLng5 != null) {
                    distance5 = SphericalUtil.computeDistanceBetween(LatLng(originLat, originLng),
                        LatLng(destinationLat5, destinationLng5))/1000.0
                }

                var maxDistance = distance
                if (maxDistance < distance2) {
                    maxDistance = distance2
                }
                if (maxDistance < distance3) {
                    maxDistance = distance3
                }
                if (maxDistance < distance4) {
                    maxDistance = distance4
                }
                if (maxDistance < distance5) {
                    maxDistance = distance5
                }

                val destination1 = CreateOrderDestinationRequest(
                    originType = "origin",
                    originName = originName,
                    originCity = originCity,
                    originLatLng = "$originLat, $originLng",
                    destinationType = if (distance < maxDistance) "transit" else "destination",
                    destinationName = destinationName,
                    destinationCity = destinationCity,
                    destinationLatLng = "$destinationLat, $destinationLng",
                    distance = distance,
                    description = description ?: ""
                )

                destinations.add(destination1)

                if (destinationLat2 != null && destinationLng2 != null) {
                    val destination2 = CreateOrderDestinationRequest(
                        originType = "transit",
                        originName = originName,
                        originCity = originCity,
                        originLatLng = "$originLat, $originLng",
                        destinationType = if (distance2 < maxDistance) "transit" else "destination",
                        destinationName = destinationName2 ?: "",
                        destinationCity = destinationCity2 ?: "",
                        destinationLatLng = "$destinationLat2, $destinationLng2",
                        distance = distance2,
                        description = description ?: ""
                    )

                    destinations.add(destination2)
                }

                if (destinationLat3 != null && destinationLng3 != null) {
                    val destination3 = CreateOrderDestinationRequest(
                        originType = "transit",
                        originName = originName,
                        originCity = originCity,
                        originLatLng = "$originLat, $originLng",
                        destinationType = if (distance3 < maxDistance) "transit" else "destination",
                        destinationName = destinationName3 ?: "",
                        destinationCity = destinationCity3 ?: "",
                        destinationLatLng = "$destinationLat3, $destinationLng3",
                        distance = distance3,
                        description = description ?: ""
                    )

                    destinations.add(destination3)
                }

                if (destinationLat4 != null && destinationLng4 != null) {
                    val destination4 = CreateOrderDestinationRequest(
                        originType = "transit",
                        originName = originName,
                        originCity = originCity,
                        originLatLng = "$originLat, $originLng",
                        destinationType = if (distance4< maxDistance) "transit" else "destination",
                        destinationName = destinationName4 ?: "",
                        destinationCity = destinationCity4 ?: "",
                        destinationLatLng = "$destinationLat4, $destinationLng4",
                        distance = distance4,
                        description = description ?: ""
                    )

                    destinations.add(destination4)
                }

                if (destinationLat5 != null && destinationLng5 != null) {
                    val destination5 = CreateOrderDestinationRequest(
                        originType = "transit",
                        originName = originName,
                        originCity = originCity,
                        originLatLng = "$originLat, $originLng",
                        destinationType = if (distance5 < maxDistance) "transit" else "destination",
                        destinationName = destinationName5 ?: "",
                        destinationCity = destinationCity5 ?: "",
                        destinationLatLng = "$destinationLat5, $destinationLng5",
                        distance = distance5,
                        description = description ?: ""
                    )

                    destinations.add(destination5)
                }

                createOrderDestinationLocalDataSource.add(CreateOrderAddressEntity(
                    name = destinationName,
                    city = destinationCity,
                    latitude = destinationLat,
                    longitude = destinationLng
                ))

                val result = orderRemoteDataSource.createOrder(customerId = profileId,
                    vehicleType = vehicleType,
                    date = format.format(Date()),
                    product = product,
                    length = length,
                    width = width,
                    height = height,
                    coli = coli,
                    weight = weight,
                    description = description,
                    addresses = destinations)

                orderLocalDataSource.clearAll()
                invoiceLocalDataSource.clearAll()
                paymentLocalDataSource.clearAll()

                if (result.status == Result.Status.SUCCESS) {
                    createOrderDestinationLocalDataSource.add(CreateOrderAddressEntity(
                        name = destinationName,
                        city = destinationCity,
                        latitude = destinationLat,
                        longitude = destinationLng
                    ))
                }

                return result
            }


        }.asFlow()

    override fun getOriginAddressHistory() = flow {
        val cached = createOrderOriginLocalDataSource.getCached()
        emit(Mappers.getMapper(OrderMapper::class.java)
            .createOrderAddressEntityToRequest(cached))
    }

    override fun getDestinationAddressHistory() = flow {
        val cached = createOrderDestinationLocalDataSource.getCached()
        emit(Mappers.getMapper(OrderMapper::class.java)
            .createOrderAddressEntityToRequest(cached))
    }
}