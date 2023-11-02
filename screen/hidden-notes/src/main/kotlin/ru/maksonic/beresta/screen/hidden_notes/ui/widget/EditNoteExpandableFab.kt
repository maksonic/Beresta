package ru.maksonic.beresta.screen.hidden_notes.ui.widget

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import org.koin.compose.koinInject
import ru.maksonic.beresta.common.ui_theme.Theme
import ru.maksonic.beresta.feature.ui.edit_note.api.EditNoteUiApi
import ru.maksonic.beresta.screen.hidden_notes.core.Model
import ru.maksonic.beresta.screen.hidden_notes.core.Msg
import ru.maksonic.beresta.screen.hidden_notes.ui.Send

/**
 * @Author maksonic on 21.07.2023
 */
@Composable
internal fun EditNoteExpandableFab(
    model: Model,
    send: Send,
    modifier: Modifier,
    api: EditNoteUiApi = koinInject()
) {
    val fabTransition = animateFloatAsState(
        if (model.editNoteFabState.isVisible) 1f else 0f,
        tween(Theme.animVelocity.common), label = ""
    )

    api.ExpandableScreen(
        router = null,
        state = model.editNoteFabState.state,
        updateFabState = {
            if (model.editNoteFabState.isVisible) send(Msg.Inner.UpdatedEditNoteFabState(it))
        },
        isEntryPoint = false,
        isHiddenNote = true,
        modifier = modifier.graphicsLayer {
            scaleX = fabTransition.value
            scaleY = fabTransition.value
            alpha = fabTransition.value
        })
}