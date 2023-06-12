package edts.base.core_utils

import android.content.Context
import edts.base.android.core_utils.R
import java.text.DecimalFormat
import kotlin.math.abs

fun Double.money(context: Context): String {
    return if (this < 0) {
        context.getString(R.string.money_minus, abs(this).formatMoney())
    }
    else {
        context.getString(R.string.money, this.formatMoney())
    }
}

fun Double.formatDecimal(): String? {
    val formatter = DecimalFormat("#,###.##")
    val symbols = formatter.decimalFormatSymbols
    symbols.groupingSeparator = '.'
    symbols.decimalSeparator = ','
    formatter.decimalFormatSymbols = symbols
    return formatter.format(this)
}

fun Double.formatMoney(): String? {
    val formatter = DecimalFormat("#,###")
    val symbols = formatter.decimalFormatSymbols
    symbols.groupingSeparator = '.'
    symbols.decimalSeparator = ','
    formatter.decimalFormatSymbols = symbols
    return formatter.format(this)
}