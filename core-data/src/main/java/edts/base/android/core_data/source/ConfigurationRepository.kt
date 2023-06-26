package edts.base.android.core_data.source

import edts.base.android.core_data.mapper.AffiliateMapper
import edts.base.android.core_data.mapper.ConfigurationMapper
import edts.base.android.core_data.source.local.AddressEntity
import edts.base.android.core_data.source.local.ConfigurationEntity
import edts.base.android.core_data.source.local.ConfigurationLocalDataSource
import edts.base.android.core_data.source.local.CustomerLocalDataSource
import edts.base.android.core_data.source.local.ProfileLocalDataSource
import edts.base.android.core_data.source.remote.AffiliateRemoteDataSource
import edts.base.android.core_domain.repository.IConfigurationRepository
import id.co.edtslib.data.Result
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import org.mapstruct.factory.Mappers

class ConfigurationRepository(private val configurationLocalDataSource: ConfigurationLocalDataSource,
                              private val customerLocalDataSource: CustomerLocalDataSource,
                              private val affiliateRemoteDataSource: AffiliateRemoteDataSource,
                              private val profileLocalDataSource: ProfileLocalDataSource):
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

    override fun getCustomer() = flow {
        var cached = configurationLocalDataSource.getCached()
        if (cached == null) {
            cached = ConfigurationEntity()
        }

        if (cached.customer == null) {
            val customerCached = customerLocalDataSource.getCached()
            if (customerCached?.isNotEmpty() == true) {
                setCustomer(cached)
            }
            else {
                val profile = profileLocalDataSource.getCached()
                if (profile?.id != null) {
                    val response = affiliateRemoteDataSource.getCustomer(id = profile.id)
                    if (response.status == Result.Status.SUCCESS) {
                        setCustomer(cached)
                    }
                }
            }

            configurationLocalDataSource.save(cached)
        }

        emitAll(get())
    }

    private fun setCustomer(cached: ConfigurationEntity) {
        val customerCached = customerLocalDataSource.getCached()
        if (customerCached?.isNotEmpty() == true) {
            val mapper = Mappers.getMapper(AffiliateMapper::class.java)
                .customerEntityToModel(customerCached)
            cached.customer = mapper?.get(0)
        }
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