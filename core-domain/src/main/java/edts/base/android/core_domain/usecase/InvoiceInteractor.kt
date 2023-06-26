package edts.base.android.core_domain.usecase

import edts.base.android.core_domain.model.CustomerData
import edts.base.android.core_domain.repository.IInvoiceRepository

class InvoiceInteractor(private val repository: IInvoiceRepository): InvoiceUseCase {
    override fun get(isReload: Boolean, status: String, customer: CustomerData?) =
        repository.get(isReload, status, customer)

    override fun getDetail(id: Long) =
        repository.getDetail(id)

    override fun getPayments(isReload: Boolean, status: String) =
        repository.getPayments(isReload, status)

}