package edts.base.android.core_data.source.remote

import edts.base.android.core_data.source.remote.network.AffiliateApiService
import edts.base.android.core_data.source.remote.request.*
import id.co.edtslib.data.BaseDataSource

class AffiliateRemoteDataSource(
    private val customerApiService: AffiliateApiService
) : BaseDataSource() {


    suspend fun getCustomer(
        id: Long) =
        getResult {
            customerApiService.getCustomer(
                AffiliateRequest(
                    id = id
                )
            )
        }
}