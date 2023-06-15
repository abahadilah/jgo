package edts.uco.android.feature_order.ui.create

import android.content.Context
import android.view.LayoutInflater
import edts.base.android.core_domain.model.CreateOrderData
import edts.uco.android.feature_order.R
import edts.uco.android.feature_order.databinding.DialogCreateOrderSuccessBinding
import id.co.edtslib.edtsds.popup.Popup

class CreateOrderSuccessDialog(context: Context, createOrderData: CreateOrderData?,
                               callback: () -> Unit) {
    private var popup: Popup? = null
    val binding = DialogCreateOrderSuccessBinding.inflate(LayoutInflater.from(context))

    init {
        binding.bvSubmit.setOnClickListener {
            popup?.cancel()
            callback()
        }

        binding.tvDescription.text = context.getString(R.string.order_success_desc,
            createOrderData?.name)
    }

    fun show() {
        popup = Popup.showFullScreen(binding.root, edts.base.android.core_resource.R.style.FullScreenDialog)
    }
}