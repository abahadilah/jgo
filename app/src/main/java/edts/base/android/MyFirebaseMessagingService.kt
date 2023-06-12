package edts.base.android

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.base.android.R
import com.bumptech.glide.Glide
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import edts.base.android.core_domain.usecase.ConfigurationUseCase
import edts.base.android.core_domain.usecase.OrderUseCase
import io.karn.notify.Notify
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.util.concurrent.ExecutionException

class MyFirebaseMessagingService: FirebaseMessagingService(), KoinComponent {
    private val configurationUseCase: ConfigurationUseCase by inject()

    companion object {
        var notifyId: Int = 0
    }

    override fun onNewToken(token: String) {
        // send token to server
        super.onNewToken(token)

        configurationUseCase.clearFcmId()

    }

    override fun handleIntent(intent: Intent?) {
        try {
            super.handleIntent(intent)
            //salesRequestUseCase.clearCache()
        }
        catch (ignore: Exception) {
        }
    }

    override fun onMessageReceived(message: RemoteMessage) {
        LocalBroadcastManager.getInstance(this).sendBroadcast(Intent().setAction("UCO_PUSH"))

        if (message.notification?.body?.isNotEmpty() == true) {
            val notify = message.notification?.let {
                Notify.with(this).header {
                    icon = R.drawable.ic_notif
                }.meta {
                    val intent = Intent(
                        applicationContext,
                        DeeplinkActivity::class.java
                    )

                    clickIntent = PendingIntent.getActivity(applicationContext, notifyId, intent,
                        getPendingIntentFlag())
                }
            }
            val builder = notify?.asBuilder()
            builder?.setContentTitle(message.notification?.title)
            builder?.setContentText(message.notification?.body)

            if (message.notification?.imageUrl != null) {
                try {
                    val futureBitmap = Glide.with(applicationContext)
                        .asBitmap()
                        .load(message.notification?.imageUrl!!)
                        .submit()
                    val bitmap = futureBitmap.get()
                    builder?.setLargeIcon(bitmap)
                    builder?.setStyle(
                        NotificationCompat.BigPictureStyle()
                            .bigPicture(bitmap)
                            .bigLargeIcon(null)
                            .setSummaryText(message.notification?.body)
                    )
                } catch (e: OutOfMemoryError) {
                    builder?.setStyle(
                        NotificationCompat.BigTextStyle()
                            .bigText(message.notification?.body)
                    )
                } catch (e: Exception) {
                    builder?.setStyle(
                        NotificationCompat.BigTextStyle()
                            .bigText(message.notification?.body)
                    )
                } catch (e: ExecutionException) {
                    builder?.setStyle(
                        NotificationCompat.BigTextStyle()
                            .bigText(message.notification?.body)
                    )
                }
            } else {
                builder?.setStyle(
                    NotificationCompat.BigTextStyle().bigText(message.notification?.body)
                )
            }


            val notificationManager = getSystemService(NOTIFICATION_SERVICE)
            if (notificationManager is NotificationManager) {
                try {
                    if (notifyId >= Int.MAX_VALUE) {
                        notifyId = 0
                    }

                    notificationManager.notify(notifyId++, builder?.build())
                } catch (ignore: RuntimeException) {

                }
            }
        }
    }

    private fun getPendingIntentFlag(): Int {
        return PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
    }
}