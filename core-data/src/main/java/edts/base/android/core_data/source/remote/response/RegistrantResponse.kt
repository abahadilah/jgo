package edts.base.android.core_data.source.remote.response

import com.google.gson.annotations.SerializedName

data class RegistrantResponse (
    @field:SerializedName("id")
    val id: Long,

    @field:SerializedName("supplierName")
    val name: String,

    @field:SerializedName("supplierPhone")
    val phone: String,

    @field:SerializedName("branchId")
    val branchId: Int?,

    @field:SerializedName("stockpointId")
    val stockPointId: Int?,

    @field:SerializedName("stockpointName")
    val stockPointName: String?,

    @field:SerializedName("supplierAddress")
    val address: String,

    @field:SerializedName("supplierLatitude")
    val latitude: Double,

    @field:SerializedName("supplierLongitude")
    val longitude: Double,

    @field:SerializedName("supplierOilAmountCommitment")
    val oilAmountCommitment: Double,

    @field:SerializedName("supplierPriceCommitment")
    val priceCommitment: Double,

    @field:SerializedName("supplierBankAccount")
    val bankAccount: String?,

    @field:SerializedName("supplierBankName")
    val bankName: String?,

    @field:SerializedName("supplierPickupDay")
    val pickupDay: Int,

    @field:SerializedName("pickupTime")
    val pickupTime: Int,

    @field:SerializedName("iapoutletCode")
    val outletCode: String?,

    @field:SerializedName("pickupLat")
    val pickupLatitude: Double,

    @field:SerializedName("pickupLong")
    val pickupLongitude: Double,

    @field:SerializedName("pickupAddress")
    val pickupAddress: String?,

    @field:SerializedName("outletName")
    val outletName: String?,

    @field:SerializedName("ktpPict")
    val ktpPict: String?,

    @field:SerializedName("selfieKtpPict")
    val selfieKtpPict: String?,

    @field:SerializedName("nik")
    val nik: String?,

    @field:SerializedName("offeredPriceCommitment")
    val offeredPriceCommitment: Double?,

    @field:SerializedName("rejectReason")
    val rejectReason: List<Int>?,

    @field:SerializedName("branchName")
    val branchName: String?,

    @field:SerializedName("userId")
    val userId: String?,


)