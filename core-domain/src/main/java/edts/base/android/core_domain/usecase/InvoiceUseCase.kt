package edts.base.android.core_domain.usecase

import edts.base.android.core_domain.model.InvoiceData
import edts.base.android.core_domain.model.InvoiceDetailData
import kotlinx.coroutines.flow.Flow
import id.co.edtslib.data.Result

interface InvoiceUseCase {
    fun get(isReload: Boolean, status: String = "all"): Flow<Result<List<InvoiceData>?>>
    fun getDetail(id: Long): Flow<Result<InvoiceDetailData?>>

}