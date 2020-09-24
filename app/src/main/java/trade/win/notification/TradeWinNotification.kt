package trade.win.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import trade.win.R
import trade.win.SplashActivity
import java.util.*

class TradeWinNotification : FirebaseMessagingService() {

    override fun onMessageReceived(p0: RemoteMessage) {
        super.onMessageReceived(p0)
        sendNotification(p0)
    }

    // send notification
    private fun sendNotification(remoteMessage: RemoteMessage) {
        var intent : Intent? = null
        var notification = remoteMessage.notification
        intent = Intent(this, SplashActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

//        intent.putExtra(SplashActivity.NOTIFY, "body")

        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT)
        val CHANNEL_ID = "my_channel_01"
        val defaultSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val builder = NotificationCompat.Builder(this).apply {
            setSmallIcon(R.mipmap.ic_push_noti)
            setContentTitle(notification?.title)
            color = resources.getColor(R.color.colorIconPush)
            setChannelId(CHANNEL_ID)
            setContentText(notification?.body)
            setAutoCancel(true)
            setSound(defaultSound)
            setContentIntent(pendingIntent)
        }
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val name = getString(R.string.default_notification_channel_id)
        val importance = NotificationManager.IMPORTANCE_HIGH
        val mChannel = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel(CHANNEL_ID, name, importance)
        } else {

        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.createNotificationChannel(mChannel as NotificationChannel)
        }
        val randomId = Random().nextInt(999 - 1) + 1
        notificationManager.notify(randomId, builder.build())

    }
}