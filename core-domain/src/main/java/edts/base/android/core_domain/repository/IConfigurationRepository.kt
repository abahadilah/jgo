package edts.base.android.core_domain.repository

import edts.base.android.core_domain.model.ConfigurationData
import kotlinx.coroutines.flow.Flow

interface IConfigurationRepository {
    fun get(): Flow<ConfigurationData?>
    fun skipVersion(version: String?): Flow<ConfigurationData?>
    fun clearFcmId()
}