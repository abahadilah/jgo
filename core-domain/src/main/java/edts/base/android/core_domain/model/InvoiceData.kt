package edts.base.android.core_domain.model

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

data class InvoiceData (
    val id: Long,
    val name: String,
    val created: String?,
    val state: String?,
    val total: String?,
    val tax: String?,
    var userId: String?,
    var residual: String?,
    val dueDate: String?,
    val created2: String?,
    val dueDate2: String?,
    var company: String?
) {
    fun getMonthYear(): String? {
        if (dueDate2 == null) {
            return null
        }
        else {
            return try {
                val simpleDateFormat1 = SimpleDateFormat("yyyy-MM-dd", Locale("ID"))

                val d = simpleDateFormat1.parse(dueDate2)
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