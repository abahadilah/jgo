package edts.uco.android.feature_map.ui.price

import android.content.Context
import android.view.LayoutInflater
import edts.base.android.core_domain.model.VehicleTypeData
import edts.uco.android.feature_map.R
import edts.uco.android.feature_map.databinding.TrayVehicleTypeBinding
import id.co.edtslib.edtsds.bottom.BottomLayoutDialog
import id.co.edtslib.edtsds.list.menu.MenuDelegate
import id.co.edtslib.edtsds.list.menu.MenuListView

class VehicleTypeTray(private val context: Context, private val list: List<VehicleTypeData>) {
    var delegate: VehicleTypeDelegate? = null
    var tray: BottomLayoutDialog? = null

    private val binding = TrayVehicleTypeBinding.inflate(LayoutInflater.from(context))

    fun show() {
        val listView = binding.root.findViewById<MenuListView<VehicleTypeData>>(R.id.listView)
        listView.delegate = object : MenuDelegate<VehicleTypeData> {
            override fun onSelected(t: VehicleTypeData) {
                delegate?.onSelected(t)
            }
        }

        listView.data = list

        tray = BottomLayoutDialog.showTray(context = context,
            title = context.getString(R.string.price_select_vehicle_type),
            contentView = binding.root)

    }

    fun close() {
        tray?.close()
    }
}