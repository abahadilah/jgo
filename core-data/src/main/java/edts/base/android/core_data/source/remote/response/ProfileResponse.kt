package edts.base.android.core_data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ProfileResponse (
    @field:SerializedName("id")
    val id: Long,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("partner_latitude")
    val latitude: Double?,

    @field:SerializedName("partner_longitude")
    val longitude: Double?,

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
    )