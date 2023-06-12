package edts.base.android.feature_auth.ui

import android.view.LayoutInflater
import edts.base.android.core_domain.model.ProfileData
import edts.base.android.core_navigation.ModuleNavigator
import edts.base.android.core_resource.UcoActivity
import edts.base.android.core_resource.base.result.UcoProcessDelegate
import edts.base.android.core_resource.base.result.UcoProcessResult
import edts.base.android.feature_auth.BuildConfig
import edts.base.android.feature_auth.R
import edts.base.android.feature_auth.databinding.ActivityLoginBinding
import edts.base.core_utils.isValidName
import edts.base.core_utils.isValidPassword
import id.co.edtslib.edtsds.textfield.TextFieldDelegate
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity: UcoActivity<ActivityLoginBinding>(), ModuleNavigator {
    private val viewModel: LoginViewModel by viewModel()

    override val bindingInflater: (LayoutInflater) -> ActivityLoginBinding
        get() = ActivityLoginBinding::inflate

    override fun getTrackerPageName(): String? = null
    override fun setup() {
        setupListener()
        setupView()
        loadData()
    }

    private fun loadData() {
        //CheckAppVersion.check(this) {}
        viewModel.getVehicle().observeForever {  }
    }

    override fun canBack() = true

    private fun setupView() {
        binding.tvVersion.text = if (BuildConfig.DEBUG) {
            String.format("%s v%s (%s)", getString(R.string.app_name),
                BuildConfig.VERSION_NAME, BuildConfig.BUILD_NUMBER)
        } else {
            String.format("%s v%s", getString(R.string.app_name), BuildConfig.VERSION_NAME)
        }
    }

    private fun setupListener() {
        binding.etPhone.delegate = object : TextFieldDelegate {
            override fun onChanged(input: String?) {
                binding.etPassword.error = null
                viewModel.username = input
                binding.bvSubmit.isEnabled = input?.isValidName() == true && viewModel.password?.isValidPassword() == true
            }
        }

        binding.etPassword.delegate = object : TextFieldDelegate {
            override fun onChanged(input: String?) {
                binding.etPassword.error = null
                viewModel.password = input
                binding.bvSubmit.isEnabled = input?.isValidName() == true && viewModel.username?.isValidName() == true
            }
        }

        binding.bvSubmit.setOnClickListener {
            viewModel.login().observe(this) {
                UcoProcessResult(fragmentActivity = this, result = it,
                    object : UcoProcessDelegate<ProfileData?> {
                        override fun success(data: ProfileData?) {
                            if (data?.id == null) {
                                binding.etPassword.error = getString(R.string.auth_phone_error)
                            }
                            else {
                                navigateToHomeActivity()
                            }
                        }

                    })
            }

        }

        binding.tvPriceCheck.setOnClickListener {
            navigateToCheckPrice()
        }
    }
}