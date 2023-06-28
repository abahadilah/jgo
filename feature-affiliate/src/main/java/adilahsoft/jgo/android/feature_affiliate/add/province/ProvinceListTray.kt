package adilahsoft.jgo.android.feature_affiliate.add.province

import adilahsoft.jgo.android.feature_affiliate.R
import adilahsoft.jgo.android.feature_affiliate.databinding.TrayProvinceListBinding
import android.view.LayoutInflater
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import edts.base.android.core_domain.model.IdNameData
import edts.base.android.core_resource.base.result.JGoProcessDelegate2
import edts.base.android.core_resource.base.result.JGoProcessResult
import id.co.edtslib.data.Result
import id.co.edtslib.edtsds.bottom.BottomLayoutDialog
import id.co.edtslib.edtsds.list.menu.MenuDelegate
import id.co.edtslib.edtsds.list.menu.MenuListView

class ProvinceListTray(private val context: FragmentActivity,
    private val liveData: LiveData<Result<List<IdNameData>?>>) {
    var delegate: ProvinceDelegate? = null

    private val binding = TrayProvinceListBinding.inflate(LayoutInflater.from(context))

    fun show() {
        val listView = binding.root.findViewById<MenuListView<IdNameData>>(R.id.listView)

        liveData.observe(context) {
            JGoProcessResult(fragmentActivity = context, result = it,
                delegate = object : JGoProcessDelegate2<List<IdNameData>?> {
                    override fun success(data: List<IdNameData>?) {
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

        listView.delegate = object : MenuDelegate<IdNameData> {
            override fun onSelected(t: IdNameData) {
                delegate?.onSelected(t)
                tray.close()
            }

        }

    }
}