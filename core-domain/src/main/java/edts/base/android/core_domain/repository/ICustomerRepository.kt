package edts.base.android.core_domain.repository

import edts.base.android.core_domain.model.ProfileData
import id.co.edtslib.data.Result
import kotlinx.coroutines.flow.Flow

interface ICustomerRepository {
    fun login(username: String, password: String): Flow<Result<ProfileData?>>
    fun logout(): Flow< Boolean>
}