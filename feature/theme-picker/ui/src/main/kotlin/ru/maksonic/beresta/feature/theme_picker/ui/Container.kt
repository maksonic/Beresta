package ru.maksonic.beresta.feature.theme_picker.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel
import ru.maksonic.beresta.feature.theme_picker.ui.core.Msg
import ru.maksonic.beresta.feature.theme_picker.ui.core.ThemePickerSandbox

/**
 * @Author maksonic on 19.06.2023
 */
internal typealias SendMessage = (Msg) -> Unit

@Composable
internal fun Container(
    hideSheet: () -> Unit,
    sandbox: ThemePickerSandbox = koinViewModel()
) {
    val model = sandbox.model.collectAsStateWithLifecycle()

    Content(
        model = model,
        send = sandbox::send,
        hideSheet = hideSheet,
    )
}