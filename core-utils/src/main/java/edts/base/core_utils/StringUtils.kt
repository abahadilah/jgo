package edts.base.core_utils

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Build
import android.util.Patterns
import androidx.core.content.ContextCompat
import edts.base.android.core_utils.R
import kotlin.math.abs

fun String.bold(subText: String): String {
    val f = indexOf(subText, 0, ignoreCase = true)
    return if (f < 0) {
        this
    } else {
        val prefix = if (f <= 0) "" else substring(0, f)
        val postfix = substring(f + subText.length)

        String.format("%s<b>%s</b>%s", prefix, subText, postfix)
    }
}

fun String.copy(context: Context, label: String) {
    try {
        val clipboard = ContextCompat.getSystemService(context, ClipboardManager::class.java)
        val clip = ClipData.newPlainText(label, this)
        clipboard?.setPrimaryClip(clip)
    } catch (e: SecurityException) {
        // nothing to
    }
}

fun String.removePhoneNumberPrefix(): String {
    return if (startsWith("08")) {
        removePrefix("08")
    }
    else
        if (startsWith("+628")) {
            removePrefix("+628")
        }
        else
    if (startsWith("628")) {
        removePrefix("628")
    }
    else {
        this
    }
}

fun String.more(max: Int, postfix: String? = null): String {
    if (length > max) {
        val sentence = substring(0, max)
        var i = max
        while (i >= 0) {
            if (this[i] == ' ' || this[i] == '\n' || this[i] == '\t') {
                return if (postfix == null) String.format("%s...", this.substring(0, i)) else
                    String.format("%s... %s", this.substring(0, i), postfix)
            }

            i--
        }

        return sentence
    }

    return this
}

fun String.isValidName() = isNotEmpty()

fun String.isValidPhone() =
    !isNullOrEmpty() && Patterns.PHONE.matcher(this).matches() && length >= 8 && this.substring(0..1) == "08"
fun String.isPhoneCandidate() = !isNullOrEmpty() && Patterns.PHONE.matcher(this).matches()

fun String.isValidEmail() =
    !isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()


fun String.isValidAddress() = isNotEmpty()
fun String.isValidPassword() = !isNullOrEmpty() && length >= 6 && this.contains("""[a-zA-Z]""".toRegex()) && this.contains("""[0-9]""".toRegex())
fun String.isValidPhoneOrAddress() = isValidPhone() || isValidEmail()
fun String.isPhoneNo() = Patterns.PHONE.matcher(this).matches() && this.substring(0..1) == "08"
fun String.isValidKtp() = !isNullOrEmpty() && length == 16

fun getDeviceName() : String{
    val manufactureHp = Build.MANUFACTURER ?: ""
    val modelhp = Build.MODEL ?: ""
    return "$manufactureHp $modelhp"
}

fun String.money(context: Context): String {
    return context.getString(R.string.money, this)
}