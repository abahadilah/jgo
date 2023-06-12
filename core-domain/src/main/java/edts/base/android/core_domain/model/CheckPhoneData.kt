package edts.base.android.core_domain.model

data class CheckPhoneData (
    val phoneNumber: String,
    val status: String,
    val sessionUUID: String?,
    val registrant: RegistrantData?,
)