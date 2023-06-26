package edts.base.android.core_domain.usecase

import edts.base.android.core_domain.model.CustomerData
import id.co.edtslib.data.Result
import kotlinx.coroutines.flow.Flow

interface AffiliateUseCase {
    fun getCustomer(isReload: Boolean): Flow<Result<List<CustomerData>?>>
}