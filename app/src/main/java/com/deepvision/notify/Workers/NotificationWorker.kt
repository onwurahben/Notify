package com.deepvision.notify.Workers

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.deepvision.notify.NoteNotifications.MyNotificationBuilder

class NotificationWorker(private val appContext: Context,
                         private val params: WorkerParameters)
    : CoroutineWorker(appContext, params) {

    companion object{
        const val WORK_NAME = "NotificationWorker"
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun doWork(): Result {

        MyNotificationBuilder (appContext,
            inputData.getString("title").toString(),
            inputData.getString("content").toString()).build()

        return Result.success()
    }
}