package edts.base.android.core_resource.base.result

interface JGoProcessDelegate2<T>: JGoProcessDelegate<T> {
    fun error(code: String?, message: String?)
}