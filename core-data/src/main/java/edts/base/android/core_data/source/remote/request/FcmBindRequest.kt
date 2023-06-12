package edts.base.android.core_data.source.remote.request

import com.google.gson.annotations.SerializedName

data class FcmBindRequest (
    @field:SerializedName("fcmId")
    val fcmId: String?,

    @field:SerializedName("deviceId")
    val deviceId: String,

    @field:SerializedName("osType")
    val osType: String?,

    @field:SerializedName("phone")
    val phone: String?
)