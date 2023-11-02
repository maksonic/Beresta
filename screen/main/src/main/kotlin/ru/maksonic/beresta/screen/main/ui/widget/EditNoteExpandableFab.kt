package ru.maksonic.beresta.screen.main.ui.widget

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import org.koin.compose.koinInject
import ru.maksonic.beresta.common.ui_theme.Theme
import ru.maksonic.beresta.feature.ui.edit_note.api.EditNoteUiApi
import ru.maksonic.beresta.screen.main.core.Model
import ru.maksonic.beresta.screen.main.core.Msg
import ru.maksonic.beresta.screen.main.ui.screen.Send

/**
 * @Author maksonic on 16.10.2023
 */
@Composable
internal fun EditNoteExpandableFab(
    model: Model,
    send: Send,
    modifier: Modifier,
    api: EditNoteUiApi = koinInject()
) {
    val fabTransition = animateFloatAsState(
        if (model.editNoteFabState.isVisible) 1f else 0f, tween(Theme.animVelocity.common),
        label = ""
    )

    api.ExpandableScreen(
        router = null,
        state = model.editNoteFabState.state,
        updateFabState = { send(Msg.Inner.UpdatedEditNoteFabState(it)) },
        isEntryPoint = false,
        isHiddenNote = false,
        modifier = modifier.graphicsLayer {
            scaleX = fabTransition.value
            scaleY = fabTransition.value
            alpha = fabTransition.value
        })
}