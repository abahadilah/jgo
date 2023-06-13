package edts.base.android.core_data.source.local

data class OrderEntity (
    val taxAmount: Double?,
    val beforeTaxAmount: Double?,
    val weight: Double?,
    val created: String?,
    val customerStreet: String?,
    val customerStreet2: String?,
    val date: String?,
    val description: String?,
    val id: Long,
    val type: String?,
    val coli: Double?,
    val width: Double?,
    val name: String?,
    val length: Double?,
    val productName: String?,
    val height: Double?,
    val totalAmount: Double?,
    val state: String?,
    val invoice: IdNameEntity?,
)