package edts.base.android.core_domain.usecase

import edts.base.android.core_domain.repository.IAffiliateRepository

class AffiliateInteractor(private val repository: IAffiliateRepository): AffiliateUseCase {
    override fun getCustomer(isReload: Boolean) = repository.getCustomer(isReload)
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
                            province: Long
    ) = repository.addCustomer(name, ktp, mobile, email, lng, lat, username, password, street,
        street2, city, zipcode, province)

    override fun getProvinces(isReload: Boolean) = repository.getProvinces(isReload)
}