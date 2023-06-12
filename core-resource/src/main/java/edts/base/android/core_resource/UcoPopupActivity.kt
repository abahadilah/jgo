package edts.base.android.core_resource

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.viewbinding.ViewBinding
import com.google.android.gms.tasks.Task
import com.google.firebase.messaging.FirebaseMessaging
import id.co.edtslib.uibase.PopupActivity
import org.koin.androidx.viewmodel.ext.android.viewModel


abstract class UcoPopupActivity<viewBinding : ViewBinding> : PopupActivity<viewBinding>() {
    open fun reload() {}

    private val ucoViewModel: UcoViewModel by viewModel()

    private val broadcastReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent) {
            reload()
        }
    }

    override fun onStart() {
        super.onStart()

        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver, IntentFilter("UCO_PUSH"))
    }

    override fun onStop() {
        super.onStop()
        LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcastReceiver)
    }

    private fun getToken(onCompleteListener: (Task<String>) -> Unit) {
        ucoViewModel.getConfiguration().observe(this) {configuration ->
            if (configuration?.fcmId?.isNotEmpty() != true) {
                try {
                    FirebaseMessaging.getInstance().token.addOnCompleteListener {
                        onCompleteListener(it)
                    }
                }
                catch (ignore: Exception) {

                }
            }
        }
    }
}