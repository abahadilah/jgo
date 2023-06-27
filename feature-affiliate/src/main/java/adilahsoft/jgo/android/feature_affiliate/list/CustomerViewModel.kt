package adilahsoft.jgo.android.feature_affiliate.list

import androidx.lifecycle.asLiveData
import edts.base.android.core_domain.usecase.AffiliateUseCase
import id.co.edtslib.uibase.BaseViewModel

class CustomerViewModel(private val useCase: AffiliateUseCase): BaseViewModel() {
    fun get(isReload: Boolean) = useCase.getCustomer(isReload).asLiveData()
}