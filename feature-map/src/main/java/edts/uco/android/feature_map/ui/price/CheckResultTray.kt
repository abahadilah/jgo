package edts.uco.android.feature_map.ui.price

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import androidx.core.view.isVisible
import com.google.android.libraries.places.api.model.Place
import edts.base.android.core_domain.model.CheckPriceData
import edts.base.android.core_domain.model.VehicleTypeData
import edts.base.core_utils.formatMoney
import edts.uco.android.feature_map.R
import edts.uco.android.feature_map.databinding.TrayCheckPriceResultBinding
import id.co.edtslib.edtsds.bottom.BottomLayoutDialog

class CheckResultTray(private val context: Context,
                      private val vehicle: VehicleTypeData?,
                      private val originAddress: Place?,
                      private val destinationAddress1: Place?,
                      private val destinationAddress2: Place?,
                      private val destinationAddress3: Place?,
                      private val destinationAddress4: Place?,
                      private val destinationAddress5: Place?,
                      private val price: CheckPriceData?) {
    var delegate: VehicleTypeDelegate? = null
    var tray: BottomLayoutDialog? = null

    private val binding = TrayCheckPriceResultBinding.inflate(LayoutInflater.from(context))

    init {
        binding.bvSubmit.setOnClickListener {
            tray?.close()
        }
    }

    @SuppressLint("SetTextI18n")
    fun show() {
        tray = BottomLayoutDialog.showTray(context = context,
            title = context.getString(R.string.price_check),
            contentView = binding.root)

        binding.tvVehicleType.text = vehicle?.toString()

        binding.tvPrice.text = price?.cost?.formatMoney()
        binding.tvDuration.text = price?.duration
        binding.tvDistance.text = price?.km

        binding.tvOriginAddress.text = originAddress?.name
        binding.tvDestinationAddress1.text = destinationAddress1?.name
        binding.tvDestinationAddress2.text = destinationAddress2?.name
        binding.tvDestinationAddress3.text = destinationAddress3?.name
        binding.tvDestinationAddress4.text = destinationAddress4?.name
        binding.tvDestinationAddress5.text = destinationAddress5?.name

        binding.tvDestinationAddress2.isVisible = destinationAddress2 != null
        binding.tvDestinationAddress3.isVisible = destinationAddress3 != null
        binding.tvDestinationAddress4.isVisible = destinationAddress4 != null
        binding.tvDestinationAddress5.isVisible = destinationAddress5 != null

        binding.lbDestinationAddress2.isVisible = destinationAddress2 != null
        binding.lbDestinationAddress3.isVisible = destinationAddress3 != null
        binding.lbDestinationAddress4.isVisible = destinationAddress4 != null
        binding.lbDestinationAddress5.isVisible = destinationAddress5 != null

    }
}