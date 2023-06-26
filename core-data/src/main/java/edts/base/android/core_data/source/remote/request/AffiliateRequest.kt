package edts.base.android.core_data.source.remote.request

import com.google.gson.annotations.SerializedName

data class AffiliateRequest(
    @field:SerializedName("affiliate_id")
    val id: Long
)
