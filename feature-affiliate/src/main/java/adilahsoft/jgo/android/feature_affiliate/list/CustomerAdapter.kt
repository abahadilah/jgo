package adilahsoft.jgo.android.feature_affiliate.list

import adilahsoft.jgo.android.feature_affiliate.databinding.AdapterCustomerBinding
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import edts.base.android.core_domain.model.CustomerData
import id.co.edtslib.baserecyclerview.BaseRecyclerViewAdapter

class CustomerAdapter: BaseRecyclerViewAdapter<AdapterCustomerBinding, CustomerData>() {
    var data: List<CustomerData>? = null
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value

            list.clear()
            if (value != null) {
                list.addAll(value)
            }

            notifyDataSetChanged()
        }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> AdapterCustomerBinding
        get() = AdapterCustomerBinding::inflate

    override fun createHolder() = CustomerHolder(binding)
}