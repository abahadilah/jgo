package edts.base.android.core_domain.usecase

import edts.base.android.core_domain.model.ConfigurationData
import kotlinx.coroutines.flow.Flow

interface ConfigurationUseCase {
    fun skipVersion(version: String?): Flow<ConfigurationData?>
    fun get(): Flow<ConfigurationData?>
    fun clearFcmId()

}