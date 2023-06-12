package edts.uco.android.feature_map.ui.tray

interface LocationDelegate {
    fun onCurrentLocation()
    fun onSearch(keyword: String)
    fun onMap(lat: Double, lng: Double)
}