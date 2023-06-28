package adilahsoft.jgo.android.feature_affiliate.add

import adilahsoft.jgo.android.feature_affiliate.R
import adilahsoft.jgo.android.feature_affiliate.add.province.ProvinceDelegate
import adilahsoft.jgo.android.feature_affiliate.add.province.ProvinceListTray
import adilahsoft.jgo.android.feature_affiliate.databinding.ActivityAddCustomerBinding
import android.view.LayoutInflater
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.Autocomplete
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode
import edts.base.android.core_domain.model.IdNameData
import edts.base.android.core_navigation.ModuleNavigator
import edts.base.android.core_resource.base.result.JGoProcessDelegate
import edts.base.android.core_resource.base.result.JGoProcessResult
import edts.base.core_utils.isValidEmail
import edts.base.core_utils.isValidKtp
import edts.base.core_utils.isValidPassword
import edts.base.core_utils.isValidPhone
import edts.uco.android.feature_map.BuildConfig
import id.co.edtslib.edtsds.textfield.TextFieldDelegate
import id.co.edtslib.uibase.PopupActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.Locale

class CustomerAddActivity: PopupActivity<ActivityAddCustomerBinding>(), ModuleNavigator {
    private val viewModel: CustomerAddViewModel by viewModel()

    private val pinPointAutocomplete = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult(),
        ActivityResultCallback { result: ActivityResult ->
            if (result.resultCode == RESULT_OK) {
                val intent = result.data
                if (intent != null) {
                    val place = Autocomplete.getPlaceFromIntent(intent)
                    viewModel.place.postValue(place)
                }
            }
        } as ActivityResultCallback<ActivityResult>)

    override val bindingInflater: (LayoutInflater) -> ActivityAddCustomerBinding
        get() = ActivityAddCustomerBinding::inflate

    override fun getTrackerPageName(): String?  = null

    override fun setupPopup() {
        if (!Places.isInitialized()) {
            Places.initialize(this, BuildConfig.MAPS_KEY,
                Locale("ID")
            )
        }

        setupObserver()
        setupView()
        setupListener()
        loadData()
    }

    private fun loadData() {
        viewModel.getProvinces().observeForever {  }
    }

    private fun setupView() {
        binding.ivPinPoint.isVisible = false
    }

    private fun setupObserver() {
        viewModel.place.observe(this) {
            binding.etLatLng.text = it?.name
            binding.ivPinPoint.isVisible = true
            binding.ivPinPoint.setOnClickListener { _ ->
                navigateToMapActivity(it?.latLng?.latitude, it?.latLng?.longitude)
            }
        }

        viewModel.province.observe(this) {
            binding.etProvince.text = it?.toString()
        }
    }

    private fun setupListener() {
        binding.etName.delegate = object : TextFieldDelegate {
            override fun onChanged(input: String?) {
                viewModel.name = input
                binding.etName.error = null
            }
        }

        binding.etPhone.delegate = object : TextFieldDelegate {
            override fun onChanged(input: String?) {
                viewModel.phone = input
                binding.etPhone.error = null
            }
        }

        binding.etEmail.delegate = object : TextFieldDelegate {
            override fun onChanged(input: String?) {
                viewModel.email = input
                binding.etEmail.error = null
            }
        }

        binding.etKtp.delegate = object : TextFieldDelegate {
            override fun onChanged(input: String?) {
                viewModel.ktp = input
                binding.etKtp.error = null
            }
        }

        binding.etCity.delegate = object : TextFieldDelegate {
            override fun onChanged(input: String?) {
                viewModel.city = input
                binding.etCity.error = null
            }
        }

        binding.etVillage.delegate = object : TextFieldDelegate {
            override fun onChanged(input: String?) {
                viewModel.village = input
                binding.etVillage.error = null
            }
        }


        binding.etAddress.delegate = object : TextFieldDelegate {
            override fun onChanged(input: String?) {
                viewModel.address = input
                binding.etAddress.error = null
            }
        }

        binding.etZip.delegate = object : TextFieldDelegate {
            override fun onChanged(input: String?) {
                viewModel.zipCode = input
                binding.etZip.error = null
            }
        }

        binding.etUsername.delegate = object : TextFieldDelegate {
            override fun onChanged(input: String?) {
                viewModel.username = input
                binding.etUsername.error = null
            }
        }

        binding.etPassword.delegate = object : TextFieldDelegate {
            override fun onChanged(input: String?) {
                viewModel.password = input
                binding.etPassword.error = null
            }
        }

        binding.etConfirmPassword.delegate = object : TextFieldDelegate {
            override fun onChanged(input: String?) {
                viewModel.confirmPassword = input
                binding.etConfirmPassword.error = null
            }
        }

        binding.etLatLng.delegate = object : TextFieldDelegate {
            override fun onChanged(input: String?) {
                startAutocompleteIntent()
            }
        }

        binding.etProvince.delegate = object : TextFieldDelegate {
            override fun onChanged(input: String?) {
                showProvinceTray()
            }
        }

        binding.bvSubmit.setOnClickListener {
            submit()
        }
    }

    private fun submit() {
        var error = false
        if (viewModel.name?.trim()?.isNotEmpty() != true) {
            binding.etName.error = getString(R.string.affiliate_name_empty)
            error = true
        }

        if (viewModel.phone?.trim()?.isNotEmpty() != true) {
            binding.etPhone.error = getString(R.string.affiliate_phone_empty)
            error = true
        }
        else
            if (viewModel.phone?.isValidPhone() != true) {
                binding.etPhone.error = getString(R.string.affiliate_phone_invalid)
                error = true
            }

        if (viewModel.email?.trim()?.isNotEmpty() != true) {
            binding.etEmail.error = getString(R.string.affiliate_email_empty)
            error = true
        }
        else
            if (viewModel.email?.isValidEmail() != true) {
                binding.etEmail.error = getString(R.string.affiliate_email_invalid)
                error = true
            }

        if (viewModel.ktp?.trim()?.isNotEmpty() != true) {
            binding.etKtp.error = getString(R.string.affiliate_ktp_empty)
            error = true
        }
        else
            if (viewModel.ktp?.isValidKtp() != true) {
                binding.etKtp.error = getString(R.string.affiliate_ktp_invalid)
                error = true
            }

        if (viewModel.place.value == null) {
            binding.etLatLng.error = getString(R.string.affiliate_pin_point_empty)
            error = true
        }

        if (viewModel.province.value == null) {
            binding.etProvince.error = getString(R.string.affiliate_province_empty)
            error = true
        }

        if (viewModel.city?.isNotEmpty() != true) {
            binding.etCity.error = getString(R.string.affiliate_city_empty)
            error = true
        }

        if (viewModel.village?.isNotEmpty() != true) {
            binding.etVillage.error = getString(R.string.affiliate_village)
            error = true
        }

        if (viewModel.address?.isNotEmpty() != true) {
            binding.etAddress.error = getString(R.string.affiliate_address_empty)
            error = true
        }

        if (viewModel.zipCode?.isNotEmpty() != true) {
            binding.etZip.error = getString(R.string.affiliate_zipcode_empty)
            error = true
        }

        if (viewModel.username?.isNotEmpty() != true) {
            binding.etUsername.error = getString(R.string.affiliate_username_empty)
            error = true
        }
        else {
            val reg = Regex("[^0-9a-zA-Z_]")
            if (viewModel.username?.contains(reg) == true) {
                binding.etUsername.error = getString(R.string.affiliate_username_invalid)
                error = true
            }
            else
                if (viewModel.username!!.length < 6) {
                    binding.etUsername.error = getString(R.string.affiliate_username_short)
                    error = true
                }
        }

        if (viewModel.password?.isNotEmpty() != true) {
            binding.etPassword.error = getString(R.string.affiliate_password_empty)
            error = true
        }
        else
        if (viewModel.password?.isValidPassword() != true) {
            binding.etPassword.error = getString(R.string.affiliate_password_invalid)
            error = true
        }

        if (viewModel.confirmPassword?.isNotEmpty() != true) {
            binding.etConfirmPassword.error = getString(R.string.affiliate_confirm_password_empty)
            error = true
        }
        else
            if (viewModel.confirmPassword != viewModel.password) {
                binding.etConfirmPassword.error = getString(R.string.affiliate_confirm_password_not_match)
                error = true
            }

        if (! error) {
            doSubmit()
        }
    }

    private fun doSubmit() {
        viewModel.addCustomer().observe(this) {
            JGoProcessResult(fragmentActivity = this, result = it,
                delegate = object : JGoProcessDelegate<Any?> {
                    override fun success(data: Any?) {
                        setResult(RESULT_OK)
                        finish()
                    }
                })
        }
    }

    private fun showProvinceTray() {
        val tray = ProvinceListTray(this,
            viewModel.getProvinces())
        tray.delegate = object : ProvinceDelegate {
            override fun onSelected(item: IdNameData) {
                viewModel.province.postValue(item)
            }
        }
        tray.show()
    }

    private fun startAutocompleteIntent() {
        // Set the fields to specify which types of place data to
        // return after the user has made a selection.
        val fields = listOf(
            Place.Field.ID,
            Place.Field.NAME,
            Place.Field.LAT_LNG,
            Place.Field.ADDRESS
        )

        // Build the autocomplete intent with field, country, and type filters applied
        val intent = Autocomplete.IntentBuilder(AutocompleteActivityMode.OVERLAY, fields)
            .setCountries(listOf("ID"))
            .build(this)
        pinPointAutocomplete.launch(intent)
    }
}