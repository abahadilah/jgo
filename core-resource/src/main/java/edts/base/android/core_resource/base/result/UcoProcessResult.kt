package edts.base.android.core_resource.base.result

import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.fragment.app.FragmentActivity
import edts.base.android.core_resource.R
import edts.base.android.core_resource.base.ProgressView
import edts.base.core_utils.SnackBarUtils
import id.co.edtslib.data.ProcessResult
import id.co.edtslib.data.ProcessResultDelegate
import id.co.edtslib.data.Result

open class UcoProcessResult<T>(private val fragmentActivity: FragmentActivity,
                               result: Result<T>, private val delegate: UcoProcessDelegate<T>,
                               private val isShowProgressBar: Boolean = true,
                               private val errorViewContainer: View? = null,
                               private val errorConnectionDelegate: ErrorConnectionDelegate? = null,
                               private val avoidUnAuth: Boolean = false):
    ProcessResult<T>(result,
        object : ProcessResultDelegate<T> {
            override fun error(code: String?, message: String?) {
                if (isShowProgressBar) {
                    ProgressView.close()
                }

                doError(code, message)
            }

            override fun errorConnection() {
                if (errorConnectionDelegate != null) {
                    //abah ErrorConnectionDialog.show(fragmentActivity, errorConnectionDelegate)
                } else {
                    error("404", fragmentActivity.getString(R.string.err_connection_body))
                }
            }

            override fun errorSystem() {
                error("500", fragmentActivity.getString(R.string.err_system_body))
            }

            override fun loading() {
                if (isShowProgressBar) {
                    ProgressView.show(fragmentActivity)
                }
            }

            override fun success(data: T?) {
                if (isShowProgressBar) {
                    ProgressView.close()
                }

                delegate.success(data)
            }

            override fun unAuthorize(message: String?) {
                if (isShowProgressBar) {
                    ProgressView.close()
                }

                if (! avoidUnAuth) {
                    doUnAuthorize()
                }
            }

            private fun doUnAuthorize() {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse("uco://show?page=login&isSessionExpired=true")

                fragmentActivity.startActivity(intent)
            }

            private fun doError(code: String?, message: String?) {

                    if (delegate is UcoProcessDelegate2) {
                        delegate.error(code, message)
                    }
                    else
                        if (delegate is UcoProcessDelegate3) {
                            showError(fragmentActivity, message, errorViewContainer)
                            delegate.error(code, message)
                        }
                        else {
                            showError(fragmentActivity, message, errorViewContainer)
                        }
            }
        }) {
    companion object {
        fun showError(fragmentActivity: FragmentActivity, message: String?, errorViewContainer: View? = null) {
            val root = errorViewContainer ?: fragmentActivity.findViewById(android.R.id.content)
            SnackBarUtils.showSnackBar(
                fragmentActivity,
                root,
                message ?: fragmentActivity.getString(R.string.err_system_body),
                id.co.edtslib.edtsds.R.color.colorSupportError
            )
        }
    }
}