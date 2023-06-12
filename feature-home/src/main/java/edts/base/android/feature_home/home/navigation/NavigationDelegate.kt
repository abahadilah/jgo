package edts.base.android.feature_home.home.navigation

import edts.base.android.core_resource.HomeBaseFragment

interface NavigationDelegate {
    fun unselected(fragment: HomeBaseFragment<*>)
    fun reselected(fragment: HomeBaseFragment<*>)
    fun selected(fragment: HomeBaseFragment<*>)
}