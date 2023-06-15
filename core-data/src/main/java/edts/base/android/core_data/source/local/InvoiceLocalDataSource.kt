package edts.base.android.core_data.source.local

import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import id.co.edtslib.data.source.local.LocalDataSource

class InvoiceLocalDataSource(sharedPreference: SharedPreferences) :
    LocalDataSource<List<InvoiceEntity>?>(sharedPreference) {

    var key = "all"

    fun save(key: String, data: List<InvoiceEntity>?) {
        this.key = key
        save(data = data)
    }

    fun getCached(key: String): List<InvoiceEntity>? {
        this.key = key
        return getCached()
    }

    fun clearAll() {
        InvoiceStatus.values().forEach {
            clear(it.code()!!)
        }
    }

    private fun clear(key: String) {
        this.key = key
        clear()
    }

    override fun getKeyName(): String = "invoice_list_$key"

    override fun expiredInterval() = 3600
    override fun getValue(json: String): List<InvoiceEntity>? =
        Gson().fromJson(json, object : TypeToken<List<InvoiceEntity>?>() {}.type)
}