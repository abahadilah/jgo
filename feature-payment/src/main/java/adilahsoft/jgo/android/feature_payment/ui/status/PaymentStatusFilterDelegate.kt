package adilahsoft.jgo.android.feature_payment.ui.status

import adilahsoft.jgo.android.feature_payment.ui.PaymentStatus

interface PaymentStatusFilterDelegate {
    fun onSubmit(selected: PaymentStatus)
}