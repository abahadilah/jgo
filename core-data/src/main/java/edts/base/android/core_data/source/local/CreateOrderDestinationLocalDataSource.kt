package edts.base.android.core_data.source.local

import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import id.co.edtslib.data.source.local.LocalDataSource

class CreateOrderDestinationLocalDataSource(sharedPreference: SharedPreferences) :
    LocalDataSource<List<CreateOrderAddressEntity>?>(sharedPreference) {

    override fun getKeyName(): String = "destination_addresses"

    override fun getValue(json: String): List<CreateOrderAddressEntity>? =
        Gson().fromJson(json, object : TypeToken<List<CreateOrderAddressEntity>?>() {}.type)

    fun add(item: CreateOrderAddressEntity) {
        var cache = getCached()
        if (cache == null) {
            cache = listOf()
        }

        val mutable = cache.toMutableList()
        val found = mutable.find {
            it == item
        }

        if (found != null) {
            mutable.remove(found)
        }

        mutable.add(0, item)
        while (mutable.size > 10) {
            mutable.removeLast()
        }

        save(mutable)
    }
}