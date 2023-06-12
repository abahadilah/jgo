package edts.base.android.core_resource.base.result

interface UcoProcessDelegate2<T>: UcoProcessDelegate<T> {
    fun error(code: String?, message: String?)
}