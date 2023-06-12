package edts.base.android.core_resource.base.result

import android.view.View
import androidx.fragment.app.FragmentActivity
import id.co.edtslib.data.Result

class UcoProcessLoadResult<T>(fragmentActivity: FragmentActivity, result: Result<T>,
                              delegate: UcoProcessDelegate<T>, errorViewContainer: View? = null,
                              errorConnectionDelegate: ErrorConnectionDelegate? = null,
                              avoidUnAuth: Boolean = false):
    UcoProcessResult<T>(fragmentActivity = fragmentActivity, result = result, delegate = delegate,
        isShowProgressBar = false, errorViewContainer = errorViewContainer,
        errorConnectionDelegate = errorConnectionDelegate, avoidUnAuth = avoidUnAuth)