package ru.maksonic.beresta.feature.ui.theme_picker.core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel
import ru.maksonic.beresta.feature.ui.theme_picker.core.main.Msg
import ru.maksonic.beresta.feature.ui.theme_picker.core.main.ThemePickerSandbox

/**
 * @Author maksonic on 19.06.2023
 */
internal typealias Send = (Msg) -> Unit

@Composable
internal fun Container(sandbox: ThemePickerSandbox = koinViewModel()) {
    val model by sandbox.model.collectAsStateWithLifecycle()

    Content(model, sandbox::send)
}