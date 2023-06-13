package edts.uco.android.feature_pickup.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import edts.base.android.core_domain.model.OrderData
import edts.uco.android.feature_pickup.databinding.AdapterItemBinding
import edts.uco.android.feature_pickup.databinding.AdapterPeriodBinding
import id.co.edtslib.baserecyclerview2.AdapterData
import id.co.edtslib.baserecyclerview2.BaseRecyclerView2
import id.co.edtslib.baserecyclerview2.BaseRecyclerView2AdapterDelegate
import id.co.edtslib.baserecyclerview2.BaseViewHolder

class OrderAdapter: BaseRecyclerView2()  {
    var delegate: OrderAdapterDelegate? = null
    override fun createHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<*> {
        return when(viewType) {

            OrderAdapterType.Period.ordinal -> {
                val binding = AdapterPeriodBinding.inflate(
                    inflater, parent,
                    false
                )
                OrderPeriodHolder(binding)
            }
            else -> {
                val binding = AdapterItemBinding.inflate(
                    inflater, parent,
                    false
                )
                val holder = OrderItemHolder(binding)
                holder.delegate =
                    object : BaseRecyclerView2AdapterDelegate<OrderData> {
                        override fun onClick(
                            t: OrderData?,
                            position: Int,
                            viewBinding: ViewBinding
                        ) {
                           delegate?.onDetail(t)
                        }

                        override fun onDraw(
                            t: OrderData?,
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
    fun setData(data: List<OrderData>?) {
        val map = mutableMapOf<String?, MutableList<OrderData>>()
        val total = mutableMapOf<String?, Double>()
        val period = mutableListOf<String?>()
        data?.forEach {
            val t = it.getMonthYear()
            if (map.containsKey(t)) {
                map[t]?.add(it)

                val p = if (total[t] == null) 0.0 else total[t]!!
                val c = if (it.totalAmount == null) 0.0 else it.totalAmount!!
                total[t] = c + p
            }
            else {
                period.add(t)

                total[t] = if (it.totalAmount == null) 0.0 else it.totalAmount!!
                map[t] = mutableListOf()
                map[t]?.add(it)
            }
        }

        list.clear()
        period.forEach {

            list.add(AdapterData(rowType = OrderAdapterType.Period.ordinal,
                OrderPeriodData(
                    date = it,
                    total = total[it]
                )))
            map[it]?.forEach { hist ->
                list.add(AdapterData(rowType = OrderAdapterType.Item.ordinal,
                    hist))
            }
        }

        notifyDataSetChanged()
    }
}