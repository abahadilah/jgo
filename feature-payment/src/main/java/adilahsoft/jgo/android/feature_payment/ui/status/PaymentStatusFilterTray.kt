package adilahsoft.jgo.android.feature_payment.ui.status

import adilahsoft.jgo.android.feature_payment.R
import adilahsoft.jgo.android.feature_payment.databinding.TrayPaymentStatusFilterBinding
import adilahsoft.jgo.android.feature_payment.ui.PaymentStatus
import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import id.co.edtslib.edtsds.bottom.BottomLayoutDialog
import id.co.edtslib.edtsds.list.checkmenu.RadioButtonListDelegate
import id.co.edtslib.edtsds.list.radiobuttonlist.RadioButtonListView

class PaymentStatusFilterTray(private val context: Context, private val init: PaymentStatus) {
    var delegate: PaymentStatusFilterDelegate? = null

    private var selected: PaymentStatus? = null
    private val binding = TrayPaymentStatusFilterBinding.inflate(LayoutInflater.from(context))

    fun show() {
        val listView = binding.root.findViewById<RadioButtonListView<PaymentStatusFilterData>>(R.id.listView)
        listView.gravity = Gravity.END
        listView.delegate = object : RadioButtonListDelegate<PaymentStatusFilterData> {
            override fun onSelected(t: PaymentStatusFilterData) {
                selected = t.type
            }
        }

        val list = mutableListOf<PaymentStatusFilterData>()
        PaymentStatus.values().forEach {
            val e = PaymentStatusFilterData(it)
            e.selected = it == init
            if (e.selected) {
                selected = it
            }

            list.add(e)
        }
        listView.data = list

        val tray = BottomLayoutDialog.showTray(context = context,
            title = context.getString(R.string.payment_status_1),
            contentView = binding.root)

        binding.bvSubmit.setOnClickListener {
            if (selected != null) {
                tray.close()
                delegate?.onSubmit(selected!!)
            }
        }

    }
}