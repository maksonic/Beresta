package ru.maksonic.beresta.screen.hidden_notes.ui.widget

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import org.koin.compose.koinInject
import ru.maksonic.beresta.feature.edit_note.api.EditNoteApi
import ru.maksonic.beresta.screen.hidden_notes.core.Model
import ru.maksonic.beresta.screen.hidden_notes.core.Msg
import ru.maksonic.beresta.screen.hidden_notes.ui.SendMessage
import ru.maksonic.beresta.ui.theme.Theme

/**
 * @Author maksonic on 21.07.2023
 */
@Composable
internal fun EditNoteExpandableFab(
    model: State<Model>,
    send: SendMessage,
    modifier: Modifier,
    api: EditNoteApi.Ui = koinInject()
) {
    val fabTransition = animateFloatAsState(
        if (model.value.editNoteFabState.isVisible) 1f else 0f, tween(Theme.animVelocity.common)
    )

    api.ExpandableScreen(
        router = null,
        state = rememberUpdatedState(model.value.editNoteFabState.state),
        updateFabState = {
            if (model.value.editNoteFabState.isVisible) send(Msg.Inner.UpdatedEditNoteFabState(it))
        },
        isEntryPoint = false,
        isHiddenNotes = true,
        modifier = modifier.graphicsLayer {
            scaleX = fabTransition.value
            scaleY = fabTransition.value
            alpha = fabTransition.value
        })
}