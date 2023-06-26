package adilahsoft.jgo.android.feature_affiliate

import adilahsoft.jgo.android.feature_affiliate.databinding.TrayFilterBinding
import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import edts.base.android.core_domain.model.CustomerData
import id.co.edtslib.edtsds.bottom.BottomLayoutDialog
import id.co.edtslib.edtsds.list.checkmenu.RadioButtonListDelegate
import id.co.edtslib.edtsds.list.radiobuttonlist.RadioButtonListView

class FilterTray(private val context: Context, private val init: CustomerData?) {
    var delegate: FilterDelegate? = null

    private var selected: CustomerData? = null
    private val binding = TrayFilterBinding.inflate(LayoutInflater.from(context))

    fun show(data: List<CustomerData>?) {
        val listView = binding.root.findViewById<RadioButtonListView<CustomerData>>(R.id.listView)
        listView.gravity = Gravity.END
        listView.delegate = object : RadioButtonListDelegate<CustomerData> {
            override fun onSelected(t: CustomerData) {
                selected = t
            }
        }
        data?.forEach {
            it.selected = it.id == init?.id
        }
        listView.data = data ?: listOf()

        val tray = BottomLayoutDialog.showTray(context = context,
            title = context.getString(R.string.affiliate_filter),
            contentView = binding.root)

        binding.bvSubmit.setOnClickListener {
            if (selected != null) {
                tray.close()
                delegate?.onSubmit(selected!!)
            }
        }

    }
}