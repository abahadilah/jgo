package edts.uco.android.feature_order.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import edts.base.android.core_data.source.local.OrderStatus
import edts.base.android.core_domain.model.CustomerData
import edts.base.android.core_domain.usecase.ConfigurationUseCase
import edts.base.android.core_domain.usecase.InvoiceUseCase
import edts.base.android.core_domain.usecase.OrderUseCase
import edts.base.android.core_domain.usecase.ProfileUseCase
import id.co.edtslib.uibase.BaseViewModel

class OrderViewModel(private val profileUseCase: ProfileUseCase,
                     private val orderUseCase: OrderUseCase,
                     private val invoiceUseCase: InvoiceUseCase,
                     private val configurationUseCase: ConfigurationUseCase): BaseViewModel() {
    var filter = MutableLiveData<OrderStatus>()
    var customer = MutableLiveData<CustomerData?>()
    var isReload = false

    fun getProfile() = profileUseCase.get().asLiveData()
    fun getOrder(isReload: Boolean) = orderUseCase.get(isReload,
        status = if (filter.value?.code() == null) "all" else filter.value!!.code()!!,
        customer = customer.value).asLiveData()

    fun getInvoice(id: Long) = invoiceUseCase.getDetail(id).asLiveData()
    fun getCustomer() = configurationUseCase.getCustomer().asLiveData()
}