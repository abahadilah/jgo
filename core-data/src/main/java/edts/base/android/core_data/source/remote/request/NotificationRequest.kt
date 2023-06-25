package edts.base.android.core_data.source.remote.request

import com.google.gson.annotations.SerializedName

data class NotificationRequest (
    @field:SerializedName("partner_id")
    val id: Long,

    @field:SerializedName("offset")
    val offset: Int,

    @field:SerializedName("limit")
    val limit: Int
)