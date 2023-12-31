package edts.base.android.core_domain.usecase

import edts.base.android.core_domain.model.ConfigurationData
import edts.base.android.core_domain.model.CustomerData
import kotlinx.coroutines.flow.Flow

interface ConfigurationUseCase {
    fun setOriginAddress(lat: Double?, lng: Double?, name: String?): Flow<ConfigurationData?>
    fun skipVersion(version: String?): Flow<ConfigurationData?>
    fun get(): Flow<ConfigurationData?>
    fun getCustomer(): Flow<ConfigurationData?>
    fun setCustomer(customerData: CustomerData): Flow<ConfigurationData?>
    fun clearFcmId()
}