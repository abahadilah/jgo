package edts.base.android.core_domain.repository

import edts.base.android.core_domain.model.CustomerData
import id.co.edtslib.data.Result
import kotlinx.coroutines.flow.Flow

interface IAffiliateRepository {
    fun getCustomer(isReload: Boolean): Flow<Result<List<CustomerData>?>>
}