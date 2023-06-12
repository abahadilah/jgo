package edts.base.core_utils

import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar

object SnackBarUtils {
    fun showSnackBar(context: Context, view: View, message: String, color: Int? = null) {
        val snackBar = snackBar(context, view, message)
        if (color != null) {
            snackBar.setBackgroundTint(
                ContextCompat.getColor(context, color)
            )
        }
        snackBar.show()
    }

    fun showSnackBarForError(context: Context, view: View, message: String) {
        showSnackBar(context, view, message, id.co.edtslib.edtsds.R.color.colorSupportError)
    }

    private fun snackBar(context: Context, view: View, message: String): Snackbar {
        return Snackbar.make(
            context, view, message, Snackbar.LENGTH_SHORT
        )
    }
}