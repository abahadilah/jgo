package adilahsoft.jgo.android.feature_payment.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import edts.base.android.core_domain.usecase.InvoiceUseCase
import id.co.edtslib.uibase.BaseViewModel

class PaymentViewModel(private val invoiceUseCase: InvoiceUseCase): BaseViewModel() {
    var filter = MutableLiveData<PaymentStatus?>()

    fun getPayments(isReload: Boolean) = invoiceUseCase.getPayments(isReload = isReload,
        status = if (filter.value == null) PaymentStatus.All.code()!! else filter.value!!.code()!!).asLiveData()
}