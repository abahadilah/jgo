package edts.base.android.core_domain.model

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Locale

data class NotificationData (
    val id: Long,
    val image: Boolean?,
    val title: String?,
    val description: String?,
    val isPromo: Boolean?,
    val created: String?,
    val idObject: Long?,
    val route: String?
) {
    fun getTimeFormat(): String? {
        if (created == null) {
            return null
        }
        else {
            return try {
                val format = SimpleDateFormat("yyyy-MM-dd", Locale("US"))
                val date = format.parse(created)
                if (date == null) {
                    created
                } else {
                    val format1 = SimpleDateFormat("dd MMMM yyyy", Locale("ID"))
                    format1.format(date)
                }
            } catch (e: ParseException) {
                created
            }
        }

    }
}