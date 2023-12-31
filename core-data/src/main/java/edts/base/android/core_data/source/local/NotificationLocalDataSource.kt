package edts.base.android.core_data.source.local

import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import id.co.edtslib.data.source.local.LocalDataSource

class NotificationLocalDataSource(sharedPreference: SharedPreferences) :
    LocalDataSource<List<NotificationEntity>?>(sharedPreference) {
    override fun expiredInterval() = 24*3600

    override fun getKeyName(): String = "notifications"

    override fun getValue(json: String): List<NotificationEntity>? =
        Gson().fromJson(json, object : TypeToken<List<NotificationEntity>?>() {}.type)
}