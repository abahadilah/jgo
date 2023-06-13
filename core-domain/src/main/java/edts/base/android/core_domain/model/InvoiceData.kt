package edts.base.android.core_domain.model

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

data class InvoiceData (
    val id: Long,
    val name: String,
    val created: String?,
    val state: String?,
    val total: Double?,
    val tax: String?,
    var userId: String?,
    var residual: String?,
    val dueDate: String?,
    var company: String?
) {

    fun getCreatedDateFormat() = getDate(created)
    fun getDueDateFormat() = getDate(dueDate)
    fun getMonthYear(): String? {
        if (dueDate == null) {
            return null
        }
        else {
            return try {
                val simpleDateFormat1 = SimpleDateFormat("yyyy-MM-dd", Locale("ID"))

                val d = simpleDateFormat1.parse(dueDate)
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

    private fun getDate(s: String?): String? {
        if (s == null) {
            return null
        }
        else {
            return try {
                val simpleDateFormat1 = SimpleDateFormat("yyyy-MM-dd", Locale("ID"))

                val d = simpleDateFormat1.parse(s)
                if (d == null) {
                    null
                } else {
                    val simpleDateFormat = SimpleDateFormat("dd MMMM yyyy", Locale("ID"))
                    simpleDateFormat.format(d)
                }
            } catch (e: ParseException) {
                null
            }
        }
    }
}