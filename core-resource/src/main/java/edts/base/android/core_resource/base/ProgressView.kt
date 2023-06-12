package edts.base.android.core_resource.base

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import edts.base.android.core_resource.databinding.ViewProgressBinding
import id.co.edtslib.edtsds.popup.Popup

class ProgressView {
    companion object  {
        private var popup: Popup? = null

        fun show(context: Context) {
            val binding = ViewProgressBinding.inflate(
                LayoutInflater.from(context),
                null, false)
            if (popup == null) {
                popup = Popup.show(binding.root, width = ViewGroup.LayoutParams.WRAP_CONTENT.toFloat())
            }
        }

        fun close() {
            try {
                popup?.dismiss()
                popup = null
            }
            catch (ignore: Exception) {

            }
        }
    }
}