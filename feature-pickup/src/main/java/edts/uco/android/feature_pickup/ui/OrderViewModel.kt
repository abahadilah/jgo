package edts.uco.android.feature_pickup.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import edts.base.android.core_domain.usecase.OrderUseCase
import edts.base.android.core_domain.usecase.ProfileUseCase
import id.co.edtslib.uibase.BaseViewModel

class OrderViewModel(private val profileUseCase: ProfileUseCase,
                     private val orderUseCase: OrderUseCase): BaseViewModel() {
    var filter = MutableLiveData<OrderStatus>()

    fun getProfile() = profileUseCase.getProfile().asLiveData()
    fun getOrder(isReload: Boolean) = orderUseCase.get(isReload,
        if (filter.value?.code() == null) "all" else filter.value!!.code()!!).asLiveData()
}