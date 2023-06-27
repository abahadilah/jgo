package adilahsoft.jgo.android.feature_affiliate.add

import adilahsoft.jgo.android.feature_affiliate.databinding.ActivityAddCustomerBinding
import android.view.LayoutInflater
import id.co.edtslib.uibase.PopupActivity

class CustomerAddActivity: PopupActivity<ActivityAddCustomerBinding>() {
    override val bindingInflater: (LayoutInflater) -> ActivityAddCustomerBinding
        get() = ActivityAddCustomerBinding::inflate

    override fun getTrackerPageName(): String?  = null

    override fun setupPopup() {
    }
}