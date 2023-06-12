package edts.uco.android.feature_pickup.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import edts.base.android.core_domain.model.OrderData
import edts.base.android.core_domain.model.OrderDetailData
import edts.base.android.core_domain.usecase.OrderUseCase
import id.co.edtslib.uibase.BaseViewModel

class OrderDetailViewModel(private val orderUseCase: OrderUseCase): BaseViewModel() {
    var order = MutableLiveData<OrderData?>()
    var orderDetail = MutableLiveData<OrderDetailData?>()

    fun getOrderDetail() = orderUseCase.getDetail(orderId = order.value!!.id).asLiveData()
}