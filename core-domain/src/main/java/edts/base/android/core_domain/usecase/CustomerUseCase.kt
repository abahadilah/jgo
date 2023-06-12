package edts.base.android.core_domain.usecase

import edts.base.android.core_domain.model.ProfileData
import kotlinx.coroutines.flow.Flow
import id.co.edtslib.data.Result

interface CustomerUseCase {
    fun login(username: String, password: String): Flow<Result<ProfileData?>>
    fun logout(): Flow<Boolean>
}