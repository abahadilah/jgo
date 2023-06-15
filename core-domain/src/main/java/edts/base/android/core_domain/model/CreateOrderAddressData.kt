package edts.base.android.core_domain.model

data class CreateOrderAddressData (
    val name: String,
    val city: String?,
    val latitude: Double,
    val longitude: Double,
) {
    override fun toString() = "$name, $city"
    override fun equals(other: Any?) =
        if (other !is CreateOrderAddressData) {
            false
        } else {
            other.name == name && other.city == city && other.latitude == latitude &&
                    other.longitude == longitude
        }

    override fun hashCode(): Int {
        return super.hashCode()
    }
}