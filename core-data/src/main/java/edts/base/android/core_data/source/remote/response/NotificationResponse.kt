package edts.base.android.core_data.source.remote.response

import com.google.gson.annotations.SerializedName

data class NotificationResponse (
    @field:SerializedName("id")
    val id: Long,

    @field:SerializedName("notif_image")
    val image: Boolean?,

    @field:SerializedName("notif_title")
    val title: String?,

    @field:SerializedName("notif_description")
    val description: String?,

    @field:SerializedName("is_promo")
    val isPromo: Boolean?,

    @field:SerializedName("create_date")
    val created: String?,

    @field:SerializedName("id_object")
    val idObject: Long?,

    @field:SerializedName("route_android")
    val route: String?
)