package edts.base.android.core_domain.repository

import edts.base.android.core_domain.model.CustomerData
import edts.base.android.core_domain.model.InvoiceData
import edts.base.android.core_domain.model.InvoiceDetailData
import edts.base.android.core_domain.model.PaymentData
import kotlinx.coroutines.flow.Flow
import id.co.edtslib.data.Result

interface IInvoiceRepository {
    fun get(isReload: Boolean,
            status: String = "all",
            customer: CustomerData? = null): Flow<Result<List<InvoiceData>?>>
    fun getDetail(id: Long): Flow<Result<InvoiceDetailData?>>
    fun getPayments(isReload: Boolean,
                    status: String = "all",
                    customer: CustomerData? = null): Flow<Result<List<PaymentData>?>>

}