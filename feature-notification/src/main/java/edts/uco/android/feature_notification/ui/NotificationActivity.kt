package edts.uco.android.feature_notification.ui

import android.view.LayoutInflater
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import edts.base.android.core_domain.model.NotificationData
import edts.base.android.core_resource.base.result.JGoProcessDelegate
import edts.base.android.core_resource.base.result.JGoProcessLoadResult
import edts.uco.android.feature_notification.databinding.ActivityNotificationBinding
import id.co.edtslib.uibase.PopupActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class NotificationActivity: PopupActivity<ActivityNotificationBinding>() {
    private val viewModel: NotificationViewModel by viewModel()
    private val adapter = NotificationAdapter()

    override val bindingInflater: (LayoutInflater) -> ActivityNotificationBinding
        get() = ActivityNotificationBinding::inflate

    override fun getTrackerPageName(): String?  = null

    override fun setupPopup() {
        setupListener()
        setupView()
        loadData(false)
    }

    private fun setupListener() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.swipeRefreshLayout.isRefreshing = false
            loadData(true)
        }
    }

    private fun setupView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }

    private fun loadData(isReload: Boolean) {
        showShimmer()

        viewModel.get(isReload).observe(this) {
            JGoProcessLoadResult(fragmentActivity = this, result = it,
                delegate = object : JGoProcessDelegate<List<NotificationData>?> {
                    override fun success(data: List<NotificationData>?) {
                        processData(data)
                    }

                })
        }
    }

    private fun showShimmer() {
        binding.clEmpty.isVisible = false
        binding.recyclerView.isVisible = false
        binding.shimmerLayout.show()
    }

    private fun processData(data: List<NotificationData>?) {
        binding.shimmerLayout.hide()
        binding.clEmpty.isVisible = data?.isNotEmpty() != true
        binding.recyclerView.isVisible = data?.isNotEmpty() == true

        adapter.data = data
    }
}