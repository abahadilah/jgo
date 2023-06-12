package edts.base.android.core_navigation

import android.content.Context
import android.content.Intent
import com.gaelmarhic.quadrant.App.DEEPLINK_ACTIVITY
import com.gaelmarhic.quadrant.`Feature-home`.HOME_ACTIVITY
import com.gaelmarhic.quadrant.`Feature-home`.SPLASH_ACTIVITY
import com.gaelmarhic.quadrant.`Feature-auth`.LOGIN_ACTIVITY
import com.gaelmarhic.quadrant.`Feature-auth`.OTP_ACTIVITY
import com.gaelmarhic.quadrant.`Feature-auth`.CREATE_PIN_ACTIVITY
import com.gaelmarhic.quadrant.`Feature-auth`.CREATE_PIN_CONFIRMATION_ACTIVITY
import com.gaelmarhic.quadrant.`Feature-auth`.FORGOT_OTP_ACTIVITY
import com.gaelmarhic.quadrant.`Feature-auth`.LOGIN_PIN_ACTIVITY
import com.gaelmarhic.quadrant.`Feature-auth`.FORGOT_PIN_ACTIVITY
import com.gaelmarhic.quadrant.`Feature-auth`.FORGOT_RESET_PIN_ACTIVITY
import com.gaelmarhic.quadrant.`Feature-map`.MAP_ACTIVITY
import com.gaelmarhic.quadrant.`Feature-activity`.INVOICE_DETAIL_ACTIVITY
import com.gaelmarhic.quadrant.`Feature-map`.CHECK_PRICE_ACTIVITY
import com.gaelmarhic.quadrant.`Feature-profile`.CHANGE_PIN_ACTIVITY
import com.gaelmarhic.quadrant.`Feature-profile`.CHANGE_PROFILE_ACTIVITY
import com.gaelmarhic.quadrant.`Feature-pickup`.ORDER_DETAIL_ACTIVITY

enum class ActivityClassPath(private val className: String) {
    Home(HOME_ACTIVITY),
    Splash(SPLASH_ACTIVITY),
    Deeplink(DEEPLINK_ACTIVITY),
    Login(LOGIN_ACTIVITY),
    Otp(OTP_ACTIVITY),
    CreatePin(CREATE_PIN_ACTIVITY),
    CreatePinConfirmation(CREATE_PIN_CONFIRMATION_ACTIVITY),
    LoginPin(LOGIN_PIN_ACTIVITY),
    ForgotPin(FORGOT_PIN_ACTIVITY),
    ForgotOtp(FORGOT_OTP_ACTIVITY),
    ForgotResetPin(FORGOT_RESET_PIN_ACTIVITY),
    CheckPrice(CHECK_PRICE_ACTIVITY),
    Map(MAP_ACTIVITY),
    ChangePin(CHANGE_PIN_ACTIVITY),
    OrderDetail(ORDER_DETAIL_ACTIVITY),
    InvoiceDetail(INVOICE_DETAIL_ACTIVITY),
    ChangeProfile(CHANGE_PROFILE_ACTIVITY);

    fun getIntent(context: Context) = Intent(context, Class.forName(className))
}