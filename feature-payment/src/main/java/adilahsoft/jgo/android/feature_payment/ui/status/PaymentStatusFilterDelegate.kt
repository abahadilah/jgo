package adilahsoft.jgo.android.feature_payment.ui.status

import edts.base.android.core_data.source.local.PaymentStatus

interface PaymentStatusFilterDelegate {
    fun onSubmit(selected: PaymentStatus)
}