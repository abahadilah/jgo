package edts.uco.android.feature_order.ui.create

import edts.base.android.core_domain.model.CreateOrderAddressData

interface AddressHistoryDelegate {
    fun onSelected(t: CreateOrderAddressData)
}