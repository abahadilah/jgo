package edts.base.android.core_data.source.remote

import edts.base.android.core_data.source.remote.network.InvoiceApiService
import edts.base.android.core_data.source.remote.request.InvoiceDetailRequest
import edts.base.android.core_data.source.remote.request.InvoiceRequest
import id.co.edtslib.data.BaseDataSource

class InvoiceRemoteDataSource(
    private val invoiceApiService: InvoiceApiService
) : BaseDataSource() {

    suspend fun get(id: Long, status: String) =
        getResult { invoiceApiService.get(InvoiceRequest(
            partnerId = id,
            status = status
        )) }

    suspend fun getDetail(id: Long) =
        getResult { invoiceApiService.getDetail(InvoiceDetailRequest(
            id = id
        )) }

    suspend fun getPayments(id: Long, status: String) =
        getResult { invoiceApiService.getPayments(InvoiceRequest(
            partnerId = id,
            status = status
        )) }
}