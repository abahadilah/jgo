package edts.base.android

import android.content.Intent
import android.view.LayoutInflater
import com.base.android.databinding.ActivityDeeplinkBinding
import edts.base.android.core_navigation.ModuleNavigator
import edts.base.android.feature_home.splash.SplashActivity
import id.co.edtslib.uibase.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class DeeplinkActivity: BaseActivity<ActivityDeeplinkBinding>(), ModuleNavigator {
    private val viewModel: DeeplinkViewModel by viewModel()

    override val bindingInflater: (LayoutInflater) -> ActivityDeeplinkBinding
        get() = ActivityDeeplinkBinding::inflate

    override fun getTrackerPageName(): String? = null

    override fun setup() {
        val uri = intent?.data
        val isSessionExpired = uri?.getQueryParameter("isSessionExpired")
        if (isSessionExpired?.isNotEmpty() == true) {
            viewModel.logout().observe(this) {
                openSplash()
            }
        }
        else {
            openSplash()
        }
    }
    private fun openSplash() {
        finishAffinity()
        val i = Intent(this, SplashActivity::class.java)
        startActivity(i)
    }

}