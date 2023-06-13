package edts.uco.android.feature_order.ui

enum class OrderStatus {
    All {
        override fun toString() = "Semua Status"
        override fun code() = "all"
    }, Draft {
        override fun toString() = "Draft"
        override fun code() = "draft"
    }, Confirm {
        override fun toString() = "Tunggu Konfirmasi"
        override fun code() = "confirm"
    }, Driver {
        override fun toString() = "Sudah di SPJ"
        override fun code() = "driver"
    }, Pickup {
        override fun toString() = "Diperjalan"
        override fun code() = "otw"
    }, Sent {
        override fun toString() = "Selesai"
        override fun code() = "sent"
    }, Cancel {
        override fun code() = "canceled"
    };

    override fun toString() = "Dibatalkan"
    abstract fun code(): String?

    companion object {
        fun getStatus(status: String?): OrderStatus? {
            OrderStatus.values().forEach {
                if (status == it.code()) {
                    return it
                }
            }

            return null
        }
    }
}