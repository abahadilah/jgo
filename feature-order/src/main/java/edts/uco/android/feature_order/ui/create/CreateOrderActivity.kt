package edts.uco.android.feature_order.ui.create

import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.Autocomplete
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode
import edts.base.android.core_domain.model.CheckPriceData
import edts.base.android.core_domain.model.CreateOrderAddressData
import edts.base.android.core_domain.model.CreateOrderData
import edts.base.android.core_domain.model.VehicleTypeData
import edts.base.android.core_navigation.ModuleNavigator
import edts.base.android.core_resource.base.result.UcoProcessDelegate
import edts.base.android.core_resource.base.result.UcoProcessDelegate2
import edts.base.android.core_resource.base.result.UcoProcessResult
import edts.base.core_utils.money
import edts.uco.android.feature_map.ui.price.AutocompleteTarget
import edts.uco.android.feature_map.ui.price.VehicleTypeDelegate
import edts.uco.android.feature_map.ui.price.VehicleTypeTray
import edts.uco.android.feature_order.BuildConfig
import edts.uco.android.feature_order.R
import edts.uco.android.feature_order.databinding.ActivityCreateOrderBinding
import id.co.edtslib.edtsds.checkbox.CheckBoxDelegate
import id.co.edtslib.edtsds.popup.Popup
import id.co.edtslib.edtsds.popup.PopupDelegate
import id.co.edtslib.edtsds.textfield.TextFieldDelegate
import id.co.edtslib.uibase.PopupActivity
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.NumberFormatException
import java.util.Locale

class CreateOrderActivity: PopupActivity<ActivityCreateOrderBinding>(), OnMapReadyCallback, ModuleNavigator {
    private val viewModel: CreateOrderViewModel by viewModel()

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

