package com.deepvision.notify.Workers

import android.content.Context
import androidx.work.*
import java.util.concurrent.TimeUnit

class MyWorkManager() {


    fun setUpWork( noteText:String, context: Context ){

        val constraints = setUpConstraints()
        val notificationWorkRequest = setUpWorkRequest(noteText, constraints)

        WorkManager.getInstance(context).enqueueUniqueWork(

            NotificationWorker.WORK_NAME,
            ExistingWorkPolicy.KEEP,
            notificationWorkRequest
        )
    }

    private fun setUpWorkRequest(
        noteText: String,
        constraints: Constraints
    ): OneTimeWorkRequest {
        return OneTimeWorkRequestBuilder<NotificationWorker>()
            .setInputData(
                workDataOf(
                    "title" to "Notification",
                    "content" to noteText
                )
            )
            .setInitialDelay(5,TimeUnit.DAYS)
            .setConstraints(constraints)
            .build()
    }

    private fun setUpConstraints(): Constraints {
        return Constraints.Builder()
            .setRequiredNetworkType(NetworkType.NOT_REQUIRED)
            .setRequiresBatteryNotLow(false)
            .setRequiresCharging(false)
            .setRequiresDeviceIdle(false)
            .build()
    }
}