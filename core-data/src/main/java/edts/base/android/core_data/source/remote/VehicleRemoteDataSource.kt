package edts.base.android.core_data.source.remote

import edts.base.android.core_data.source.remote.network.VehicleApiService
import edts.base.android.core_data.source.remote.request.CheckPriceRequest
import edts.base.android.core_data.source.remote.request.InvoiceDetailRequest
import id.co.edtslib.data.BaseDataSource

class VehicleRemoteDataSource(
    private val vehicleApiService: VehicleApiService
) : BaseDataSource() {

    suspend fun get() =
        getResult {
            vehicleApiService.get()
        }

    suspend fun checkPrice(vehicle: Long,
                          origins: String,
                          destinations: String,
                          destinations2: String,
                          destinations3: String,
                          destinations4: String,
                          destinations5: String) =
        getResult { vehicleApiService.checkPrice(
            CheckPriceRequest(
                vehicle = vehicle,
                origins = origins,
                destinations = destinations,
                destinations2 = destinations2,
                destinations3 = destinations3,
                destinations4 = destinations4,
                destinations5 = destinations5

        )
        ) }
}