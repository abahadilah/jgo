package edts.uco.android.feature_profile

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import edts.uco.android.feature_profile.databinding.ViewProfileMenuBinding

class ProfileMenuView: FrameLayout {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(attrs)
    }

    var title: String? = null
        set(value) {
            field = value
            binding.tvTitle.text = value
        }

    var description: String? = null
        set(value) {
            field = value
            binding.tvSubTitle.text = value
        }

    private val binding = ViewProfileMenuBinding.inflate(LayoutInflater.from(context), this, true)

    private fun init(attrs: AttributeSet?) {
        if (attrs != null) {
            val a = context.theme.obtainStyledAttributes(
                attrs,
                R.styleable.ProfileMenuView,
                0, 0
            )

            title = a.getString(R.styleable.ProfileMenuView_menuTitle)
            description = a.getString(R.styleable.ProfileMenuView_menuDescription)

            a.recycle()
        }
    }
}