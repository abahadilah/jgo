package edts.base.android.core_data.source.remote.request

import com.google.gson.annotations.SerializedName

data class LoginRequest (
    @field:SerializedName("username")
    val username: String,

    @field:SerializedName("password")
    val password: String,

    @field:SerializedName("deviceId")
    val deviceId: String,

    @field:SerializedName("deviceType")
    val deviceType: String,

    @field:SerializedName("deviceOsName")
    val deviceOsName: String,
)