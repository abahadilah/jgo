package edts.base.android.core_domain.usecase

import edts.base.android.core_domain.repository.IConfigurationRepository

class ConfigurationInteractor(private val repository: IConfigurationRepository):
    ConfigurationUseCase {
    override fun skipVersion(version: String?) = repository.skipVersion(version)
    override fun get() = repository.get()
    override fun clearFcmId()  = repository.clearFcmId()
}