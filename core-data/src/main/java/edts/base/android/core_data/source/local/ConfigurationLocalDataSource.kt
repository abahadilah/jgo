package edts.base.android.core_data.source.local

import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import id.co.edtslib.data.source.local.LocalDataSource

class ConfigurationLocalDataSource(sharedPreference: SharedPreferences) :
    LocalDataSource<ConfigurationEntity?>(sharedPreference) {
    override fun getKeyName(): String = "configuration"
    override fun getValue(json: String): ConfigurationEntity? =
        Gson().fromJson(json, object : TypeToken<ConfigurationEntity?>() {}.type)
}