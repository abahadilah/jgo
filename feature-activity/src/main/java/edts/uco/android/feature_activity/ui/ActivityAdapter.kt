package edts.uco.android.feature_activity.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import edts.base.android.core_domain.model.InvoiceData
import edts.uco.android.feature_activity.databinding.AdapterInvoiceItemBinding
import edts.uco.android.feature_activity.databinding.AdapterInvoicePeriodBinding
import id.co.edtslib.baserecyclerview2.AdapterData
import id.co.edtslib.baserecyclerview2.BaseRecyclerView2
import id.co.edtslib.baserecyclerview2.BaseRecyclerView2AdapterDelegate
import id.co.edtslib.baserecyclerview2.BaseViewHolder

class ActivityAdapter: BaseRecyclerView2()  {
    var delegate: ActivityAdapterDelegate? = null
    override fun createHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<*> {
        return when(viewType) {

            InvoiceAdapterType.Period.ordinal -> {
                val binding = AdapterInvoicePeriodBinding.inflate(
                    inflater, parent,
                    false
                )
                InvoicePeriodHolder(binding)
            }
            else -> {
                val binding = AdapterInvoiceItemBinding.inflate(
                    inflater, parent,
                    false
                )
                val holder = InvoiceHolder(binding)
                holder.delegate =
                    object : BaseRecyclerView2AdapterDelegate<InvoiceData> {
                        override fun onClick(
                            t: InvoiceData?,
                            position: Int,
                            viewBinding: ViewBinding
                        ) {
                            delegate?.onDetail(t)
                        }

                        override fun onDraw(
                            t: InvoiceData?,
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
    fun setData(data: List<InvoiceData>?) {
        val map = mutableMapOf<String?, MutableList<InvoiceData>>()
        val total = mutableMapOf<String?, Double>()
        val period = mutableListOf<String?>()
        data?.forEach {
            val t = it.getMonthYear()
            if (map.containsKey(t)) {
                map[t]?.add(it)

                val p = if (total[t] == null) 0.0 else total[t]!!
                val c = if (it.total == null) 0.0 else it.total!!
                total[t] = c + p
            }
            else {
                period.add(t)
                total[t] = if (it.total == null) 0.0 else it.total!!
                map[t] = mutableListOf()
                map[t]?.add(it)
            }
        }

        list.clear()
        period.forEach {
            list.add(AdapterData(rowType = InvoiceAdapterType.Period.ordinal,
                InvoicePeriodData(
                    date = it,
                    total = total[it]
                )))
            map[it]?.forEach { hist ->
                list.add(AdapterData(rowType = InvoiceAdapterType.Item.ordinal,
                    hist))
            }
        }

        notifyDataSetChanged()
    }
}