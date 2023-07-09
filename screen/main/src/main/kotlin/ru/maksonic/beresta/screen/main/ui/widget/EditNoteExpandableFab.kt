package ru.maksonic.beresta.screen.main.ui.widget

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
 * @Author maksonic on 30.06.2023
 */
@Composable
internal fun EditNoteExpandableFab(
    isVisible: State<Boolean>,
    modifier: Modifier,
    api: EditNoteApi.Ui = koinInject()
) {
    val fabTransition = animateFloatAsState(
        if (isVisible.value) 1f else 0f, tween(Theme.animSpeed.common), label = ""
    )

    api.ExpandableScreen(router = null, isEntryPoint = false, modifier.graphicsLayer {
        scaleX = fabTransition.value
        scaleY = fabTransition.value
        alpha = fabTransition.value
    })
}