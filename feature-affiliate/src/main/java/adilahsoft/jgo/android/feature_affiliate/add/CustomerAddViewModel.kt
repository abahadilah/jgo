package adilahsoft.jgo.android.feature_affiliate.add

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import com.google.android.libraries.places.api.model.Place
import edts.base.android.core_domain.model.IdNameData
import edts.base.android.core_domain.usecase.AffiliateUseCase
import id.co.edtslib.uibase.BaseViewModel

class CustomerAddViewModel(private val affiliateUseCase: AffiliateUseCase):
    BaseViewModel() {
    var name: String? = null
    var phone: String? = null
    var email: String? = null
    var ktp: String? = null
    var city: String? = null
    var village: String? = null
    var address: String? = null
    var zipCode: String? = null
    var username: String? = null
    var password: String? = null
    var confirmPassword: String? = null

    var place = MutableLiveData<Place?>()
    var province = MutableLiveData<IdNameData?>()

    fun getProvinces() = affiliateUseCase.getProvinces(false).asLiveData()
    fun addCustomer() = affiliateUseCase.addCustomer(
        name = name!!,
        ktp = ktp!!,
        mobile = phone!!,
        email = email!!,
        lng = place.value!!.latLng!!.longitude,
        lat = place.value!!.latLng!!.latitude,
        username = username!!,
        password = password!!,
        street = address!!,
        street2 = village!!,
        city = city!!,
        zipcode = zipCode!!,
        province = province.value!!.id
    ).asLiveData()
}