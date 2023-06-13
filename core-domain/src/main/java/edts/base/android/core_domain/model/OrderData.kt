package edts.base.android.core_domain.model

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

data class OrderData (
    val taxAmount: Double?,
    val beforeTaxAmount: Double?,
    val weight: Double?,
    val created: String?,
    val customerStreet: String?,
    val customerStreet2: String?,
    val date: String?,
    val description: String?,
    val id: Long,
    val type: String?,
    val coli: Double?,
    val width: Double?,
    val name: String?,
    val length: Double?,
    val productName: String?,
    val height: Double?,
    val totalAmount: Double?,
    val state: String?,
    val invoice: List<String>?,

    ) {

    fun getCreatedDate(): String? {
        if (created == null) {
            return null
        }
        else {
            val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale("ID"))
            return try {
                val d = simpleDateFormat.parse(created)
                if (d == null) {
                    created
                } else {
                    val simpleDateFormat1 = SimpleDateFormat("dd MMMM yyyy HH:mm:ss", Locale("ID"))
                    simpleDateFormat1.format(d)
                }
            } catch (e: ParseException) {
                created
            }
        }
    }

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