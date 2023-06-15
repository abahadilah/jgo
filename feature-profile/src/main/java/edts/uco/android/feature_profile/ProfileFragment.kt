package edts.uco.android.feature_profile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import edts.base.android.core_navigation.ModuleNavigator
import edts.base.android.core_resource.HomeBaseFragment
import edts.uco.android.feature_profile.databinding.FragmentProfileBinding
import id.co.edtslib.edtsds.popup.Popup
import id.co.edtslib.edtsds.popup.PopupDelegate
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment: HomeBaseFragment<FragmentProfileBinding>(), ModuleNavigator {
    private val viewModel: ProfileViewModel by viewModel()

    override fun reselect() {
    }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentProfileBinding
        get() = FragmentProfileBinding::inflate

    override fun getTrackerPageName(): String?  = null

    override fun setup() {
        setupListener()
        loadData()
    }

    private fun setupListener() {
        binding.llProfile.setOnClickListener {
        }

        binding.menuChangePin.setOnClickListener {
        }

        binding.tvLogout.setOnClickListener {
            confirmLogout()
        }
    }
    private fun loadData() {
        getProfile()
    }

    private fun getProfile() {
        viewModel.get().observe(this) {
            binding.tvName.text = it?.name
            binding.tvPhone.text = null
        }
    }

    private fun confirmLogout() {
        Popup.show(
            activity = requireActivity(), title = getString(R.string.profile_logout_confirm),
            message = getString(R.string.profile_logout_confirm_question),
            positiveButton = getString(edts.base.android.core_resource.R.string.quit),
            negativeButton = getString(edts.base.android.core_resource.R.string.cancel),
            positiveClickListener = object : PopupDelegate {
                override fun onClick(popup: Popup, view: View) {
                    popup.dismiss()
                    logout()
                }
            },
            negativeClickListener =  null,
            orientation = Popup.Orientation.Vertical
        )
    }

    private fun logout() {
        viewModel.logout().observe(this) {
            navigateToLoginActivity()
        }
    }
}