package adilahsoft.jgo.android.feature_affiliate

import adilahsoft.jgo.android.feature_affiliate.databinding.ViewFilterBinding
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import edts.base.android.core_domain.model.CustomerData
import edts.base.android.core_resource.base.result.JGoProcessDelegate
import edts.base.android.core_resource.base.result.JGoProcessResult
import id.co.edtslib.data.Result

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

    var selected: CustomerData? = null
        set(value) {
            field = value
            binding.textView.text = selected?.name
        }

    var data: LiveData<Result<List<CustomerData>?>>? = null
    var delegate: FilterDelegate? = null

    init {
        setOnClickListener {
            data?.observe(context as FragmentActivity) {
                JGoProcessResult(fragmentActivity = context as FragmentActivity, result = it,
                    delegate = object : JGoProcessDelegate<List<CustomerData>?> {
                        override fun success(data: List<CustomerData>?) {
                            val tray = FilterTray(context, selected)
                            tray.delegate = delegate
                            tray.show(data)
                        }
                    })
            }
        }
    }

}