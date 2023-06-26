package edts.base.android.core_data.source

import edts.base.android.core_data.mapper.AffiliateMapper
import edts.base.android.core_data.source.local.ConfigurationEntity
import edts.base.android.core_data.source.local.ConfigurationLocalDataSource
import edts.base.android.core_data.source.local.CustomerLocalDataSource
import edts.base.android.core_data.source.local.ProfileLocalDataSource
import edts.base.android.core_data.source.remote.AffiliateRemoteDataSource
import edts.base.android.core_data.source.remote.response.CustomerResponse
import edts.base.android.core_domain.model.CustomerData
import edts.base.android.core_domain.repository.IAffiliateRepository
import id.co.edtslib.data.NetworkBoundGetResource
import id.co.edtslib.data.Result
import id.co.edtslib.data.source.local.HttpHeaderLocalSource
import id.co.edtslib.data.source.remote.SessionRemoteDataSource
import kotlinx.coroutines.flow.flow
import org.mapstruct.factory.Mappers

class AffiliateRepository(private val profileLocalDataSource: ProfileLocalDataSource,
                          private val affiliateRemoteDataSource: AffiliateRemoteDataSource,
                          private val localDataSource: HttpHeaderLocalSource,
                          private val sessionRemoteDataSource: SessionRemoteDataSource,
                          private val customerLocalDataSource: CustomerLocalDataSource,
                          private val configurationLocalDataSource: ConfigurationLocalDataSource
):
    IAffiliateRepository {

    override fun getCustomer(isReload: Boolean) =
        object : NetworkBoundGetResource<List<CustomerData>?, List<CustomerResponse>>(
            localDataSource, sessionRemoteDataSource
        ) {
            override suspend fun createCall(): Result<List<CustomerResponse>> {
                val cached = profileLocalDataSource.getCached()

                return affiliateRemoteDataSource.getCustomer(id = if (cached?.id == null) 0 else cached.id)
            }

            override fun getCached() = flow {
                val cached = customerLocalDataSource.getCached()
                emit(
                    Mappers.getMapper(AffiliateMapper::class.java)
                    .customerEntityToModel(cached))
            }


            override suspend fun saveCallResult(data: List<CustomerResponse>) {
                val mapper = Mappers.getMapper(AffiliateMapper::class.java)
                    .customerResponseToEntity(data)

                if (mapper?.isNotEmpty() == true) {
                    var cached = configurationLocalDataSource.getCached()
                    if (cached == null) {
                        cached = ConfigurationEntity()
                    }

                    val mapper1 = Mappers.getMapper(AffiliateMapper::class.java)
                        .customerEntityToModel(mapper)


                    val f = mapper1?.find { it.id == cached.customer?.id }
                    if (f == null) {
                        cached.customer = mapper1?.get(0)
                        configurationLocalDataSource.save(cached)
                    }
                }

                customerLocalDataSource.save(mapper)
            }

            override fun shouldFetch(data: List<CustomerData>?) =
                isReload || data?.isNotEmpty() != true

        }.asFlow()
}
