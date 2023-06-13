package edts.base.android.core_navigation

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP
import androidx.activity.result.ActivityResultLauncher
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.google.gson.Gson
import edts.base.android.core_domain.model.InvoiceData
import edts.base.android.core_domain.model.InvoiceDetailData
import edts.base.android.core_domain.model.OrderData

interface ModuleNavigator {
    fun <T> T.navigateToLoginActivity() where T : FragmentActivity, T : ModuleNavigator {
        startLoginActivity(ActivityClassPath.Login)
    }

    fun <T> T.navigateToLoginActivity() where T : Fragment, T : ModuleNavigator {
        startLoginActivity(ActivityClassPath.Login)
    }

    fun <T> T.navigateToOrderDetail(orderData: OrderData) where T : Fragment, T : ModuleNavigator {
        startActivity(ActivityClassPath.OrderDetail, orderData)
    }

    fun <T> T.navigateToCheckPrice() where T : FragmentActivity, T : ModuleNavigator {
        startActivity(ActivityClassPath.CheckPrice)
    }

    fun <T> T.navigateToInvoiceDetail(invoiceData: InvoiceData) where T : Fragment, T : ModuleNavigator {
        startActivity(ActivityClassPath.InvoiceDetail, invoiceData)
    }

    fun <T> T.navigateToInvoiceDetail(invoiceDetailData: InvoiceDetailData) where T : Fragment, T : ModuleNavigator {
        startActivity(ActivityClassPath.InvoiceDetail, invoiceDetailData)
    }
    fun <T> T.navigateToInvoiceDetail(invoiceDetailData: InvoiceDetailData) where T : FragmentActivity, T : ModuleNavigator {
        startActivity(ActivityClassPath.InvoiceDetail, invoiceDetailData)
    }

    fun <T> T.navigateToMapActivity(lat: Double?, lng: Double?,
                                    resultLauncher: ActivityResultLauncher<Intent>? = null) where T : FragmentActivity, T : ModuleNavigator {
        startMapActivity(activityClassPath = ActivityClassPath.Map,
            lat = lat, lng = lng,
            resultLauncher = resultLauncher)
    }

    fun <T> T.navigateToHomeActivity() where T : FragmentActivity, T : ModuleNavigator {
        startHomeActivity(ActivityClassPath.Home)
    }
}

private fun FragmentActivity.startHomeActivity(
    activityClassPath: ActivityClassPath
) {
    val intent = activityClassPath.getIntent(this)
    intent.addFlags(
        Intent.FLAG_ACTIVITY_NEW_TASK or
                FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK
    )
    startActivity(intent)
}

private fun FragmentActivity.startLoginActivity(
    activityClassPath: ActivityClassPath
) {
    val intent = activityClassPath.getIntent(this)
    intent.addFlags(
        Intent.FLAG_ACTIVITY_NEW_TASK or
                FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK
    )
    startActivity(intent)
}

private fun Fragment.startLoginActivity(
    activityClassPath: ActivityClassPath
) {
    val intent = activityClassPath.getIntent(requireContext())
    intent.addFlags(
        Intent.FLAG_ACTIVITY_NEW_TASK or
                FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK
    )
    startActivity(intent)
}

private fun Fragment.startActivity(
    activityClassPath: ActivityClassPath,
    orderData: OrderData
) {
    val intent = activityClassPath.getIntent(requireContext())
    intent.putExtra("order", Gson().toJson(orderData))
    startActivity(intent)
}

private fun FragmentActivity.startActivity(
    activityClassPath: ActivityClassPath
) {
    val intent = activityClassPath.getIntent(this)
    startActivity(intent)
}

private fun Fragment.startActivity(
    activityClassPath: ActivityClassPath,
    invoiceData: InvoiceData
) {
    val intent = activityClassPath.getIntent(requireContext())
    intent.putExtra("invoice", Gson().toJson(invoiceData))
    startActivity(intent)
}

private fun Fragment.startActivity(
    activityClassPath: ActivityClassPath,
    invoiceDetailData: InvoiceDetailData
) {
    val intent = activityClassPath.getIntent(requireContext())
    intent.putExtra("invoiceDetailData", Gson().toJson(invoiceDetailData))
    startActivity(intent)
}

private fun FragmentActivity.startActivity(
    activityClassPath: ActivityClassPath,
    invoiceDetailData: InvoiceDetailData
) {
    val intent = activityClassPath.getIntent(this)
    intent.putExtra("invoiceDetailData", Gson().toJson(invoiceDetailData))
    startActivity(intent)
}

private fun FragmentActivity.startMapActivity(
    activityClassPath: ActivityClassPath,
    lat: Double?, lng: Double?,
    resultLauncher: ActivityResultLauncher<Intent>?
) {
    val intent = activityClassPath.getIntent(this)
    if (lat != null) {
        intent.putExtra("lat", lat)
    }
    if (lat != null) {
        intent.putExtra("lng", lng)
    }
    if (resultLauncher == null) {
        ActivityCompat.startActivity(this, intent, null)
    }
    else {
        resultLauncher.launch(intent)
    }
}
