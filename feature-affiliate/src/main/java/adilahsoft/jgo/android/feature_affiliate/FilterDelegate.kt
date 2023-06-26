package adilahsoft.jgo.android.feature_affiliate

import edts.base.android.core_domain.model.CustomerData

interface FilterDelegate {
    fun onSubmit(selected: CustomerData)
}