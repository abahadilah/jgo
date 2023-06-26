package edts.base.android.core_data.source.local

import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import id.co.edtslib.data.source.local.LocalDataSource

class CustomerLocalDataSource(sharedPreference: SharedPreferences) :
    LocalDataSource<List<CustomerEntity>?>(sharedPreference) {
    override fun expiredInterval() = 24*3600

    override fun getKeyName(): String = "customers"

    override fun getValue(json: String): List<CustomerEntity>? =
        Gson().fromJson(json, object : TypeToken<List<CustomerEntity>?>() {}.type)
}