package edts.uco.android.feature_invoice.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import edts.base.android.core_data.source.local.InvoiceStatus
import edts.base.android.core_domain.model.CustomerData
import edts.base.android.core_domain.usecase.ConfigurationUseCase
import edts.base.android.core_domain.usecase.InvoiceUseCase
import edts.base.android.core_domain.usecase.ProfileUseCase
import id.co.edtslib.uibase.BaseViewModel

class InvoiceViewModel(private val invoiceUseCase: InvoiceUseCase,
                       private val profileUseCase: ProfileUseCase,
                       private val configurationUseCase: ConfigurationUseCase): BaseViewModel() {
    var filter = MutableLiveData<InvoiceStatus?>()

    var isReload = false
    var customer = MutableLiveData<CustomerData?>()

    fun getProfile() = profileUseCase.get().asLiveData()
    fun getCustomer() = configurationUseCase.getCustomer().asLiveData()
    fun getInvoice(isReload: Boolean) = invoiceUseCase.get(isReload = isReload,
        status = if (filter.value == null) InvoiceStatus.All.code()!! else filter.value!!.code()!!,
        customer = customer.value).asLiveData()
}