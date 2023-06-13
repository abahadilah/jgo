package adilahsoft.jgo.android.feature_payment.ui

enum class PaymentStatus {
    All {
        override fun toString() = "Semua Status"
        override fun code() = "all"
    }, Draft {
        override fun toString() = "Draft"
        override fun code() = "draft"
    }, Paid {
        override fun toString() = "Dibayar"
        override fun code() = "posted"
    }, Cancel {
        override fun code() = "cancel"
    };

    override fun toString() = "Dibatalkan"
    abstract fun code(): String?

    companion object {
        fun getStatus(status: String?): PaymentStatus? {
            PaymentStatus.values().forEach {
                if (status?.lowercase() == it.code()?.lowercase()) {
                    return it
                }
            }

            return null
        }
    }
}