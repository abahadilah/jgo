package edts.base.android.core_domain.usecase

import edts.base.android.core_domain.model.ProfileData
import kotlinx.coroutines.flow.Flow

interface ProfileUseCase {
    fun get(): Flow<ProfileData?>
}