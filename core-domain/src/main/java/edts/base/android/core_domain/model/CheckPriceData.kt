package edts.base.android.core_domain.model

data class CheckPriceData (
    val tipe: String,
    val km: String?,
    val cost: Double?,
    var duration: String?,
    var origin: List<String>?,
    var destination: List<String>?,
)