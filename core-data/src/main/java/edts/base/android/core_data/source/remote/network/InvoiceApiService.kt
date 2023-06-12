package edts.base.android.core_data.source.remote.network

import edts.base.android.core_data.source.remote.request.InvoiceDetailRequest
import edts.base.android.core_data.source.remote.request.InvoiceRequest
import edts.base.android.core_data.source.remote.response.InvoiceDetailResponse
import edts.base.android.core_data.source.remote.response.InvoiceResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface InvoiceApiService {
    @POST("/invoices")
    suspend fun get(@Body request: InvoiceRequest): Response<List<InvoiceResponse>>

    @POST("/invoice_line")
    suspend fun getDetail(@Body request: InvoiceDetailRequest): Response<List<InvoiceDetailResponse>>

}