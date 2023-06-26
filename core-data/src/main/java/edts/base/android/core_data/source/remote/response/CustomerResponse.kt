package edts.base.android.core_data.source.remote.response

import com.google.gson.annotations.SerializedName

data class CustomerResponse (
    @field:SerializedName("id")
    val id: Long,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("email")
    val email: String?,

    @field:SerializedName("mobile")
    val mobile: String?,

    @field:SerializedName("partner_latitude")
    val lat: Double?,

    @field:SerializedName("partner_longitude")
    val lng: Double?,

    @field:SerializedName("street")
    val street: String?,

    @field:SerializedName("street2")
    val street2: String?,

    @field:SerializedName("city_id")
    val city: IdNameResponse?,

    @field:SerializedName("zip")
    val zip: String?,

    @field:SerializedName("state_id")
    val state: IdNameResponse?,

    @field:SerializedName("country_id")
    val country: IdNameResponse?,

    @field:SerializedName("partner_type")
    val type: String?,

    @field:SerializedName("fcm_token_android")
    val token: String?
    )