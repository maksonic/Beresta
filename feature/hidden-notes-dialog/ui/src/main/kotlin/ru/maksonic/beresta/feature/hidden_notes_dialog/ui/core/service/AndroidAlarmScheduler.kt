package ru.maksonic.beresta.feature.hidden_notes_dialog.ui.core.service

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset


class AndroidAlarmScheduler(
    private val context: Context
) : AlarmScheduler {

    private companion object {
        private const val ALARM_CODE = 44444
    }

    private val alarmManager = context.getSystemService(AlarmManager::class.java)

    override fun schedule() {
        val intent = Intent(context, CounterService::class.java)
        context.startService(intent)
    /* val intent = Intent(context, AlarmReceiver::class.java).apply {
            putExtra("EXTRA_MESSAGE", "ZZZ")
        }
        Log.e("AAA", "SCHEDULE START")
        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            //time.atZone(ZoneId.systemDefault()).toEpochSecond() * 1000,
            LocalDateTime.now().atZone(ZoneId.systemDefault()).toEpochSecond() * 1000,
            PendingIntent.getBroadcast(
                context,
                ALARM_CODE,
                intent.addFlags(Intent.FLAG_RECEIVER_FOREGROUND),
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
        )*/
    }

    override fun cancel() {
        val intent = PendingIntent.getBroadcast(
            context,
            ALARM_CODE,
            Intent(context, AlarmReceiver::class.java),
            PendingIntent.FLAG_CANCEL_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        alarmManager.cancel(intent)
    }
}

/*val CODEZ = 44444

class AndroidAlarmScheduler(
    private val context: Context
) : AlarmScheduler {

    private val alarmManager = context.getSystemService(AlarmManager::class.java)

    override fun schedule() {
        val time = LocalDateTime.now().plusSeconds(10)
        val intent = Intent(context, AlarmReceiver::class.java).apply {
            putExtra("EXTRA_MESSAGE", "ZZZ")
        }
        Log.e("AAA", "SCHEDULE START")
        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            time.atZone(ZoneId.systemDefault()).toEpochSecond() * 1000,
            PendingIntent.getBroadcast(
                context,
                CODEZ,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
        )
    }

    override fun cancel() {
        alarmManager.cancel(
            PendingIntent.getBroadcast(
                context,
                CODEZ,
                Intent(context, AlarmReceiver::class.java),
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
        )
    }
}*/