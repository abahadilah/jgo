package edts.uco.android.feature_order.ui.status

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import edts.uco.android.feature_order.R
import edts.uco.android.feature_order.databinding.TrayOrderStatusFilterBinding
import edts.base.android.core_data.source.local.OrderStatus
import id.co.edtslib.edtsds.bottom.BottomLayoutDialog
import id.co.edtslib.edtsds.list.checkmenu.RadioButtonListDelegate
import id.co.edtslib.edtsds.list.radiobuttonlist.RadioButtonListView

class OrderStatusFilterTray(private val context: Context, private val init: OrderStatus) {
    var delegate: OrderStatusFilterDelegate? = null

    private var selected: OrderStatus? = null
    private val binding = TrayOrderStatusFilterBinding.inflate(LayoutInflater.from(context))

    fun show() {
        val listView = binding.root.findViewById<RadioButtonListView<OrderStatusFilterData>>(R.id.listView)
        listView.gravity = Gravity.END
        listView.delegate = object : RadioButtonListDelegate<OrderStatusFilterData> {
            override fun onSelected(t: OrderStatusFilterData) {
                selected = t.type
            }
        }

        val list = mutableListOf<OrderStatusFilterData>()
        OrderStatus.values().forEach {
            val e = OrderStatusFilterData(it)
            e.selected = it == init
            if (e.selected) {
                selected = it
            }

            list.add(e)
        }
        listView.data = list

        val tray = BottomLayoutDialog.showTray(context = context,
            title = context.getString(R.string.order_status_1),
            contentView = binding.root)

        binding.bvSubmit.setOnClickListener {
            if (selected != null) {
                tray.close()
                delegate?.onSubmit(selected!!)
            }
        }

    }
}