package edts.base.android.core_data.source.remote.request

import com.google.gson.annotations.SerializedName

data class FcmBindRequest (
    @field:SerializedName("partner_id")
    val id: Long?,

    @field:SerializedName("fcm_token_android")
    val token: String?
)