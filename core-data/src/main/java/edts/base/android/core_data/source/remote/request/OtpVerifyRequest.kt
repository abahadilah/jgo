package edts.base.android.core_data.source.remote.request

import com.google.gson.annotations.SerializedName

data class OtpVerifyRequest (
    @field:SerializedName("phoneNumber")
    val phoneNumber: String,

    @field:SerializedName("otp")
    val otp: String
)