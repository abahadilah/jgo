package edts.base.android.feature_home.splash

import android.annotation.SuppressLint
import android.os.Build
import android.view.LayoutInflater
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.gms.tasks.Task
import com.google.firebase.messaging.FirebaseMessaging
import edts.base.android.core_domain.model.ProfileData
import edts.base.android.core_navigation.ModuleNavigator
import edts.base.android.feature_home.databinding.ActivitySplashBinding
import id.co.edtslib.data.ProcessResult
import id.co.edtslib.data.ProcessResultDelegate
import id.co.edtslib.uibase.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

@SuppressLint("CustomSplashScreen")
class SplashActivity : BaseActivity<ActivitySplashBinding>(), ModuleNavigator {
    private val viewModel: SplashViewModel by viewModel()

    private val pushNotificationPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()) {
        checkProfile()
    }

    override val bindingInflater: (LayoutInflater) -> ActivitySplashBinding
        get() = ActivitySplashBinding::inflate

    override fun getTrackerPageName(): String? = null

    override fun setup() {
        loadData()

    }

    private fun loadData() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            pushNotificationPermissionLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS)
        }
        else {
            checkProfile()
        }
    }

    private fun checkProfile() {
        viewModel.getProfile().observe(this) {
            if (it?.id != null) {
                navigateToHome()
            }
            else {
                navigateToLogin()
            }
        }
    }

    private fun navigateToLogin() {
        navigateToLoginActivity()
        finish()
    }

    private fun navigateToHome() {
        navigateToHomeActivity()
        finish()
    }

    private fun getToken(onCompleteListener: (Task<String>?) -> Unit) {
        viewModel.getConfiguration().observe(this) {configuration->
            if (configuration?.fcmId?.isNotEmpty() != true) {
                try {
                    FirebaseMessaging.getInstance().token.addOnCompleteListener {
                        onCompleteListener(it)
                    }
                }
                catch (ignore: Exception) {

                }
            }
            else {
                onCompleteListener(null)
            }
        }
    }
}