package com.nochita.shabbatCandles.receiver

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.nochita.shabbatCandles.R
import com.nochita.shabbatCandles.extentions.parseToHoursAndSeconds
import com.nochita.shabbatCandles.ui.MainFragment


class NotificationReceiver : BroadcastReceiver() {

    private val TAG = NotificationReceiver::class.java.simpleName

    override fun onReceive(context: Context, intent: Intent) {
        Log.d(TAG, "onReceive() called with: context = [$context], intent = [$intent]")

        val dateString = intent.getStringExtra(MainFragment.DATE_EXTRA)
        val parashat = intent.getStringExtra(MainFragment.PARASHAT_EXTRA)

        dateString?.let {

            val notificationId = 953
            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager?

            var builder: NotificationCompat.Builder

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channelId = "shabbat_candle_light"
                val name: CharSequence = context.getString(R.string.light_candle_notificacion)
                val description = "Channel to shabbat candle lights timming"

                val importance = NotificationManager.IMPORTANCE_HIGH
                val mChannel = NotificationChannel(channelId, name, importance)
                mChannel.description = description
                mChannel.enableLights(true)
                mChannel.lightColor = Color.RED
                mChannel.enableVibration(true)
                mChannel.vibrationPattern = longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)
                mChannel.setShowBadge(false)
                notificationManager?.createNotificationChannel(mChannel)

                builder = NotificationCompat.Builder(context, channelId)
            } else {
                builder = NotificationCompat.Builder(context)
            }

            builder.apply {
                setSmallIcon(R.drawable.ic_candle)
                setContentTitle("${parashat} at ${dateString.parseToHoursAndSeconds()}")
                setContentText(context.getString(R.string.light_candle_notificacion))
            }

            notificationManager?.notify(notificationId, builder.build())
        }
    }
}