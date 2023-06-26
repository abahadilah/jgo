package edts.base.android.core_data.source.remote.network

import edts.base.android.core_data.source.remote.request.*
import edts.base.android.core_data.source.remote.response.*
import retrofit2.Response
import retrofit2.http.*

interface AffiliateApiService {
    @POST("/customer_affiliate")
    suspend fun getCustomer(@Body request: AffiliateRequest): Response<List<CustomerResponse>>
}