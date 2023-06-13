package adilahsoft.jgo.android.feature_payment.ui

import adilahsoft.jgo.android.feature_payment.databinding.AdapterPaymentItemBinding
import adilahsoft.jgo.android.feature_payment.databinding.AdapterPaymentPeriodBinding
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import edts.base.android.core_domain.model.PaymentData
import id.co.edtslib.baserecyclerview2.AdapterData
import id.co.edtslib.baserecyclerview2.BaseRecyclerView2
import id.co.edtslib.baserecyclerview2.BaseRecyclerView2AdapterDelegate
import id.co.edtslib.baserecyclerview2.BaseViewHolder

class PaymentAdapter: BaseRecyclerView2()  {
    override fun createHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<*> {
        return when(viewType) {

            PaymentAdapterType.Period.ordinal -> {
                val binding = AdapterPaymentPeriodBinding.inflate(
                    inflater, parent,
                    false
                )
                PaymentPeriodHolder(binding)
            }
            else -> {
                val binding = AdapterPaymentItemBinding.inflate(
                    inflater, parent,
                    false
                )
                val holder = PaymentHolder(binding)
                holder.delegate =
                    object : BaseRecyclerView2AdapterDelegate<PaymentData> {
                        override fun onClick(
                            t: PaymentData?,
                            position: Int,
                            viewBinding: ViewBinding
                        ) {
                        }

                        override fun onDraw(
                            t: PaymentData?,
                            position: Int,
                            viewBinding: ViewBinding
                        ) {
                        }
                    }

                holder
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<PaymentData>?) {
        val map = mutableMapOf<String?, MutableList<PaymentData>>()
        val total = mutableMapOf<String?, Double>()
        val period = mutableListOf<String?>()
        data?.forEach {
            val t = it.getMonthYear()
            if (map.containsKey(t)) {
                map[t]?.add(it)

                val p = if (total[t] == null) 0.0 else total[t]!!
                val c = if (it.amount == null) 0.0 else it.amount!!
                total[t] = c + p
            }
            else {
                period.add(t)
                total[t] = if (it.amount == null) 0.0 else it.amount!!
                map[t] = mutableListOf()
                map[t]?.add(it)
            }
        }

        list.clear()
        period.forEach {
            list.add(AdapterData(rowType = PaymentAdapterType.Period.ordinal,
                PaymentPeriodData(
                    date = it,
                    total = total[it]
                )))
            map[it]?.forEach { hist ->
                list.add(AdapterData(rowType = PaymentAdapterType.Item.ordinal,
                    hist))
            }
        }

        notifyDataSetChanged()
    }
}