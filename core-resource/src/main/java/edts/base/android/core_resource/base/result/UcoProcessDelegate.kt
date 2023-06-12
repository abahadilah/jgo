package edts.base.android.core_resource.base.result

interface UcoProcessDelegate<T> {
    fun success(data: T?)
}