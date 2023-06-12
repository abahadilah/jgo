package edts.base.android.core_resource

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import edts.base.android.core_domain.model.PickupRequestStatus
import edts.base.android.core_resource.databinding.ViewStatusBinding

class SaleRequestStatusView: FrameLayout {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private val binding = ViewStatusBinding.inflate(LayoutInflater.from(context), this, true)

    var status: PickupRequestStatus? = null
        set(value) {
            field = value

            binding.tvStatus.text = value?.toString()
            if (value != null) {
                binding.tvStatus.setTextColor(Color.parseColor(value.textColor()))
                binding.mcvStatus.setCardBackgroundColor(Color.parseColor(value.bgColor()))
            }
        }

}