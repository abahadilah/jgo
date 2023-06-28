package edts.base.android.core_data.source

import edts.base.android.core_data.mapper.AffiliateMapper
import edts.base.android.core_data.source.local.ConfigurationEntity
import edts.base.android.core_data.source.local.ConfigurationLocalDataSource
import edts.base.android.core_data.source.local.CustomerLocalDataSource
import edts.base.android.core_data.source.local.ProfileLocalDataSource
import edts.base.android.core_data.source.local.ProvincesLocalDataSource
import edts.base.android.core_data.source.remote.AffiliateRemoteDataSource
import edts.base.android.core_data.source.remote.response.CustomerResponse
import edts.base.android.core_data.source.remote.response.IdNameResponse
import edts.base.android.core_domain.model.CustomerData
import edts.base.android.core_domain.model.IdNameData
import edts.base.android.core_domain.repository.IAffiliateRepository
import id.co.edtslib.data.NetworkBoundGetResource
import id.co.edtslib.data.NetworkBoundProcessResource
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
                          private val configurationLocalDataSource: ConfigurationLocalDataSource,
                          private val provincesLocalDataSource: ProvincesLocalDataSource
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

    override fun addCustomer(name: String,
                             ktp: String,
                             mobile: String,
                             email: String,
                             lng: Double,
                             lat: Double,
                             username: String,
                             password: String,
                             street: String,
                             street2: String,
                             city: String,
                             zipcode: String,
                             province: Long) =
        object : NetworkBoundProcessResource<Boolean?, Any>(
            localDataSource, sessionRemoteDataSource
        ) {
            override suspend fun callBackResult(data: Any) = true

            override suspend fun createCall(): Result<Any> {
                val cached = profileLocalDataSource.getCached()
                val profileId = if (cached?.id == null) 0L else cached.id

                return affiliateRemoteDataSource.addCustomer(
                    name = name,
                    affiliateId = profileId,
                    ktp = ktp,
                    mobile = mobile,
                    email = email,
                    lng = lng,
                    lat = lat,
                    username = username,
                    password = password,
                    street = street,
                    street2 = street2,
                    city = city,
                    zipcode = zipcode,
                    province = province
                )
            }


        }.asFlow()

    override fun getProvinces(isReload: Boolean) =
        object : NetworkBoundGetResource<List<IdNameData>?, List<IdNameResponse>>(
            localDataSource, sessionRemoteDataSource
        ) {
            override suspend fun createCall(): Result<List<IdNameResponse>> =
                affiliateRemoteDataSource.getProvinces()

            override fun getCached() = flow {
                val cached = provincesLocalDataSource.getCached()
                emit(
                    Mappers.getMapper(AffiliateMapper::class.java)
                        .provincesEntityToModel(cached))
            }


            override suspend fun saveCallResult(data: List<IdNameResponse>) =
                provincesLocalDataSource.save(Mappers.getMapper(AffiliateMapper::class.java)
                    .provincesResponseToEntity(data))

            override fun shouldFetch(data: List<IdNameData>?) =
                isReload || data?.isNotEmpty() != true

        }.asFlow()
}
