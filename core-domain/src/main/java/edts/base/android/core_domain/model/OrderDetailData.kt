package edts.base.android.core_domain.model

data class OrderDetailData (
    val recipient: List<OrderDetailRecipientData>?,
    val cost: List<OrderDetailCostData>?,
    val vehicle: List<OrderDetailDriverData>?,
    )