package adilahsoft.jgo.android.feature_affiliate.list

import adilahsoft.jgo.android.feature_affiliate.databinding.AdapterCustomerBinding
import androidx.core.view.isVisible
import edts.base.android.core_domain.model.CustomerData
import id.co.edtslib.baserecyclerview.BaseRecyclerViewAdapterDelegate
import id.co.edtslib.baserecyclerview.BaseViewHolder

class CustomerHolder(private val binding: AdapterCustomerBinding):
    BaseViewHolder<CustomerData>(binding) {
    override fun setData(
        list: MutableList<CustomerData>,
        position: Int,
        delegate: BaseRecyclerViewAdapterDelegate<CustomerData>?
    ) {
        with(list[position]) {
            binding.tvTitle.text = name

            binding.tvPhone.isVisible = mobile?.isNotEmpty() == true
            binding.tvPhone.text = mobile
            binding.tvPhone.setOnClickListener {
                if (delegate is CustomerDelegate) {
                    delegate.onCall(this)
                }
            }

            binding.tvEmail.isVisible = email?.isNotEmpty() == true
            binding.tvEmail.text = email
            binding.tvEmail.setOnClickListener {
                if (delegate is CustomerDelegate) {
                    delegate.onEmail(this)
                }
            }

            binding.tvAddress.text = address()
            binding.tvAddress.setOnClickListener {
                if (delegate is CustomerDelegate) {
                    delegate.onMap(this)
                }
            }
        }
    }
}