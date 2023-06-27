package edts.base.android.core_navigation

import android.content.Context
import android.content.Intent
import com.gaelmarhic.quadrant.`Feature-home`.HOME_ACTIVITY
import com.gaelmarhic.quadrant.`Feature-auth`.LOGIN_ACTIVITY
import com.gaelmarhic.quadrant.`Feature-map`.MAP_ACTIVITY
import com.gaelmarhic.quadrant.`Feature-invoice`.INVOICE_DETAIL_ACTIVITY
import com.gaelmarhic.quadrant.`Feature-map`.CHECK_PRICE_ACTIVITY
import com.gaelmarhic.quadrant.`Feature-order`.ORDER_DETAIL_ACTIVITY
import com.gaelmarhic.quadrant.`Feature-order`.CREATE_ORDER_ACTIVITY
import com.gaelmarhic.quadrant.`Feature-notification`.NOTIFICATION_ACTIVITY
import com.gaelmarhic.quadrant.`Feature-affiliate`.CUSTOMER_ACTIVITY
import com.gaelmarhic.quadrant.`Feature-affiliate`.CUSTOMER_ADD_ACTIVITY

enum class ActivityClassPath(private val className: String) {
    Home(HOME_ACTIVITY),
    Login(LOGIN_ACTIVITY),
    CheckPrice(CHECK_PRICE_ACTIVITY),
    Map(MAP_ACTIVITY),
    OrderDetail(ORDER_DETAIL_ACTIVITY),
    InvoiceDetail(INVOICE_DETAIL_ACTIVITY),
    CreateOrder(CREATE_ORDER_ACTIVITY),
    Notification(NOTIFICATION_ACTIVITY),
    Customer(CUSTOMER_ACTIVITY),
    AddCustomer(CUSTOMER_ADD_ACTIVITY);

    fun getIntent(context: Context) = Intent(context, Class.forName(className))
}