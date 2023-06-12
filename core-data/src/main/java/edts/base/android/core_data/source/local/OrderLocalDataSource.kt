package edts.base.android.core_data.source.local

import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import id.co.edtslib.data.source.local.LocalDataSource

class OrderLocalDataSource(sharedPreference: SharedPreferences) :
    LocalDataSource<List<OrderEntity>?>(sharedPreference) {
    var key = "all"

    fun save(key: String, data: List<OrderEntity>?) {
        this.key = key
        save(data = data)
    }

    fun getCached(key: String): List<OrderEntity>? {
        this.key = key
        return getCached()
    }

    override fun getKeyName(): String = "orders_$key"

    override fun expiredInterval() = 3600
    override fun getValue(json: String): List<OrderEntity>? =
        Gson().fromJson(json, object : TypeToken<List<OrderEntity>?>() {}.type)
}