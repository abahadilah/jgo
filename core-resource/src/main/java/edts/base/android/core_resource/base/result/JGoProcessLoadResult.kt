package edts.base.android.core_resource.base.result

import android.view.View
import androidx.fragment.app.FragmentActivity
import id.co.edtslib.data.Result

class JGoProcessLoadResult<T>(fragmentActivity: FragmentActivity, result: Result<T>,
                              delegate: JGoProcessDelegate<T>, errorViewContainer: View? = null,
                              errorConnectionDelegate: ErrorConnectionDelegate? = null,
                              avoidUnAuth: Boolean = false):
    JGoProcessResult<T>(fragmentActivity = fragmentActivity, result = result, delegate = delegate,
        isShowProgressBar = false, errorViewContainer = errorViewContainer,
        errorConnectionDelegate = errorConnectionDelegate, avoidUnAuth = avoidUnAuth)