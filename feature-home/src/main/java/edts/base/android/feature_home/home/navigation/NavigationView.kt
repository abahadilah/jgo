package edts.base.android.feature_home.home.navigation

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.fragment.app.FragmentManager
import edts.base.android.core_resource.HomeBaseFragment
import edts.base.android.feature_home.R
import edts.base.android.feature_home.databinding.ViewNavigationBinding
import edts.uco.android.feature_activity.ui.InvoiceFragment
import edts.uco.android.feature_pickup.ui.OrderFragment
import edts.uco.android.feature_profile.ProfileFragment

class NavigationView : FrameLayout {
    lateinit var fragmentManager: FragmentManager
    var selectedFragment: HomeBaseFragment<*>? = null

    var delegate: NavigationDelegate? = null
    var selectedIndex: Navigation? = null
        set(value) {
            if (value != field) {
                field = value
                changeNav(value)
            }
        }

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private val binding: ViewNavigationBinding =
        ViewNavigationBinding.inflate(LayoutInflater.from(context), this, true)

    init {

        if (!isInEditMode) {
            binding.clPickup.setOnClickListener { selectedIndex = Navigation.Order }
            binding.clActivity.setOnClickListener { selectedIndex = Navigation.Activity }
            binding.clProfile.setOnClickListener { selectedIndex = Navigation.Profile }
        }
    }

    private fun changeNav(value: Navigation?) {
        when (value) {
            Navigation.Activity -> changeNav(R.id.clActivity)
            Navigation.Profile -> changeNav(R.id.clProfile)
            else -> changeNav(R.id.clPickup)
        }
    }

    fun canBack(): Boolean {
        if (selectedFragment?.canBack() != true) {
            return false
        }

        if (selectedFragment !is OrderFragment) {
            selectedIndex = Navigation.Order
            return false
        }

        return true
    }

    private fun changeNav(res: Int) {

        val prevSelectedIndex = if (binding.clPickup.isActivated) Navigation.Order else
            if (binding.clActivity.isActivated) Navigation.Activity else
                if (binding.clProfile.isActivated) Navigation.Profile  else null

        binding.clPickup.isActivated = R.id.clPickup == res
        binding.clActivity.isActivated = R.id.clActivity == res
        binding.clProfile.isActivated = R.id.clProfile == res

        val fragmentTransaction = fragmentManager.beginTransaction()
        if (prevSelectedIndex != null) {
            val fragment = fragmentManager.findFragmentByTag(prevSelectedIndex.toString())
            if (fragment != null) {
                fragmentTransaction.hide(fragment)
                delegate?.unselected(fragment as HomeBaseFragment<*>)
            }
        }

        var created = false
        val fragment = fragmentManager.findFragmentByTag(selectedIndex.toString())
        if (fragment != null) {
            selectedFragment = fragment as HomeBaseFragment<*>
            fragmentTransaction.show(fragment)
        } else {
            created = true
            selectedFragment = when (res) {
                R.id.clActivity -> InvoiceFragment()
                R.id.clProfile -> ProfileFragment()
                else -> OrderFragment()
            }

            fragmentTransaction.add(R.id.flContent, selectedFragment!!, selectedIndex.toString())
        }

        try {
            fragmentTransaction.commit()
        } catch (e: IllegalStateException) {
            //fragmentTransaction.commitAllowingStateLoss()
        }

        if (!created) {
            fragmentTransaction.runOnCommit {
                delegate?.reselected(selectedFragment!!)
            }
        } else {
            fragmentTransaction.runOnCommit {
                delegate?.selected(selectedFragment!!)
            }
        }
    }

}