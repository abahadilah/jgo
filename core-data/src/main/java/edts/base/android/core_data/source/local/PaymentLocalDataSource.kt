package edts.base.android.core_data.source.local

import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import id.co.edtslib.data.source.local.LocalDataSource

class PaymentLocalDataSource(sharedPreference: SharedPreferences) :
    LocalDataSource<List<PaymentEntity>?>(sharedPreference) {

    var key = "all"

    fun save(key: String, data: List<PaymentEntity>?) {
        this.key = key
        save(data = data)
    }

    fun getCached(key: String): List<PaymentEntity>? {
        this.key = key
        return getCached()
    }

    override fun getKeyName(): String = "payment_list_$key"

    override fun expiredInterval() = 3600
    override fun getValue(json: String): List<PaymentEntity>? =
        Gson().fromJson(json, object : TypeToken<List<PaymentEntity>?>() {}.type)
}