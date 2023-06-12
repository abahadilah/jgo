package edts.base.android.core_data.source

import edts.base.android.core_data.mapper.OrderMapper
import edts.base.android.core_data.source.local.ProfileLocalDataSource
import edts.base.android.core_data.source.local.OrderLocalDataSource
import edts.base.android.core_data.source.remote.OrderRemoteDataSource
import edts.base.android.core_data.source.remote.response.OrderDetailResponse
import edts.base.android.core_data.source.remote.response.OrderResponse
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

class OrderRepository(private val localDataSource: HttpHeaderLocalSource,
                      private val sessionRemoteDataSource: SessionRemoteDataSource,
                      private val orderRemoteDataSource: OrderRemoteDataSource,
                      private val orderLocalDataSource: OrderLocalDataSource,
                      private val profileLocalDataSource: ProfileLocalDataSource):
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
}