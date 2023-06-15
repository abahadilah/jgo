package edts.base.android.feature_home.home.navigation

import adilahsoft.jgo.android.feature_payment.ui.PaymentFragment
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.fragment.app.FragmentManager
import edts.base.android.core_resource.HomeBaseFragment
import edts.base.android.feature_home.R
import edts.base.android.feature_home.databinding.ViewNavigationBinding
import edts.uco.android.feature_invoice.ui.InvoiceFragment
import edts.uco.android.feature_order.ui.OrderFragment
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
            binding.clOrder.setOnClickListener { selectedIndex = Navigation.Order }
            binding.clInvoice.setOnClickListener { selectedIndex = Navigation.Invoice }
            binding.clProfile.setOnClickListener { selectedIndex = Navigation.Profile }
            binding.clPayment.setOnClickListener { selectedIndex = Navigation.Payment }

            binding.fabAdd.setOnClickListener {
                delegate?.onCreateOrder()
            }
        }
    }

    private fun changeNav(value: Navigation?) {
        when (value) {
            Navigation.Invoice -> changeNav(R.id.clInvoice)
            Navigation.Profile -> changeNav(R.id.clProfile)
            Navigation.Payment -> changeNav(R.id.clPayment)
            else -> changeNav(R.id.clOrder)
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

        val prevSelectedIndex = if (binding.clOrder.isActivated) Navigation.Order else
            if (binding.clInvoice.isActivated) Navigation.Invoice else
                if (binding.clProfile.isActivated) Navigation.Profile  else
                    if (binding.clPayment.isActivated) Navigation.Payment else null

        binding.clOrder.isActivated = R.id.clOrder == res
        binding.clInvoice.isActivated = R.id.clInvoice == res
        binding.clProfile.isActivated = R.id.clProfile == res
        binding.clPayment.isActivated = R.id.clPayment == res

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
                R.id.clInvoice -> InvoiceFragment()
                R.id.clProfile -> ProfileFragment()
                R.id.clPayment -> PaymentFragment()
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