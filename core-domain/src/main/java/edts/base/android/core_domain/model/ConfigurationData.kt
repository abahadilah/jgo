package edts.base.android.core_domain.model

data class ConfigurationData (
    val originAddress: AddressData?,
    var skipVersion: String?,
    val fcmId: String?
)