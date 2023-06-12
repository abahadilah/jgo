package edts.base.android.core_domain.repository

import edts.base.android.core_domain.model.OrderData
import edts.base.android.core_domain.model.OrderDetailData
import kotlinx.coroutines.flow.Flow
import id.co.edtslib.data.Result


interface IOrderRepository {
    fun get(isReload: Boolean, status: String = "all"): Flow<Result<List<OrderData>?>>
    fun getDetail(orderId: Long): Flow<Result<OrderDetailData?>>
}