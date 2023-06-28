package edts.base.android.core_data.source.remote

import edts.base.android.core_data.source.remote.network.AffiliateApiService
import edts.base.android.core_data.source.remote.request.*
import id.co.edtslib.data.BaseDataSource

class AffiliateRemoteDataSource(
    private val customerApiService: AffiliateApiService
) : BaseDataSource() {


    suspend fun getCustomer(
        id: Long) =
        getResult {
            customerApiService.getCustomer(
                AffiliateRequest(
                    id = id
                )
            )
        }

    suspend fun addCustomer(
        name: String,
        affiliateId: Long,
        ktp: String,
        mobile: String,
        email: String,
        lng: Double,
        lat: Double,
        username: String,
        password: String,
        street: String,
        city: String,
        zipcode: String,
        province: Long
        ) =
        getResult {
            customerApiService.addCustomer(
                CustomerAddRequest(
                    name = name,
                    affiliateId = affiliateId,
                    ktp = ktp,
                    mobile = mobile,
                    email = email,
                    lng = lng,
                    lat = lat,
                    username = username,
                    password = password,
                    street = street,
                    city = city,
                    zipcode = zipcode,
                    province = province
                )
            )
        }

    suspend fun getProvinces() =
        getResult {
            customerApiService.getProvinces()
        }
}