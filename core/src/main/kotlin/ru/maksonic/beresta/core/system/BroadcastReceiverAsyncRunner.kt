package ru.maksonic.beresta.core.system

import android.content.BroadcastReceiver
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

/**
 * @Author maksonic on 06.08.2023
 */
fun BroadcastReceiver.runAsync(
    coroutineScope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.IO),
    block: suspend () -> Unit
) {
    val pendingResult = goAsync()
    coroutineScope.launch(Dispatchers.IO) {
        block()
        pendingResult.finish()
    }
}