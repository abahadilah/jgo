package edts.uco.android.feature_invoice.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import edts.base.android.core_domain.model.InvoiceData
import edts.base.android.core_domain.model.InvoiceDetailData
import edts.base.android.core_domain.usecase.InvoiceUseCase
import id.co.edtslib.uibase.BaseViewModel

class InvoiceDetailViewModel(private val invoiceUseCase: InvoiceUseCase): BaseViewModel() {
    var invoice = MutableLiveData<InvoiceData?>()
    var invoiceDetail = MutableLiveData<InvoiceDetailData?>()

    fun getInvoiceDetail() = invoiceUseCase.getDetail(id = invoice.value!!.id).asLiveData()
}