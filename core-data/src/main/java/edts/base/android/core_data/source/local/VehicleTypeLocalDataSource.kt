package edts.base.android.core_data.source.local

import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import id.co.edtslib.data.source.local.LocalDataSource

class VehicleTypeLocalDataSource(sharedPreference: SharedPreferences) :
    LocalDataSource<List<VehicleTypeEntity>?>(sharedPreference) {

    override fun getKeyName(): String = "vehicles_type"

    override fun getValue(json: String): List<VehicleTypeEntity>? =
        Gson().fromJson(json, object : TypeToken<List<VehicleTypeEntity>?>() {}.type)
}