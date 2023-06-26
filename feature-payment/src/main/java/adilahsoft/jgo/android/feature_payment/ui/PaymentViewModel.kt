package adilahsoft.jgo.android.feature_payment.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import edts.base.android.core_data.source.local.PaymentStatus
import edts.base.android.core_domain.model.CustomerData
import edts.base.android.core_domain.usecase.AffiliateUseCase
import edts.base.android.core_domain.usecase.ConfigurationUseCase
import edts.base.android.core_domain.usecase.InvoiceUseCase
import edts.base.android.core_domain.usecase.ProfileUseCase
import id.co.edtslib.uibase.BaseViewModel

class PaymentViewModel(private val invoiceUseCase: InvoiceUseCase,
                       private val profileUseCase: ProfileUseCase,
                       private val configurationUseCase: ConfigurationUseCase,
                       private val affiliateUseCase: AffiliateUseCase): BaseViewModel() {
    var filter = MutableLiveData<PaymentStatus?>()
    var isReload = false
    var customer = MutableLiveData<CustomerData?>()

    fun getPayments(isReload: Boolean) = invoiceUseCase.getPayments(isReload = isReload,
        status = if (filter.value == null) PaymentStatus.All.code()!! else filter.value!!.code()!!,
        customer = customer.value).asLiveData()
    fun getProfile() = profileUseCase.get().asLiveData()
    fun setCustomer(customerData: CustomerData) = configurationUseCase.setCustomer(customerData).asLiveData()
    fun getCustomer() = configurationUseCase.getCustomer().asLiveData()
    fun getCustomers(isReload: Boolean) = affiliateUseCase.getCustomer(isReload).asLiveData()

}