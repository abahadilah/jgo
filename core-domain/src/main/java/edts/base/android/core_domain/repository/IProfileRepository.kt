package edts.base.android.core_domain.repository

import edts.base.android.core_domain.model.ProfileData
import kotlinx.coroutines.flow.Flow

interface IProfileRepository {
    fun get(): Flow<ProfileData?>
}