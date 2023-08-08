/*
package ru.maksonic.beresta.feature.hidden_notes_dialog.ui.core.service

import android.app.ActivityManager
import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.core.content.ContextCompat.getSystemService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import ru.maksonic.beresta.feature.hidden_notes_dialog.api.HiddenNotesApi


*/
/**
 * @Author maksonic on 06.08.2023
 *//*


class CounterStopper : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        context?.stopService(Intent(context, CounterService::class.java))
    }
}

class CounterStarter : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        context?.startService(Intent(context, CounterService::class.java))
    }
}

*/
/*
*  val stopper = Intent(applicationContext, CounterStopper::class.java)
                applicationContext.sendBroadcast(stopper)
                * *//*


class CounterService : Service() {
    companion object {
        private val instance: MutableStateFlow<CounterService?> = MutableStateFlow(null)
        val isRunning: Boolean get() = instance.asStateFlow().value != null
    }

    private val pinFailCounter: HiddenNotesApi.Feature.PinFailCounter by inject()
    private val _cachedTick: MutableStateFlow<Int> = MutableStateFlow(15)
    private val cachedTick = _cachedTick.asStateFlow()


    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.e("AAA", "service started")
        instance.update { this }
        CoroutineScope(SupervisorJob() + Dispatchers.IO).launch {
            pinFailCounter.state.collectLatest {
                val tick = it.tick
                if (tick >= 0) {
                    _cachedTick.value = it.tick

                    pinFailCounter.updateTick(cachedTick.value)
                    repeat(cachedTick.value) {
                        delay(1000)
                        _cachedTick.value = _cachedTick.value - 1
                        if (cachedTick.value >= 0) {
                            pinFailCounter.updateTick(cachedTick.value)
                        }
                    }
                    pinFailCounter.updateCounter(0)
                    val stopper = Intent(applicationContext, CounterStopper::class.java)
                    applicationContext.sendBroadcast(stopper)
                }
            }

        }.run { return START_NOT_STICKY }
    }
    */
/*override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.e("AAA", "service started")
        instance.update { this }
        CoroutineScope(SupervisorJob() + Dispatchers.IO).launch {
            pinFailCounter.state.collect {
                val tick = it.tick
                if (tick >= 0) {
                    _cachedTick.value = it.tick

                    pinFailCounter.updateTick(cachedTick.value)
                    repeat(cachedTick.value) {
                        delay(1000)
                        _cachedTick.value = _cachedTick.value - 1
                        if (cachedTick.value >= 0) {
                            pinFailCounter.updateTick(cachedTick.value)
                        }
                    }
                    pinFailCounter.updateCounter(0)
                    val stopper = Intent(applicationContext, CounterStopper::class.java)
                    applicationContext.sendBroadcast(stopper)
                }
            }
        }.run { return START_NOT_STICKY }
    }*//*


    override fun onDestroy() {
        Log.e("AAA", "service destroyed")
        instance.update { null }
        super.onDestroy()
    }
    */
/*
        override fun onDestroy() {
            CoroutineScope(SupervisorJob() + Dispatchers.IO).launch {
                if (cachedTick.value > 0) {
                    pinFailCounter.updateTick(cachedTick.value)
                }
            }
            Log.e("AAA", "service destroy")
            super.onDestroy()
        }*//*

}

*/
/*
class CounterService : Service() {
    private companion object {
        private const val PIN_SERVICE_CODE = 44444
    }

    private val pinFailCounter: HiddenNotesApi.Feature.PinFailCounter by inject()
    private val _afterFailDelay: MutableStateFlow<Long> = MutableStateFlow(15)
    private val afterFailDelay = _afterFailDelay.asStateFlow()

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        CoroutineScope(SupervisorJob() + Dispatchers.IO).launch {
            pinFailCounter.state.collect {
                val timestamp = it.timestampEpochMilli
                val currentTime = LocalDateTime.now().atZone(ZoneId.systemDefault()).toEpochSecond()
                val timeDiff = currentTime - timestamp

                if (timeDiff == 0L) {
                    pinFailCounter.reset()
                } else {
                    pinFailCounter.updateDelay(timeDiff)
                    repeat((timeDiff - 1).toInt()) {
                        delay(1000L)
                        _afterFailDelay.value = _afterFailDelay.value - 1L
                        pinFailCounter.updateDelay(afterFailDelay.value)
                    }
                }

                pinFailCounter.updateCounter(0)
                delay(1000L)
                pinFailCounter.updateDelay(15L)
            }
        }.run { return START_STICKY }
    }

    override fun onDestroy() {
        println("SERVICE DESTROYED")
        Log.e("AAA", "service destroy")
        super.onDestroy()
    }
}
*//*


*/
/*
class PinCoolDownService : Service() {
    private val _afterFailDelay: MutableStateFlow<Long> = MutableStateFlow(15)
    private val afterFailDelay = _afterFailDelay.asStateFlow()

    private val pinFailCounter: DataStore<PinFailInfo> by inject()

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        CoroutineScope(SupervisorJob() + Dispatchers.IO).launch {
            pinFailCounter.updateData { it.copy(currentDelay = afterFailDelay.value) }
            repeat(14) {
                delay(1000L)
                _afterFailDelay.value = _afterFailDelay.value - 1L
                pinFailCounter.updateData { it.copy(currentDelay = afterFailDelay.value) }
            }
            pinFailCounter.updateData { it.copy(failCount = 0) }
            delay(1000L)
            pinFailCounter.updateData { it.copy(currentDelay = 15L) }
            Log.e("AAA", "RECEIVER CANCELED")
            stopService(Intent(this@PinCoolDownService.baseContext, PinCoolDownService::class.java))

        }
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }



    override fun onDestroy() {
        Log.e("AAA", "Service destroyed")
        super.onDestroy()
    }

}*//*



//class

*/
/*
private fun isMyServiceRunning(serviceClass: Class<*>): Boolean {
    val manager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager?
    for (service in manager!!.getRunningServices(Int.MAX_VALUE)) {
        if (serviceClass.name == service.service.className) {
            return true
        }
    }
    return false
}
*/