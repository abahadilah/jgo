package edts.base.android.core_resource.base.result

interface JGoProcessDelegate<T> {
    fun success(data: T?)
}