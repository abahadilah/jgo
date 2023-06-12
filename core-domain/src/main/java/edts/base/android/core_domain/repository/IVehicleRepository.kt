package edts.base.android.core_domain.repository

import edts.base.android.core_domain.model.CheckPriceData
import edts.base.android.core_domain.model.VehicleTypeData
import kotlinx.coroutines.flow.Flow
import id.co.edtslib.data.Result

interface IVehicleRepository {
    fun get(isReload: Boolean): Flow<Result<List<VehicleTypeData>?>>
    fun checkPrice(vehicle: Long,
                   originLat: Double, originLng: Double,
                   destination1Lat: Double?, destination1Lng: Double?,
                   destination2Lat: Double?, destination2Lng: Double?,
                   destination3Lat: Double?, destination3Lng: Double?,
                   destination4Lat: Double?, destination4Lng: Double?,
                   destination5Lat: Double?, destination5Lng: Double?): Flow<Result<CheckPriceData?>>
}