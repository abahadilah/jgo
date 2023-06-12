package edts.base.android.feature_home.home

import android.graphics.Color
import android.os.Build
import android.view.LayoutInflater
import android.view.WindowInsetsController
import android.view.WindowManager
import androidx.core.content.ContextCompat
import edts.base.android.core_resource.HomeBaseFragment
import edts.base.android.core_resource.UcoActivity
import edts.base.android.feature_home.databinding.ActivityHomeBinding
import edts.base.android.feature_home.home.navigation.Navigation
import edts.base.android.feature_home.home.navigation.NavigationDelegate
import edts.uco.android.feature_activity.ui.InvoiceFragment
import edts.uco.android.feature_profile.ProfileFragment
import id.co.edtslib.edtsds.R

class HomeActivity : UcoActivity<ActivityHomeBinding>() {

    //val viewModel: HomeViewModel by viewModel()

    override val bindingInflater: (LayoutInflater) -> ActivityHomeBinding
        get() = ActivityHomeBinding::inflate

    override fun getTrackerPageName(): String? = null
    override fun isHomeActivity() = true

    override fun reload() {
        binding.navigationView.selectedFragment?.reselect()
    }

    override fun setup() {
        setupListener()
        setupView()
        initData()
    }

    private fun setupListener() {
        binding.navigationView.delegate = object : NavigationDelegate {
            override fun unselected(fragment: HomeBaseFragment<*>) {

            }

            override fun reselected(fragment: HomeBaseFragment<*>) {
                setupStatusBar(fragment)
                fragment.reselect()
            }

            override fun selected(fragment: HomeBaseFragment<*>) {
                setupStatusBar(fragment)
            }
        }
    }

    private fun setupView() {
        binding.navigationView.fragmentManager = supportFragmentManager
    }

    private fun initData() {
        binding.navigationView.selectedIndex = Navigation.Order
    }

    override fun canBack() = binding.navigationView.canBack()

    private fun setupStatusBar(fragment: HomeBaseFragment<*>) {
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)

        if (fragment is InvoiceFragment || fragment is ProfileFragment) {
            window.statusBarColor = Color.WHITE
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                window.insetsController?.setSystemBarsAppearance(
                    WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS,
                    WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
                )
            }

        } else {
            window.statusBarColor = ContextCompat.getColor(this, R.color.colorPrimary30)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                window.insetsController?.setSystemBarsAppearance(
                    0,
                    WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
                )
            }
        }
    }
}