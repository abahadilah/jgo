package edts.uco.android.feature_notification.ui

import edts.base.android.core_domain.model.NotificationData
import edts.uco.android.feature_notification.databinding.AdapterNotificationBinding
import id.co.edtslib.baserecyclerview.BaseRecyclerViewAdapterDelegate
import id.co.edtslib.baserecyclerview.BaseViewHolder

class NotificationHolder(private val binding: AdapterNotificationBinding): 
    BaseViewHolder<NotificationData>(binding) {
    override fun setData(
        list: MutableList<NotificationData>,
        position: Int,
        delegate: BaseRecyclerViewAdapterDelegate<NotificationData>?
    ) {
        with(list[position]) {
            binding.tvTitle.text = title
            binding.tvDescription.text = description
            binding.tvTime.text = getTimeFormat()
        }
    }
}