package edts.base.android.core_domain.model

data class OrderDetailRecipientData (
    val pickupName: List<Any>,
    val pickupCity: List<Any>,
    val recipientName: List<Any>,
    val recipientCity: List<Any>,
    val distance: Double?,

    )