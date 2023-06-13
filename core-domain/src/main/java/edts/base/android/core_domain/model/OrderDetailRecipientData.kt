package edts.base.android.core_domain.model

data class OrderDetailRecipientData (
    val pickupName: IdNameData,
    val pickupCity: IdNameData,
    val recipientName: IdNameData,
    val recipientCity: IdNameData,
    val distance: Double?,

    )