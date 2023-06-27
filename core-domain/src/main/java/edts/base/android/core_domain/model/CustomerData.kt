package edts.base.android.core_domain.model

import id.co.edtslib.edtsds.list.radiobuttonlist.DataSelected

class CustomerData: DataSelected() {
    var id: Long = 0L
    var name: String = ""
    var email: String? = null
    var mobile: String? = null
    var lat: Double? = null
    var lng: Double? = null
    var street: String? = null
    var street2: String? = null
    var city: IdNameData? = null
    var zip: String? = null
    var state: IdNameData? = null
    var country: IdNameData? = null
    var type: String? = null
    var token: String? = null

    override fun toString() = name
    fun address(): String {
        val s = if (street == null) "" else street
        val sCity = if (city?.name == null) "" else city!!.name
        val sState = if (state?.name == null) "" else state!!.name
        val sZip = if (zip == null) "" else zip

        return String.format("$s ${sCity} ${sState} $sZip")
    }
}
