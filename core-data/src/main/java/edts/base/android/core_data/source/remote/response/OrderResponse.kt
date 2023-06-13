package edts.base.android.core_data.source.remote.response

import com.google.gson.annotations.SerializedName

data class OrderResponse (
    @field:SerializedName("amount_taxs")
    val taxAmount: Double?,

    @field:SerializedName("before_taxs")
    val beforeTaxAmount: Double?,

    @field:SerializedName("berat")
    val weight: Double?,

    @field:SerializedName("create_date")
    val created: String?,

    @field:SerializedName("customer_street")
    val customerStreet: String?,

    @field:SerializedName("customer_street2")
    val customerStreet2: String?,

    @field:SerializedName("date")
    val date: String?,

    @field:SerializedName("description")
    val description: String?,

    @field:SerializedName("id")
    val id: Long,

    @field:SerializedName("jenis")
    val type: String?,

    @field:SerializedName("koli")
    val coli: Double?,

    @field:SerializedName("lebar")
    val width: Double?,

    @field:SerializedName("name")
    val name: String?,

    @field:SerializedName("panjang")
    val length: Double?,

    @field:SerializedName("product_name")
    val productName: String?,

    @field:SerializedName("tinggi")
    val height: Double?,

    @field:SerializedName("total_amount")
    val totalAmount: Double?,

    @field:SerializedName("state")
    val state: String?,

    @field:SerializedName("ref_invoice")
    val invoice: IdNameResponse?,
    )