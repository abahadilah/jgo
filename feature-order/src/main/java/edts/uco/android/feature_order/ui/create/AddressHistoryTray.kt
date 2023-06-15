package edts.uco.android.feature_order.ui.create

import android.content.Context
import android.view.LayoutInflater
import edts.base.android.core_domain.model.CreateOrderAddressData
import edts.uco.android.feature_map.R
import edts.uco.android.feature_map.databinding.TrayVehicleTypeBinding
import id.co.edtslib.edtsds.bottom.BottomLayoutDialog
import id.co.edtslib.edtsds.list.menu.MenuDelegate
import id.co.edtslib.edtsds.list.menu.MenuListView

class AddressHistoryTray(private val context: Context,
                         private val title: String,
                         private val list: List<CreateOrderAddressData>) {
    var delegate: AddressHistoryDelegate? = null
    var tray: BottomLayoutDialog? = null

    private val binding = TrayVehicleTypeBinding.inflate(LayoutInflater.from(context))

    fun show() {
        val listView = binding.root.findViewById<MenuListView<CreateOrderAddressData>>(R.id.listView)
        listView.delegate = object : MenuDelegate<CreateOrderAddressData> {
            override fun onSelected(t: CreateOrderAddressData) {
                delegate?.onSelected(t)
            }
        }

        listView.data = list

        tray = BottomLayoutDialog.showTray(context = context,
            title = title,
            contentView = binding.root)

    }

    fun close() {
        tray?.close()
    }
}