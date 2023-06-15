package edts.base.android.core_data.source.local

import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import id.co.edtslib.data.source.local.LocalDataSource

class CreateOrderOriginLocalDataSource(sharedPreference: SharedPreferences) :
    LocalDataSource<List<CreateOrderAddressEntity>?>(sharedPreference) {

    override fun getKeyName(): String = "origin_address"

    override fun getValue(json: String): List<CreateOrderAddressEntity>? =
        Gson().fromJson(json, object : TypeToken<List<CreateOrderAddressEntity>?>() {}.type)
}