package edts.uco.android.feature_profile

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import edts.base.android.core_domain.model.ProfileData
import edts.base.android.core_navigation.ModuleNavigator
import edts.base.android.core_resource.HomeBaseFragment
import edts.base.android.core_resource.base.result.UcoProcessDelegate2
import edts.base.android.core_resource.base.result.UcoProcessLoadResult
import edts.base.core_utils.SnackBarUtils
import edts.uco.android.feature_profile.databinding.FragmentProfileBinding
import id.co.edtslib.edtsds.popup.Popup
import id.co.edtslib.edtsds.popup.PopupDelegate
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment: HomeBaseFragment<FragmentProfileBinding>(), ModuleNavigator {
    private val viewModel: ProfileViewModel by viewModel()

    private val changePinResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                SnackBarUtils.showSnackBar(requireContext(), binding.root,
                    getString(R.string.profile_change_pin_success))
            }
        }

    private val changeBusinessDetailResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                SnackBarUtils.showSnackBar(requireContext(), binding.root,
                    getString(R.string.profile_change_business_detail_success))
            }
        }

    private val changeSupplyCommitmentResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                SnackBarUtils.showSnackBar(requireContext(), binding.root,
                    getString(R.string.profile_change_supply_commitment_success))
            }
        }

    private val changeProfileResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                SnackBarUtils.showSnackBar(requireContext(), binding.root,
                    getString(R.string.profile_change_success))
                loadData()
            }
        }

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