package edts.uco.android.feature_order.ui.create

interface DimensionDelegate {
    fun onLengthChanged(l: Int?)
    fun onWidthChanged(w: Int?)
    fun onHeightChanged(h: Int?)
}