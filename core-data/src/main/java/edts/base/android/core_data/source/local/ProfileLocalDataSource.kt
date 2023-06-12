package edts.base.android.core_data.source.local

import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import id.co.edtslib.data.source.local.LocalDataSource

class ProfileLocalDataSource(sharedPreference: SharedPreferences) :
    LocalDataSource<ProfileEntity?>(sharedPreference) {
    override fun getKeyName(): String = "profile"
    override fun getValue(json: String): ProfileEntity? =
        Gson().fromJson(json, object : TypeToken<ProfileEntity?>() {}.type)
}