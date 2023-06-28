package edts.base.android.core_data.source.remote.request

import com.google.gson.annotations.SerializedName

data class CustomerAddRequest(
    @field:SerializedName("customer")
    val name: String,

    @field:SerializedName("affiliate_id")
    val affiliateId: Long,

    @field:SerializedName("ktp")
    val ktp: String,

    @field:SerializedName("mobile")
    val mobile: String,

    @field:SerializedName("email")
    val email: String,

    @field:SerializedName("partner_longitude")
    val lng: Double,

    @field:SerializedName("partner_latitude")
    val lat: Double,

    @field:SerializedName("username_android")
    val username: String,

    @field:SerializedName("password_android")
    val password: String,

    @field:SerializedName("street")
    val street: String,

    @field:SerializedName("city")
    val city: String,

    @field:SerializedName("zipcode")
    val zipcode: String,

    @field:SerializedName("state_id")
    val province: Long
)
