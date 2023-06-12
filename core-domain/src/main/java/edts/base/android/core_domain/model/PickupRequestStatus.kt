package edts.base.android.core_domain.model

enum class PickupRequestStatus {
    Processed {
        override fun toString() = "Permintaan Diproses"
        override fun code() = "approved"
        override fun textColor() = "#FF7D1D"
        override fun bgColor() = "#FFF0E6"
        override fun canCancelled() = true
        override fun canDone() = true
    }, Cancelled {
        override fun toString() = "Dibatalkan"
        override fun code() = "canceled"
        override fun textColor() = "#654D40"
        override fun bgColor() = "#FFEDEE"
        override fun canCancelled() = false
        override fun canDone() = false
    }, Rejected {
        override fun toString() = "Ditolak"
        override fun code() = "rejected"
        override fun textColor() = "#A92C23"
        override fun bgColor() = "#FFEDEE"
        override fun canCancelled() = false
        override fun canDone() = false
    }, Completed {
        override fun toString() = "Selesai"
        override fun code() = "completed"
        override fun textColor() = "#8FC742"
        override fun bgColor() = "#EBFFD0"
        override fun canCancelled() = false
        override fun canDone() = false
    }, WaitingPickup {
        override fun toString() = "Menunggu Pickup"
        override fun code() = "waiting_pick_up"
        override fun textColor() = "#368BE2"
        override fun bgColor() = "#E6F2F9"
        override fun canCancelled() = false
        override fun canDone() = true
    }, Pickup {
        override fun toString() = "Proses Pengambilan"
        override fun code() = "picking_up_process"
        override fun textColor() = "#1A5A8C"
        override fun bgColor() = "#CCE5FF"
        override fun canCancelled() = false
        override fun canDone() = true
    },
    OnReview {
        override fun code() = "on_review"
        override fun textColor() = "#D39C2F"
        override fun bgColor() = "#FEF5E1"
        override fun canCancelled() = true
        override fun canDone() = false
        override fun toString() = "Menunggu Persetujuan"
    },
    Pending {
        override fun code() = "waiting_approval"
        override fun textColor() = "#D39C2F"
        override fun bgColor() = "#FEF5E1"
        override fun canCancelled() = true
        override fun canDone() = false
    };

    override fun toString() ="Menunggu Persetujuan"
    abstract fun code(): String
    abstract fun textColor(): String
    abstract fun bgColor(): String

    abstract fun canCancelled(): Boolean
    abstract fun canDone(): Boolean

    companion object {
        fun get(code: String?): PickupRequestStatus? {
            PickupRequestStatus.values().forEach {
                if (it.code() == code) {
                    return it
                }
            }

            return null
        }
    }
}