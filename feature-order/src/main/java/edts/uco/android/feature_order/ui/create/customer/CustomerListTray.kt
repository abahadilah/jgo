package edts.uco.android.feature_order.ui.create.customer

import adilahsoft.jgo.android.feature_affiliate.R
import android.view.LayoutInflater
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import edts.base.android.core_domain.model.CustomerData
import edts.base.android.core_resource.base.result.JGoProcessDelegate2
import edts.base.android.core_resource.base.result.JGoProcessResult
import edts.uco.android.feature_order.databinding.TrayCustomerListBinding
import id.co.edtslib.data.Result
import id.co.edtslib.edtsds.bottom.BottomLayoutDialog
import id.co.edtslib.edtsds.list.menu.MenuDelegate
import id.co.edtslib.edtsds.list.menu.MenuListView

class CustomerListTray(private val context: FragmentActivity,
                       private val liveData: LiveData<Result<List<CustomerData>?>>) {
    var delegate: CustomerDelegate? = null

    private val binding = TrayCustomerListBinding.inflate(LayoutInflater.from(context))

    fun show() {
        val listView = binding.root.findViewById<MenuListView<CustomerData>>(R.id.listView)

        liveData.observe(context) {
            JGoProcessResult(fragmentActivity = context, result = it,
                delegate = object : JGoProcessDelegate2<List<CustomerData>?> {
                    override fun success(data: List<CustomerData>?) {
                        listView.data = data ?: listOf()
                    }

                    override fun error(code: String?, message: String?) {
                        JGoProcessResult.showError(fragmentActivity = context,
                            message = message,
                            errorViewContainer = binding.root)
                    }

                })
        }

        val tray = BottomLayoutDialog.showTray(context = context,
            title = context.getString(R.string.affiliate_choose_province),
            contentView = binding.root)

        listView.delegate = object : MenuDelegate<CustomerData> {
            override fun onSelected(t: CustomerData) {
                delegate?.onSelected(t)
                tray.close()
            }

        }

    }
}