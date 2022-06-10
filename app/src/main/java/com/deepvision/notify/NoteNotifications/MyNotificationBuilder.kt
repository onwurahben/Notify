package com.deepvision.notify.NoteNotifications

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import com.deepvision.notify.MainActivity


@RequiresApi(Build.VERSION_CODES.O)
class MyNotificationBuilder (val context: Context,
                             var title: String,
                             var content: String ){


    private fun createChannel(){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

            val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_LOW)
            channel.enableVibration(true)
            channel.description = "Alarms"
            channel.enableLights(true)

            val notificationManager = context.getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }

    }


    fun build(){

        createChannel()

        val intent = Intent(context,MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(context, NOTIFICATION_ID, intent, PendingIntent.FLAG_IMMUTABLE)

        val notification = NotificationCompat.Builder(context,CHANNEL_ID)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentText(content)
            .setContentTitle(title)
            .setChannelId(CHANNEL_ID)
            .build()

        NotificationManagerCompat.from(context).notify(NOTIFICATION_ID, notification)

    }


    companion object{
        const val CHANNEL_ID = "1"
        const val CHANNEL_NAME = "Reminders"
        const val NOTIFICATION_ID = 1
    }

}