package edts.uco.android.feature_order.ui.create

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.widget.addTextChangedListener
import edts.uco.android.feature_order.databinding.ViewDimensionBinding
import java.lang.NumberFormatException

class DimensionView: FrameLayout {
    var delegate: DimensionDelegate? = null

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private val binding = ViewDimensionBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        binding.etLength.addTextChangedListener {
            try {
                val l = it?.toString()?.toInt()
                delegate?.onLengthChanged(l)

            }
            catch (e: NumberFormatException) {
                delegate?.onLengthChanged(null)
            }
        }

        binding.etWidth.addTextChangedListener {
            try {
                val l = it?.toString()?.toInt()
                delegate?.onWidthChanged(l)

            }
            catch (e: NumberFormatException) {
                delegate?.onWidthChanged(null)
            }
        }

        binding.etHeight.addTextChangedListener {
            try {
                val l = it?.toString()?.toInt()
                delegate?.onHeightChanged(l)

            }
            catch (e: NumberFormatException) {
                delegate?.onHeightChanged(null)
            }
        }
    }
}