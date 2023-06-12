package edts.base.android.core_data.source

import edts.base.android.core_data.mapper.VehicleMapper
import edts.base.android.core_data.source.local.VehicleTypeLocalDataSource
import edts.base.android.core_data.source.remote.VehicleRemoteDataSource
import edts.base.android.core_data.source.remote.response.CheckPriceResponse
import edts.base.android.core_data.source.remote.response.VehicleTypeResponse
import edts.base.android.core_domain.model.CheckPriceData
import edts.base.android.core_domain.model.VehicleTypeData
import edts.base.android.core_domain.repository.IVehicleRepository
import id.co.edtslib.data.NetworkBoundGetResource
import id.co.edtslib.data.NetworkBoundProcessResource
import id.co.edtslib.data.Result
import id.co.edtslib.data.source.local.HttpHeaderLocalSource
import id.co.edtslib.data.source.remote.SessionRemoteDataSource
import kotlinx.coroutines.flow.flow
import org.mapstruct.factory.Mappers

class VehicleRepository(private val localDataSource: HttpHeaderLocalSource,
                        private val sessionRemoteDataSource: SessionRemoteDataSource,
                        private val vehicleRemoteDataSource: VehicleRemoteDataSource,
                        private val vehicleTypeLocalDataSource: VehicleTypeLocalDataSource):
    IVehicleRepository {
    override fun get(isReload: Boolean) =
        object : NetworkBoundGetResource<List<VehicleTypeData>?, List<VehicleTypeResponse>>(
            localDataSource, sessionRemoteDataSource
        ) {
            override suspend fun createCall() = vehicleRemoteDataSource.get()

            override fun getCached() = flow {
                val cached = vehicleTypeLocalDataSource.getCached()
                emit(
                    Mappers.getMapper(VehicleMapper::class.java)
                        .vehicleTypeEntityToModel(cached)
                )
            }

            override suspend fun saveCallResult(data: List<VehicleTypeResponse>) {
                data.let {
                    val mapper = Mappers.getMapper(VehicleMapper::class.java)
                        .vehicleTypeResponseToEntity(it)
                    vehicleTypeLocalDataSource.save(mapper)
                }
            }

            override fun shouldFetch(data: List<VehicleTypeData>?) =
                isReload || data?.isNotEmpty() != true

        }.asFlow()

    override fun checkPrice(vehicle: Long,
                            originLat: Double, originLng: Double,
                            destination1Lat: Double?, destination1Lng: Double?,
                            destination2Lat: Double?, destination2Lng: Double?,
                            destination3Lat: Double?, destination3Lng: Double?,
                            destination4Lat: Double?, destination4Lng: Double?,
                            destination5Lat: Double?, destination5Lng: Double?) =
        object : NetworkBoundProcessResource<CheckPriceData?, List<CheckPriceResponse>>(
            localDataSource, sessionRemoteDataSource
        ) {
            override suspend fun callBackResult(data: List<CheckPriceResponse>) =
                Mappers.getMapper(VehicleMapper::class.java)
                    .vehiclePriceResponseToModel(data[0])

            override suspend fun createCall(): Result<List<CheckPriceResponse>> =
                vehicleRemoteDataSource.checkPrice(
                    vehicle = vehicle,
                    origins = "$originLat, $originLng",
                    destinations = "$destination1Lat, $destination1Lng",
                    destinations2 = if (destination2Lng == null || destination2Lat == null) "" else "$destination2Lat, $destination2Lng",
                    destinations3 = if (destination3Lng == null || destination3Lat == null) "" else "$destination3Lat, $destination3Lng",
                    destinations4 = if (destination4Lng == null || destination4Lat == null) "" else "$destination4Lat, $destination4Lng",
                    destinations5 = if (destination5Lng == null || destination5Lat == null) "" else "$destination5Lat, $destination5Lng",

                )


        }.asFlow()
}