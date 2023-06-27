package adilahsoft.jgo.android.feature_affiliate.list

import edts.base.android.core_domain.model.CustomerData
import id.co.edtslib.baserecyclerview.BaseRecyclerViewAdapterDelegate

interface CustomerDelegate: BaseRecyclerViewAdapterDelegate<CustomerData> {
    fun onCall(customerData: CustomerData)
    fun onEmail(customerData: CustomerData)
    fun onMap(customerData: CustomerData)
}