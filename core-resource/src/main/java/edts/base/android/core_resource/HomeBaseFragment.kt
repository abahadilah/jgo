package edts.base.android.core_resource

import androidx.viewbinding.ViewBinding
import id.co.edtslib.uibase.BaseFragment

abstract class HomeBaseFragment<viewBinding : ViewBinding>: BaseFragment<viewBinding>() {
    abstract fun reselect()
}