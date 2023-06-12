package edts.base.android.core_domain.usecase

import edts.base.android.core_domain.repository.IVehicleRepository

class VehicleInteractor(private val repository: IVehicleRepository): VehicleUseCase {
    override fun get(isReload: Boolean) = repository.get(isReload)
    override fun checkPrice(vehicle: Long,
                   originLat: Double, originLng: Double,
                   destination1Lat: Double?, destination1Lng: Double?,
                   destination2Lat: Double?, destination2Lng: Double?,
                   destination3Lat: Double?, destination3Lng: Double?,
                   destination4Lat: Double?, destination4Lng: Double?,
                   destination5Lat: Double?, destination5Lng: Double?) =
        repository.checkPrice(vehicle, originLat, originLng, destination1Lat, destination1Lng,
            destination2Lat, destination2Lng, destination3Lat, destination3Lng, destination4Lat,
            destination4Lng, destination5Lat, destination5Lng)

}