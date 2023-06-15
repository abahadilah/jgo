package edts.base.android.core_data.source.remote.request

import com.google.gson.annotations.SerializedName

data class CreateOrderRequest (
    @field:SerializedName("customer")
    val customerId: Long,

    @field:SerializedName("vehicle_type")
    val vehicleType: Long,

    @field:SerializedName("date")
    val date: String,

    @field:SerializedName("jenis")
    val type: String,

    @field:SerializedName("product_name")
    val product: String?,

    @field:SerializedName("panjang")
    val length: Int,

    @field:SerializedName("lebar")
    val width: Int,

    @field:SerializedName("tinggi")
    val height: Int,

    @field:SerializedName("koli")
    val coli: Int,

    @field:SerializedName("berat")
    val weight: Int,

    @field:SerializedName("ref_customer")
    val ref: String,

    @field:SerializedName("description")
    val description: String,

    @field:SerializedName("list_destination")
    val addresses: List<CreateOrderDestinationRequest>,
)