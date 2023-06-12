package edts.uco.android.feature_map.ui.price

import android.annotation.SuppressLint
import android.view.LayoutInflater
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.Autocomplete
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode
import edts.base.android.core_domain.model.CheckPriceData
import edts.base.android.core_domain.model.VehicleTypeData
import edts.base.android.core_navigation.ModuleNavigator
import edts.base.android.core_resource.base.result.UcoProcessDelegate
import edts.base.android.core_resource.base.result.UcoProcessResult
import edts.uco.android.feature_map.R
import edts.uco.android.feature_map.databinding.ActivityCheckPriceBinding
import id.co.edtslib.edtsds.textfield.TextFieldDelegate
import id.co.edtslib.uibase.PopupActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.Locale

class CheckPriceActivity: PopupActivity<ActivityCheckPriceBinding>(), OnMapReadyCallback, ModuleNavigator {
    private val viewModel: CheckPriceViewModel by viewModel()
    private var map: GoogleMap? = null

    private val originAddressAutocomplete = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult(),
        ActivityResultCallback { result: ActivityResult ->
            if (result.resultCode == RESULT_OK) {
                val intent = result.data
                if (intent != null) {
                    val place = Autocomplete.getPlaceFromIntent(intent)
                    viewModel.originAddress.postValue(place)
                }
            }
        } as ActivityResultCallback<ActivityResult>)

    private val destinationAddress1Autocomplete = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult(),
        ActivityResultCallback { result: ActivityResult ->
            if (result.resultCode == RESULT_OK) {
                val intent = result.data
                if (intent != null) {
                    val place = Autocomplete.getPlaceFromIntent(intent)
                    viewModel.destinationAddress1.postValue(place)
                }
            }
        } as ActivityResultCallback<ActivityResult>)

    private val destinationAddress2Autocomplete = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult(),
        ActivityResultCallback { result: ActivityResult ->
            if (result.resultCode == RESULT_OK) {
                val intent = result.data
                if (intent != null) {
                    val place = Autocomplete.getPlaceFromIntent(intent)
                    viewModel.destinationAddress2.postValue(place)
                }
            }
        } as ActivityResultCallback<ActivityResult>)

    private val destinationAddress3Autocomplete = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult(),
        ActivityResultCallback { result: ActivityResult ->
            if (result.resultCode == RESULT_OK) {
                val intent = result.data
                if (intent != null) {
                    val place = Autocomplete.getPlaceFromIntent(intent)
                    viewModel.destinationAddress3.postValue(place)
                }
            }
        } as ActivityResultCallback<ActivityResult>)

    private val destinationAddress4Autocomplete = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult(),
        ActivityResultCallback { result: ActivityResult ->
            if (result.resultCode == RESULT_OK) {
                val intent = result.data
                if (intent != null) {
                    val place = Autocomplete.getPlaceFromIntent(intent)
                    viewModel.destinationAddress4.postValue(place)
                }
            }
        } as ActivityResultCallback<ActivityResult>)

    private val destinationAddress5Autocomplete = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult(),
        ActivityResultCallback { result: ActivityResult ->
            if (result.resultCode == RESULT_OK) {
                val intent = result.data
                if (intent != null) {
                    val place = Autocomplete.getPlaceFromIntent(intent)
                    viewModel.destinationAddress5.postValue(place)
                }
            }
        } as ActivityResultCallback<ActivityResult>)

    override val bindingInflater: (LayoutInflater) -> ActivityCheckPriceBinding
        get() = ActivityCheckPriceBinding::inflate

    override fun getTrackerPageName(): String? = null

    override fun setupPopup() {
        if (!Places.isInitialized()) {
            Places.initialize(this, "AIzaSyB_G55jQfWNymaR5nTssCO5S_lM3Pau9Uw",
                Locale("ID")
            )
        }

        setupView()
        setupObserver()
        setupListener()
        initData()
    }

    private fun setupView() {
        binding.ivOriginAddressMap.isVisible = false
        binding.ivDestinationAddress1Map.isVisible = false
        binding.ivDestinationAddress2Map.isVisible = false
        binding.ivDestinationAddress3Map.isVisible = false
        binding.ivDestinationAddress4Map.isVisible = false
        binding.ivDestinationAddress5Map.isVisible = false
    }

    @SuppressLint("SetTextI18n")
    private fun setupObserver() {
        viewModel.vehicleType.observe(this) {
            clearError()
            binding.etVehicleType.text = "${it.name} (${it.description?.trim()})"
        }

        viewModel.originAddress.observe(this) {
            clearError()
            binding.etOriginAddress.text = it.name
            binding.ivOriginAddressMap.isVisible = true
            binding.ivOriginAddressMap.setOnClickListener { _ ->
                navigateToMapActivity(it.latLng?.latitude, it.latLng?.longitude)
            }

        }

        viewModel.destinationAddress1.observe(this) {
            clearError()
            binding.etDestinationAddress1.text = it.name
            binding.ivDestinationAddress1Map.isVisible = true
            binding.ivDestinationAddress1Map.setOnClickListener { _ ->
                navigateToMapActivity(it.latLng?.latitude, it.latLng?.longitude)
            }
        }

        viewModel.destinationAddress2.observe(this) {
            clearError()
            binding.etDestinationAddress2.text = it.name
            binding.ivDestinationAddress2Map.isVisible = true
            binding.ivDestinationAddress2Map.setOnClickListener { _ ->
                navigateToMapActivity(it.latLng?.latitude, it.latLng?.longitude)
            }
        }

        viewModel.destinationAddress3.observe(this) {
            clearError()
            binding.etDestinationAddress3.text = it.name
            binding.ivDestinationAddress3Map.isVisible = true
            binding.ivDestinationAddress3Map.setOnClickListener { _ ->
                navigateToMapActivity(it.latLng?.latitude, it.latLng?.longitude)
            }
        }

        viewModel.destinationAddress4.observe(this) {
            clearError()
            binding.etDestinationAddress4.text = it.name
            binding.ivDestinationAddress4Map.isVisible = true
            binding.ivDestinationAddress4Map.setOnClickListener { _ ->
                navigateToMapActivity(it.latLng?.latitude, it.latLng?.longitude)
            }
        }

        viewModel.destinationAddress5.observe(this) {
            clearError()
            binding.etDestinationAddress5.text = it.name
            binding.ivDestinationAddress5Map.isVisible = true
            binding.ivDestinationAddress5Map.setOnClickListener { _ ->
                navigateToMapActivity(it.latLng?.latitude, it.latLng?.longitude)
            }
        }
    }

    private fun initData() {
        viewModel.getVehicleType(true).observeForever {  }
    }

    private fun setupListener() {
        binding.etVehicleType.delegate = object : TextFieldDelegate {
            override fun onChanged(input: String?) {
                chooseVehicleType()
            }
        }

        binding.etOriginAddress.delegate = object : TextFieldDelegate {
            override fun onChanged(input: String?) {
                startAutocompleteIntent(AutocompleteTarget.OriginAddress)
            }
        }

        binding.etDestinationAddress1.delegate = object : TextFieldDelegate {
            override fun onChanged(input: String?) {
                startAutocompleteIntent(AutocompleteTarget.DestinationAddress1)
            }
        }

        binding.etDestinationAddress2.delegate = object : TextFieldDelegate {
            override fun onChanged(input: String?) {
                startAutocompleteIntent(AutocompleteTarget.DestinationAddress2)
            }
        }

        binding.etDestinationAddress3.delegate = object : TextFieldDelegate {
            override fun onChanged(input: String?) {
                startAutocompleteIntent(AutocompleteTarget.DestinationAddress3)
            }
        }

        binding.etDestinationAddress4.delegate = object : TextFieldDelegate {
            override fun onChanged(input: String?) {
                startAutocompleteIntent(AutocompleteTarget.DestinationAddress4)
            }
        }

        binding.etDestinationAddress5.delegate = object : TextFieldDelegate {
            override fun onChanged(input: String?) {
                startAutocompleteIntent(AutocompleteTarget.DestinationAddress5)
            }
        }

        binding.bvSubmit.setOnClickListener {
            submit()
        }
    }

    private fun submit() {
        if (viewModel.vehicleType.value == null) {
            binding.etVehicleType.error = getString(R.string.price_vehicle_type_empty)
        }

        if (viewModel.originAddress.value == null) {
            binding.etOriginAddress.error = getString(R.string.price_origin_address_empty)
        }

        if (viewModel.destinationAddress1.value == null) {
            binding.tvDestinationAddress1.error = getString(R.string.price_destination_address_empty)
        }

        viewModel.checkPrice().observe(this) {
            UcoProcessResult(fragmentActivity = this,
                result = it,
                delegate = object : UcoProcessDelegate<CheckPriceData?> {
                    override fun success(data: CheckPriceData?) {
                        CheckResultTray(this@CheckPriceActivity,
                            vehicle = viewModel.vehicleType.value,
                            originAddress = viewModel.originAddress.value,
                            destinationAddress1 = viewModel.destinationAddress1.value,
                            destinationAddress2 = viewModel.destinationAddress2.value,
                            destinationAddress3 = viewModel.destinationAddress3.value,
                            destinationAddress4 = viewModel.destinationAddress4.value,
                            destinationAddress5 = viewModel.destinationAddress5.value,
                            price = data).show()
                    }
                })
        }
    }

    private fun clearError() {
        binding.etVehicleType.error = null
        binding.etOriginAddress.error = null
        binding.etDestinationAddress1.error = null
        binding.etDestinationAddress2.error = null
        binding.etDestinationAddress3.error = null
        binding.etDestinationAddress4.error = null
        binding.etDestinationAddress5.error = null
    }

    private fun chooseVehicleType() {
        viewModel.getVehicleType(false).observe(this) {
            if (it.data?.isNotEmpty() == true) {
                val tray = VehicleTypeTray(this, it.data!!)
                tray.delegate = object : VehicleTypeDelegate {
                    override fun onSelected(vehicleTypeData: VehicleTypeData) {
                        viewModel.vehicleType.postValue(vehicleTypeData)
                        tray.close()
                    }
                }
                tray.show()
            }
        }
    }

    override fun onMapReady(p0: GoogleMap) {
        map = p0
    }

    private fun startAutocompleteIntent(target: AutocompleteTarget) {
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
        when(target) {
            AutocompleteTarget.DestinationAddress5 ->
                destinationAddress5Autocomplete.launch(intent)
            AutocompleteTarget.DestinationAddress4 ->
                destinationAddress4Autocomplete.launch(intent)
            AutocompleteTarget.DestinationAddress3 ->
                destinationAddress3Autocomplete.launch(intent)
            AutocompleteTarget.DestinationAddress2 ->
                destinationAddress2Autocomplete.launch(intent)
            AutocompleteTarget.DestinationAddress1 ->
                destinationAddress1Autocomplete.launch(intent)
            else ->
                originAddressAutocomplete.launch(intent)
        }
    }


}