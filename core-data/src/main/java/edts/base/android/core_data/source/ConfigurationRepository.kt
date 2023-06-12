package edts.base.android.core_data.source

import edts.base.android.core_data.mapper.ConfigurationMapper
import edts.base.android.core_data.source.local.AddressEntity
import edts.base.android.core_data.source.local.ConfigurationEntity
import edts.base.android.core_data.source.local.ConfigurationLocalDataSource
import edts.base.android.core_domain.repository.IConfigurationRepository
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import org.mapstruct.factory.Mappers

class ConfigurationRepository(private val configurationLocalDataSource: ConfigurationLocalDataSource):
    IConfigurationRepository {
    override fun get() = flow {
        emit(Mappers.getMapper(ConfigurationMapper::class.java)
            .configurationEntityToModel(configurationLocalDataSource.getCached()))
    }

    override fun setOriginAddress(lat: Double?, lng: Double?, name: String?) = flow {
        var cached = configurationLocalDataSource.getCached()
        if (cached == null) {
            cached = ConfigurationEntity()
        }
        cached.originAddress = AddressEntity(lat, lng, name)

        configurationLocalDataSource.save(cached)

        emitAll(get())
    }

    override fun skipVersion(version: String?) = flow {
        var cached = configurationLocalDataSource.getCached()
        if (cached == null) {
            cached = ConfigurationEntity()
        }
        cached.skipVersion = version

        configurationLocalDataSource.save(cached)

        emitAll(get())
    }

    override fun clearFcmId() {
        var cached = configurationLocalDataSource.getCached()
        if (cached == null) {
            cached = ConfigurationEntity()
        }
        cached.fcmId = null

        configurationLocalDataSource.save(cached)
    }

}