    private val destinationAddressAutocomplete2 = registerForActivityResult(
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

    private val destinationAddressAutocomplete3 = registerForActivityResult(
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

    private val destinationAddressAutocomplete4 = registerForActivityResult(
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

    private val destinationAddressAutocomplete5 = registerForActivityResult(
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

    private val destinationAddressAutocomplete = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult(),
        ActivityResultCallback { result: ActivityResult ->
            if (result.resultCode == RESULT_OK) {
                val intent = result.data
                if (intent != null) {
                    val place = Autocomplete.getPlaceFromIntent(intent)
                    viewModel.destinationAddress.postValue(place)
                }
            }
        } as ActivityResultCallback<ActivityResult>)

    override val bindingInflater: (LayoutInflater) -> ActivityCreateOrderBinding
        get() = ActivityCreateOrderBinding::inflate

    override fun getTrackerPageName(): String? = null

    override fun setupPopup() {
        if (!Places.isInitialized()) {
            Places.initialize(this, BuildConfig.MAPS_KEY,
                Locale("ID")
            )
        }

        setupObserver()
        setupListener()
        setupView()
        loadData()
    }

    override fun canBack(): Boolean {
        Popup.show(
            activity = this, title = getString(R.string.order_confirm_cancel_tile),
            message = getString(R.string.order_confirm_cancel),
            positiveButton = getString(edts.base.android.core_resource.R.string.yes),
            negativeButton = getString(edts.base.android.core_resource.R.string.no),
            positiveClickListener = object : PopupDelegate {
                override fun onClick(popup: Popup, view: View) {
                    popup.dismiss()
                    finish()
                }
            },
            negativeClickListener =  null
        )

        return false
    }

    private fun loadData() {
        viewModel.getProfile().observe(this) {
            viewModel.profile.postValue(it)
        }
    }

    private fun setupObserver() {
        viewModel.vehicleType.observe(this) {
            clearError()
            binding.etVehicleType.text = "${it?.name} (${it?.description?.trim()})"
        }

        viewModel.useOfficeAddress.observe(this) {
            binding.etOriginName.isVisible = it != true
            binding.etOriginAddress.isEnabled = it != true

            if (it) {
                loadData()
            }
            else {
                viewModel.originAddress.postValue(null)
            }
        }

        viewModel.profile.observe(this) {
            binding.etOriginAddress.text = it.name
            binding.cbUseCustomerAddress.text = getString(R.string.order_origin_address_use_mine,
                it.name)

            val place = Place.builder()
            place.name = it.name
            place.latLng = LatLng(it.latitude!!, it.longitude!!)

            viewModel.destinationName = it.name
            viewModel.originCity = it.city?.name
            viewModel.originAddress.postValue(place.build())
        }

        viewModel.originAddress.observe(this) {
            binding.ivOriginAddress.isVisible = it != null
            binding.etOriginName.text = it?.name
            binding.etOriginAddress.text = it?.name

            val originAddressComponents = it?.addressComponents?.asList()
            if (originAddressComponents != null && originAddressComponents.size > 2) {
                viewModel.originCity = originAddressComponents[2]?.name
            }
        }

        viewModel.destinationAddress.observe(this) {
            binding.etDestinationAddress.text = it?.name
            binding.etDestinationName.text = it?.name
            binding.ivDestinationAddress.isVisible = it != null

            val destinationComponents = it?.addressComponents?.asList()
            if (destinationComponents != null && destinationComponents.size > 2) {
                viewModel.destinationCity = destinationComponents[2]?.name
            }
        }

        viewModel.destinationAddress2.observe(this) {
            binding.etDestinationAddress2.text = it?.name
            binding.etDestinationName2.text = it?.name
            binding.ivDestinationAddress2.isVisible = it != null
            binding.ivDestinationAddressRemove2.isVisible = it != null

            val destinationComponents = it?.addressComponents?.asList()
            if (destinationComponents != null && destinationComponents.size > 2) {
                viewModel.destinationCity2 = destinationComponents[2]?.name
            }
            else {
                viewModel.destinationCity2 = null
            }
        }

        viewModel.destinationAddress3.observe(this) {
            binding.etDestinationAddress3.text = it?.name
            binding.etDestinationName3.text = it?.name
            binding.ivDestinationAddress3.isVisible = it != null
            binding.ivDestinationAddressRemove3.isVisible = it != null

            val destinationComponents = it?.addressComponents?.asList()
            if (destinationComponents != null && destinationComponents.size > 2) {
                viewModel.destinationCity3 = destinationComponents[2]?.name
            }
            else {
                viewModel.destinationCity3 = null
            }

        }

        viewModel.destinationAddress4.observe(this) {
            binding.etDestinationAddress4.text = it?.name
            binding.etDestinationName4.text = it?.name
            binding.ivDestinationAddress4.isVisible = it != null
            binding.ivDestinationAddressRemove4.isVisible = it != null

            val destinationComponents = it?.addressComponents?.asList()
            if (destinationComponents != null && destinationComponents.size > 2) {
                viewModel.destinationCity4 = destinationComponents[2]?.name
            }
            else {
                viewModel.destinationCity4 = null
            }

        }

        viewModel.destinationAddress5.observe(this) {
            binding.etDestinationAddress5.text = it?.name
            binding.etDestinationName5.text = it?.name
            binding.ivDestinationAddress5.isVisible = it != null
            binding.ivDestinationAddressRemove5.isVisible = it != null

            val destinationComponents = it?.addressComponents?.asList()
            if (destinationComponents != null && destinationComponents.size > 2) {
                viewModel.destinationCity5 = destinationComponents[2]?.name
            }
            else {
                viewModel.destinationCity5 = null
            }

        }

        viewModel.isMultipleDestination.observe(this) {
            binding.llMultipleDestination.isVisible = it == true
            binding.tvMultipleAddress.isSelected = it == true
        }

        lifecycleScope.launch {
            viewModel.isCheckPriseValid.collect {
                loadPrice(it)
            }
        }

        lifecycleScope.launch {
            viewModel.isCheckPriseValid2.collect {
                loadPrice(it)
            }
        }

        lifecycleScope.launch {
            viewModel.isCheckPriseValid3.collect {
                loadPrice(it)
            }
        }

        lifecycleScope.launch {
            viewModel.isCheckPriseValid4.collect {
                loadPrice(it)
            }
        }

        lifecycleScope.launch {
            viewModel.isCheckPriseValid5.collect {
                loadPrice(it)
            }
        }
    }

    private fun loadPrice(isValid: Boolean) {
        if (isValid) {
            viewModel.checkPrice().observe(this@CreateOrderActivity) {
                UcoProcessResult(fragmentActivity = this@CreateOrderActivity, result = it,
                    delegate = object : UcoProcessDelegate2<CheckPriceData?> {
                        override fun success(data: CheckPriceData?) {
                            binding.tvPrice.text = data?.cost?.money(this@CreateOrderActivity)
                        }

                        override fun error(code: String?, message: String?) {
                            binding.tvPrice.text = getString(R.string.order_price_empty)
                            UcoProcessResult.showError(fragmentActivity = this@CreateOrderActivity, message)
                        }

                    })
            }
        } else {
            binding.tvPrice.text = getString(R.string.order_price_empty)
        }
    }

    private fun setupView() {
        binding.cbUseCustomerAddress.isChecked = true

        binding.ivDestinationAddress.isVisible = false
        binding.ivDestinationAddress2.isVisible = false
        binding.ivDestinationAddress3.isVisible = false
        binding.ivDestinationAddress4.isVisible = false
        binding.ivDestinationAddress5.isVisible = false

        binding.ivDestinationAddressRemove2.isVisible = false
        binding.ivDestinationAddressRemove3.isVisible = false
        binding.ivDestinationAddressRemove4.isVisible = false
        binding.ivDestinationAddressRemove5.isVisible = false

        binding.llMultipleDestination.isVisible = false
    }

    private fun setupListener() {
        binding.etVehicleType.delegate = object : TextFieldDelegate {
            override fun onChanged(input: String?) {
                chooseVehicleType()
            }
        }

        binding.tvMultipleAddress.setOnClickListener {
            viewModel.isMultipleDestination.postValue(viewModel.isMultipleDestination.value != true)
        }

        binding.cbUseCustomerAddress.delegate = object : CheckBoxDelegate {
            override fun onChecked(checked: Boolean) {
                viewModel.useOfficeAddress.postValue(checked)
            }
        }

        binding.ivOriginAddress.setOnClickListener {
            navigateToMapActivity(
                viewModel.originAddress.value!!.latLng!!.latitude,
                viewModel.originAddress.value!!.latLng!!.longitude)
        }

        binding.etOriginAddress.delegate = object : TextFieldDelegate {
            override fun onChanged(input: String?) {
                startAutocompleteIntent(AutocompleteTarget.OriginAddress)
            }
        }

        binding.etOriginName.delegate = object : TextFieldDelegate {
            override fun onChanged(input: String?) {
                viewModel.originName = input
            }
        }

        binding.etDestinationAddress.delegate = object : TextFieldDelegate {
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

        binding.ivDestinationAddress.setOnClickListener {
            navigateToMapActivity(
                viewModel.destinationAddress.value!!.latLng!!.latitude,
                viewModel.destinationAddress.value!!.latLng!!.longitude)
        }

        binding.ivDestinationAddress2.setOnClickListener {
            navigateToMapActivity(
                viewModel.destinationAddress2.value!!.latLng!!.latitude,
                viewModel.destinationAddress2.value!!.latLng!!.longitude)
        }

        binding.ivDestinationAddress3.setOnClickListener {
            navigateToMapActivity(
                viewModel.destinationAddress3.value!!.latLng!!.latitude,
                viewModel.destinationAddress3.value!!.latLng!!.longitude)
        }

        binding.ivDestinationAddress4.setOnClickListener {
            navigateToMapActivity(
                viewModel.destinationAddress4.value!!.latLng!!.latitude,
                viewModel.destinationAddress4.value!!.latLng!!.longitude)
        }

        binding.ivDestinationAddress5.setOnClickListener {
            navigateToMapActivity(
                viewModel.destinationAddress5.value!!.latLng!!.latitude,
                viewModel.destinationAddress5.value!!.latLng!!.longitude)
        }

        binding.ivDestinationAddressRemove2.setOnClickListener {
            viewModel.destinationAddress2.postValue(null)
        }

        binding.ivDestinationAddressRemove3.setOnClickListener {
            viewModel.destinationAddress3.postValue(null)
        }

        binding.ivDestinationAddressRemove4.setOnClickListener {
            viewModel.destinationAddress4.postValue(null)
        }

        binding.ivDestinationAddressRemove5.setOnClickListener {
            viewModel.destinationAddress5.postValue(null)
        }

        binding.etDestinationName.delegate = object : TextFieldDelegate {
            override fun onChanged(input: String?) {
                viewModel.destinationName = input
            }
        }

        binding.etDestinationName2.delegate = object : TextFieldDelegate {
            override fun onChanged(input: String?) {
                viewModel.destinationName2 = input
            }
        }

        binding.etDestinationName3.delegate = object : TextFieldDelegate {
            override fun onChanged(input: String?) {
                viewModel.destinationName3 = input
            }
        }

        binding.etDestinationName4.delegate = object : TextFieldDelegate {
            override fun onChanged(input: String?) {
                viewModel.destinationName4 = input
            }
        }

        binding.etDestinationName5.delegate = object : TextFieldDelegate {
            override fun onChanged(input: String?) {
                viewModel.destinationName5 = input
            }
        }

        binding.etDimension.delegate = object : DimensionDelegate {
            override fun onLengthChanged(l: Int?) {
                viewModel.length = l
            }

            override fun onWidthChanged(w: Int?) {
                viewModel.width = w
            }

            override fun onHeightChanged(h: Int?) {
                viewModel.height = h
            }
        }

        binding.etWeight.delegate = object : TextFieldDelegate {
            override fun onChanged(input: String?) {
                try {
                    viewModel.weight = input?.toInt()
                }
                catch (e: NumberFormatException) {
                    viewModel.weight = null
                }
            }
        }

        binding.etColi.delegate = object : TextFieldDelegate {
            override fun onChanged(input: String?) {
                try {
                    viewModel.coli = input?.toInt()
                }
                catch (e: NumberFormatException) {
                    viewModel.coli = null
                }
            }
        }

        binding.etProduct.delegate = object : TextFieldDelegate {
            override fun onChanged(input: String?) {
                viewModel.productName =  input
            }
        }

        binding.etDescription.delegate = object : TextFieldDelegate {
            override fun onChanged(input: String?) {
                try {
                    viewModel.description = input
                }
                catch (e: NumberFormatException) {
                    viewModel.description = null
                }
            }
        }

        binding.ivOriginHistory.setOnClickListener {
            viewModel.getOriginAddressHistory().observe(this) {
                if (it?.isNotEmpty() == true) {
                    showHistoryDialog(HistoryType.Origin, it)
                }
                else {
                    Toast.makeText(this, getString(R.string.order_origin_address_history_empty),
                        Toast.LENGTH_LONG).show()
                }
            }
        }

        binding.ivDestinationHistory.setOnClickListener {
            viewModel.getDestinationAddressHistory().observe(this) {
                if (it?.isNotEmpty() == true) {
                    showHistoryDialog(HistoryType.Destination, it)
                }
                else {
                    Toast.makeText(this, getString(R.string.order_destination_address_history_empty),
                        Toast.LENGTH_LONG).show()
                }
            }
        }

        binding.bvSubmit.setOnClickListener {
            var error = false
            if (viewModel.vehicleType.value == null) {
                binding.etVehicleType.error = getString(R.string.order_vehicle_type_empty)
                error = true
            }

            if (viewModel.originAddress.value == null) {
                binding.etOriginAddress.error = getString(R.string.order_origin_address_empty)
                error = true
            }

            if (viewModel.originName?.isNotEmpty() != true) {
                binding.etOriginAddress.error = getString(R.string.order_origin_address_empty)
                error = true
            }

            if (viewModel.destinationAddress.value == null) {
                binding.etDestinationAddress.error = getString(R.string.order_destination_address_empty)
                error = true
            }

            if (viewModel.destinationName == null) {
                binding.etDestinationName.error = getString(R.string.order_destination_name_empty)
                error = true
            }

            if (viewModel.productName?.isNotEmpty() != true) {
                binding.etProduct.error = getString(R.string.order_product_name_empty)
                error = true
            }

            if (! error) {
                viewModel.createOrder().observe(this@CreateOrderActivity) {
                    UcoProcessResult(fragmentActivity = this@CreateOrderActivity,
                        result = it,
                        delegate = object : UcoProcessDelegate<CreateOrderData?> {
                            override fun success(data: CreateOrderData?) {
                                if (data?.name?.isNotEmpty() == true) {
                                    CreateOrderSuccessDialog(this@CreateOrderActivity, data) {
                                        navigateToHomeActivity()
                                    }.show()
                                }
                                else {
                                    UcoProcessResult.showError(fragmentActivity = this@CreateOrderActivity,
                                        getString(edts.base.android.core_resource.R.string.err_system_body))
                                }
                            }
                        })
                }
            }
        }
    }

    private fun showHistoryDialog(type: HistoryType, list: List<CreateOrderAddressData>) {
        val title = if (type == HistoryType.Origin) R.string.order_origin_history else
            R.string.order_recipient_history
        val tray = AddressHistoryTray(context = this,
                title = getString(title),
                list = list)
        tray.delegate = object : AddressHistoryDelegate {
            override fun onSelected(t: CreateOrderAddressData) {
                if (type == HistoryType.Destination) {
                    val place = Place.builder()
                    place.latLng = LatLng(t.latitude, t.longitude)
                    place.name = t.name

                    viewModel.destinationCity = t.city
                    viewModel.destinationAddress.postValue(place.build())

                    tray.close()
                }
            }
        }
        tray.show()
    }

    private fun startAutocompleteIntent(target: AutocompleteTarget) {
        // Set the fields to specify which types of place data to
        // return after the user has made a selection.
        val fields = listOf(
            Place.Field.ID,
            Place.Field.NAME,
            Place.Field.LAT_LNG,
            Place.Field.ADDRESS,
            Place.Field.ADDRESS_COMPONENTS
        )

        // Build the autocomplete intent with field, country, and type filters applied
        val intent = Autocomplete.IntentBuilder(AutocompleteActivityMode.OVERLAY, fields)
            .setCountries(listOf("ID"))
            .build(this)
        when(target) {
            AutocompleteTarget.DestinationAddress5 ->
                destinationAddressAutocomplete5.launch(intent)
            AutocompleteTarget.DestinationAddress4 ->
                destinationAddressAutocomplete4.launch(intent)
            AutocompleteTarget.DestinationAddress3 ->
                destinationAddressAutocomplete3.launch(intent)
            AutocompleteTarget.DestinationAddress2 ->
                destinationAddressAutocomplete2.launch(intent)
            AutocompleteTarget.DestinationAddress1 ->
                destinationAddressAutocomplete.launch(intent)
            else ->
                originAddressAutocomplete.launch(intent)
        }
    }

    private fun clearError() {
        binding.etVehicleType.error = null
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
    }
}