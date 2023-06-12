package edts.base.android.core_domain.usecase

import edts.base.android.core_domain.repository.IInvoiceRepository

class InvoiceInteractor(private val repository: IInvoiceRepository): InvoiceUseCase {
    override fun get(isReload: Boolean, status: String) =
        repository.get(isReload, status)

    override fun getDetail(id: Long) =
        repository.getDetail(id)
}