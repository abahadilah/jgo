package edts.base.android.core_domain.usecase

import edts.base.android.core_domain.model.CustomerData
import edts.base.android.core_domain.repository.IConfigurationRepository

class ConfigurationInteractor(private val repository: IConfigurationRepository):
    ConfigurationUseCase {
    override fun setOriginAddress(lat: Double?, lng: Double?, name: String?) =
        repository.setOriginAddress(lat, lng, name)
    override fun skipVersion(version: String?) = repository.skipVersion(version)
    override fun get() = repository.get()
    override fun getCustomer() = repository.getCustomer()
    override fun setCustomer(customerData: CustomerData) = repository.setCustomer(customerData)
    override fun clearFcmId()  = repository.clearFcmId()
}