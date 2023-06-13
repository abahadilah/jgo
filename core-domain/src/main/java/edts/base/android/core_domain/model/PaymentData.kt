package edts.base.android.core_domain.model

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Locale

data class PaymentData (
    val id: Long,
    val name: String,
    val partnerName: String?,
    val partnerType: String?,
    val type: String?,
    val company: String?,
    val amount: Double?,
    val date: String?,
    val invoice: String?,
    val journal: String?,
    val state: String?
) {
    fun getMonthYear(): String? {
        if (date == null) {
            return null
        }
        else {
            return try {
                val simpleDateFormat1 = SimpleDateFormat("yyyy-MM-dd", Locale("ID"))

                val d = simpleDateFormat1.parse(date)
                if (d == null) {
                    null
                } else {
                    val simpleDateFormat = SimpleDateFormat("MMMM yyyy", Locale("ID"))
                    simpleDateFormat.format(d)
                }
            } catch (e: ParseException) {
                null
            }
        }
    }

}