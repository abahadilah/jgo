package edts.uco.android.feature_invoice.ui.status

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import edts.uco.android.feature_invoice.R
import edts.uco.android.feature_invoice.databinding.TrayInvoiceStatusFilterBinding
import edts.base.android.core_data.source.local.InvoiceStatus
import id.co.edtslib.edtsds.bottom.BottomLayoutDialog
import id.co.edtslib.edtsds.list.checkmenu.RadioButtonListDelegate
import id.co.edtslib.edtsds.list.radiobuttonlist.RadioButtonListView

class InvoiceStatusFilterTray(private val context: Context, private val init: InvoiceStatus) {
    var delegate: InvoiceStatusFilterDelegate? = null

    private var selected: InvoiceStatus? = null
    private val binding = TrayInvoiceStatusFilterBinding.inflate(LayoutInflater.from(context))

    fun show() {
        val listView = binding.root.findViewById<RadioButtonListView<InvoiceStatusFilterData>>(R.id.listView)
        listView.gravity = Gravity.END
        listView.delegate = object : RadioButtonListDelegate<InvoiceStatusFilterData> {
            override fun onSelected(t: InvoiceStatusFilterData) {
                selected = t.type
            }
        }

        val list = mutableListOf<InvoiceStatusFilterData>()
        InvoiceStatus.values().forEach {
            val e = InvoiceStatusFilterData(it)
            e.selected = it == init
            if (e.selected) {
                selected = it
            }

            list.add(e)
        }
        listView.data = list

        val tray = BottomLayoutDialog.showTray(context = context,
            title = context.getString(R.string.invoice_status_1),
            contentView = binding.root)

        binding.bvSubmit.setOnClickListener {
            if (selected != null) {
                tray.close()
                delegate?.onSubmit(selected!!)
            }
        }

    }
}