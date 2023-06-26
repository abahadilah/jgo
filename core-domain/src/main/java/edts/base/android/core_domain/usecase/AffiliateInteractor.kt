package edts.base.android.core_domain.usecase

import edts.base.android.core_domain.repository.IAffiliateRepository

class AffiliateInteractor(private val repository: IAffiliateRepository): AffiliateUseCase {
    override fun getCustomer(isReload: Boolean) = repository.getCustomer(isReload)
}