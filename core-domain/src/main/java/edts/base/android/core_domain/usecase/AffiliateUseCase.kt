package edts.base.android.core_domain.usecase

import edts.base.android.core_domain.model.CustomerData
import edts.base.android.core_domain.model.IdNameData
import id.co.edtslib.data.Result
import kotlinx.coroutines.flow.Flow

interface AffiliateUseCase {
    fun getCustomer(isReload: Boolean): Flow<Result<List<CustomerData>?>>
    fun addCustomer(name: String,
                    ktp: String,
                    mobile: String,
                    email: String,
                    lng: Double,
                    lat: Double,
                    username: String,
                    password: String,
                    street: String,
                    street2: String,
                    city: String,
                    zipcode: String,
                    province: Long
    ): Flow<Result<Any?>>

    fun getProvinces(isReload: Boolean): Flow<Result<List<IdNameData>?>>
}