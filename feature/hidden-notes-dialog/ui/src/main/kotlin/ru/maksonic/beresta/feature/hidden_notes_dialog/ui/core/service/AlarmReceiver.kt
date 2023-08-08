package ru.maksonic.beresta.feature.hidden_notes_dialog.ui.core.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.koin.java.KoinJavaComponent.inject
import ru.maksonic.beresta.core.system.runAsync
import ru.maksonic.beresta.feature.hidden_notes_dialog.api.HiddenNotesApi

class AlarmReceiver : BroadcastReceiver() {
    private val _afterFailDelay: MutableStateFlow<Long> = MutableStateFlow(15)
    private val afterFailDelay = _afterFailDelay.asStateFlow()

    private val pinFailCounter: HiddenNotesApi.Feature.PinFailCounter by inject(HiddenNotesApi.Feature.PinFailCounter::class.java)
    private val alarmScheduler: AndroidAlarmScheduler by inject(AndroidAlarmScheduler::class.java)

    override fun onReceive(context: Context?, intent: Intent?) {
       /* Log.e("AAA", "RECEIVER CALLED")

        val message = intent?.getStringExtra("EXTRA_MESSAGE") ?: return
        // val pendingResult = goAsync()

        if (message == "ZZZ") {
            runAsync {
                // _afterFailDelay.value = _afterFailDelay.value - 1L
                pinFailCounter.updateCoolDown(afterFailDelay.value)
                repeat(14) {
                    delay(1000L)
                    _afterFailDelay.value = _afterFailDelay.value - 1L
                    pinFailCounter.updateCoolDown(afterFailDelay.value)
                }
                pinFailCounter.updateCounter(0)
                delay(1000L)
                pinFailCounter.updateCoolDown(15L)
                Log.e("AAA", "RECEIVER CANCELED")
            }*/
        }

    }
    /*override fun onReceive(context: Context?, intent: Intent?) {

            val message = intent?.getStringExtra("EXTRA_MESSAGE") ?: return
            Log.e("AAA", "SCHEDULE CANCEL")
            // val pendingResult = goAsync()

            runAsync {
                do {
                    delay(1000)
                    _afterFailDelay.value = _afterFailDelay.value - 1L
                    pinFailCounter.updateDelay(afterFailDelay.value)
                } while (afterFailDelay.value != 0L)

                pinFailCounter.updateCounter(0)
            }

        }*/

