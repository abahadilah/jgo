package edts.base.android.core_domain.usecase

import edts.base.android.core_domain.repository.IOrderRepository

class OrderInteractor(private val repository: IOrderRepository): OrderUseCase {
    override fun get(isReload: Boolean, status: String) = repository.get(isReload, status)
    override fun getDetail(orderId: Long) = repository.getDetail(orderId)

}