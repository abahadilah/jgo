package edts.uco.android.feature_activity.ui

enum class InvoiceStatus {
    All {
        override fun toString() = "Semua Status"
        override fun code() = "all"
    }, Draft {
        override fun toString() = "Belum Dibayar"
        override fun code() = "not_paid"
    }, Confirm {
        override fun toString() = "Dalam Pembayaran"
        override fun code() = "in_payment"
    }, Paid {
        override fun toString() = "Lunas"
        override fun code() = "paid"
    }, Partial {
        override fun toString() = "Dibayar Sebagian"
        override fun code() = "partial"
    }, Reversed {
        override fun toString() = "Dikembalikan"
        override fun code() = "reversed"
    }, Cancel {
        override fun code() = "invoicing_legacy"
    };

    override fun toString() = "Invoice Arsip"
    abstract fun code(): String?

    companion object {
        fun getStatus(status: String?): InvoiceStatus? {
            InvoiceStatus.values().forEach {
                if (status == it.code()) {
                    return it
                }
            }

            return null
        }
    }
}