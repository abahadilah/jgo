package edts.base.android.core_data

import com.google.firebase.remoteconfig.FirebaseRemoteConfig

object RemoteConfig {
    fun getOtpInterval() = FirebaseRemoteConfig.getInstance().getLong("otp_interval")
    fun getBankList() = FirebaseRemoteConfig.getInstance().getString("bank_list")
    fun getLatitude() = FirebaseRemoteConfig.getInstance().getDouble("latitude")
    fun getLongitude() = FirebaseRemoteConfig.getInstance().getDouble("longitude")

}