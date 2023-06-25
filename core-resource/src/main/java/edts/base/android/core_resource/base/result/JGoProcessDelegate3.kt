package edts.base.android.core_resource.base.result

interface JGoProcessDelegate3<T>: JGoProcessDelegate<T> {
    fun error(code: String?, message: String?)
}