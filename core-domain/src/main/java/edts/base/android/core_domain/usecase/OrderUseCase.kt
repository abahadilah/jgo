package edts.base.android.core_domain.usecase

import edts.base.android.core_domain.model.OrderData
import edts.base.android.core_domain.model.OrderDetailData
import id.co.edtslib.data.Result
import kotlinx.coroutines.flow.Flow

interface OrderUseCase {
    fun get(isReload: Boolean, status: String = "all"): Flow<Result<List<OrderData>?>>
    fun getDetail(orderId: Long): Flow<Result<OrderDetailData?>>
}