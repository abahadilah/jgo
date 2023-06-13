package edts.uco.android.feature_invoice.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import edts.base.android.core_domain.usecase.InvoiceUseCase
import id.co.edtslib.uibase.BaseViewModel

class InvoiceViewModel(private val invoiceUseCase: InvoiceUseCase): BaseViewModel() {
    var filter = MutableLiveData<InvoiceStatus?>()

    fun getInvoice(isReload: Boolean) = invoiceUseCase.get(isReload = isReload,
        status = if (filter.value == null) InvoiceStatus.All.code()!! else filter.value!!.code()!!).asLiveData()
}