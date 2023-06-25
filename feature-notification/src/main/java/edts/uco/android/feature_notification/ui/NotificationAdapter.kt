package edts.uco.android.feature_notification.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import edts.base.android.core_domain.model.NotificationData
import edts.uco.android.feature_notification.databinding.AdapterNotificationBinding
import id.co.edtslib.baserecyclerview.BaseRecyclerViewAdapter

class NotificationAdapter: BaseRecyclerViewAdapter<AdapterNotificationBinding, NotificationData>() {
    var data: List<NotificationData>? = null
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value

            list.clear()
            if (value != null) {
                list.addAll(value)
            }

            notifyDataSetChanged()
        }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> AdapterNotificationBinding
        get() = AdapterNotificationBinding::inflate

    override fun createHolder() = NotificationHolder(binding)
}