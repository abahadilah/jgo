package edts.uco.android.feature_order.ui.create.customer

import edts.base.android.core_domain.model.CustomerData

interface CustomerDelegate {
    fun onSelected(item: CustomerData)
}