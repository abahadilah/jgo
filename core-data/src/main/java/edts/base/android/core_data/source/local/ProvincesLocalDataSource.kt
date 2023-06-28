package edts.base.android.core_data.source.local

import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import id.co.edtslib.data.source.local.LocalDataSource

class ProvincesLocalDataSource(sharedPreference: SharedPreferences) :
    LocalDataSource<List<IdNameEntity>?>(sharedPreference) {
    override fun getKeyName(): String = "provinces"
    override fun getValue(json: String): List<IdNameEntity>? =
        Gson().fromJson(json, object : TypeToken<List<IdNameEntity>?>() {}.type)
}