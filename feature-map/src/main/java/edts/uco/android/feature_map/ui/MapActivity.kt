package edts.uco.android.feature_map.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.widget.Toast
import androidx.preference.PreferenceManager
import edts.uco.android.feature_map.BuildConfig
import edts.uco.android.feature_map.databinding.ActivityMapBinding
import id.co.edtslib.uibase.PopupActivity
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.CustomZoomButtonsController

class MapActivity: PopupActivity<ActivityMapBinding>() {
    override val bindingInflater: (LayoutInflater) -> ActivityMapBinding
        get() = ActivityMapBinding::inflate

    override fun getTrackerPageName(): String? = null

    override fun setupPopup() {
        setupView()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setupView() {
        Configuration.getInstance().apply {
            userAgentValue = BuildConfig.LIBRARY_PACKAGE_NAME
        }

        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        if (!prefs.contains("osmdroid.basePath")) {
            val file = Configuration.getInstance().getOsmdroidBasePath(this)
            if (file == null) {
                Toast.makeText(this, "Gagal membuka peta", Toast.LENGTH_SHORT).show()
                return
            }
        }
        Configuration.getInstance().load(this, prefs)

        binding.mapView.setUseDataConnection(true)
        binding.mapView.zoomController.setVisibility(CustomZoomButtonsController.Visibility.NEVER)
        binding.mapView.setTileSource(TileSourceFactory.MAPNIK)
        binding.mapView.setMultiTouchControls(true)


        val lat = intent?.getDoubleExtra("lat", 0.0)
        val lng = intent?.getDoubleExtra("lng", 0.0)
        animateCamera(lat!!, lng!!, 18.0)
    }

    private fun animateCamera(lat: Double, lng: Double, zoom: Double) {
        val latLng = GeoPoint(lat, lng)

        binding.mapView.controller?.setCenter(latLng)
        binding.mapView.controller?.zoomTo(zoom)
        binding.mapView.controller?.animateTo(latLng)
        binding.mapView.invalidate()
    }
}