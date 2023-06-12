package edts.base.android.core_data.source.remote

import edts.base.android.core_data.source.remote.network.LocationApiService
import edts.base.android.core_data.source.remote.request.SearchLocationRequest
import id.co.edtslib.data.BaseDataSource

class LocationRemoteDataSource(
    private val locationApiService: LocationApiService
) : BaseDataSource() {

    suspend fun searchLocation(keyword: String) =
        getResult { locationApiService.searchLocation(SearchLocationRequest(location = keyword)) }

    suspend fun geoReverse(lat: Double, lng: Double) =
        getResult { locationApiService.geoReverse(lat, lng) }

}