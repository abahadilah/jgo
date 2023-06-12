package edts.base.android.core_data.source.remote.request

import com.google.gson.annotations.SerializedName

data class SearchLocationRequest (
    @field:SerializedName("location")
    val location: String
)