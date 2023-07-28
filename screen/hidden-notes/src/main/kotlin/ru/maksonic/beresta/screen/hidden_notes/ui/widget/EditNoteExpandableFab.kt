package ru.maksonic.beresta.screen.hidden_notes.ui.widget

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import org.koin.compose.koinInject
import ru.maksonic.beresta.feature.edit_note.api.EditNoteApi
import ru.maksonic.beresta.ui.theme.Theme

/**
 * @Author maksonic on 21.07.2023
 */
@Composable
internal fun EditNoteExpandableFab(
    isVisible: State<Boolean>,
    modifier: Modifier,
    api: EditNoteApi.Ui = koinInject()
) {
    val fabTransition = animateFloatAsState(
        if (isVisible.value) 1f else 0f, tween(Theme.animVelocity.common), label = ""
    )

    api.ExpandableScreen(
        router = null,
        isEntryPoint = false,
        isHiddenNotes = true,
        modifier = modifier.graphicsLayer {
            scaleX = fabTransition.value
            scaleY = fabTransition.value
            alpha = fabTransition.value
        })
}