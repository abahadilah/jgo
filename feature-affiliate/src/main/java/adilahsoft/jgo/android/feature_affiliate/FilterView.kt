package adilahsoft.jgo.android.feature_affiliate

import adilahsoft.jgo.android.feature_affiliate.databinding.ViewFilterBinding
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import edts.base.android.core_domain.model.CustomerData

class FilterView: FrameLayout {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private val binding = ViewFilterBinding.inflate(LayoutInflater.from(context),
        this, true)

    var data: CustomerData? = null
        set(value) {
            field = value
            binding.textView.text = data?.name
        }

}