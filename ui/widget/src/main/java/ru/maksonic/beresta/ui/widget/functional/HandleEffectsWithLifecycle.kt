package ru.maksonic.beresta.ui.widget.functional

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

/**
 * @Author maksonic on 15.12.2022
 */
@Composable
fun <T> HandleEffectsWithLifecycle(effects: Flow<T>, handle: (T) -> Unit) {
    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(Unit) {
        launch {
            effects.flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED)
                .collect { effect ->
                    handle.invoke(effect)
                }
        }
    }
}