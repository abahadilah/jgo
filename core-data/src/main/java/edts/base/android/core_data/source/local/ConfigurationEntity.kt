package edts.base.android.core_data.source.local

import edts.base.android.core_domain.model.CustomerData

class ConfigurationEntity {
    var originAddress: AddressEntity? = null
    var skipVersion: String? = null
    var fcmId: String? = null
    var customer: CustomerData? = null
}