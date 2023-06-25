package edts.base.android.core_data.source.local

data class NotificationEntity (
    val id: Long,
    val image: Boolean?,
    val title: String?,
    val description: String?,
    val isPromo: Boolean?,
    val created: String?,
    val idObject: Long?,
    val route: String?
)