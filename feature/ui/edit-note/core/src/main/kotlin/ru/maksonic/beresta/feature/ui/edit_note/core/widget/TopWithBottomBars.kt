package ru.maksonic.beresta.feature.ui.edit_note.core.widget

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import ru.maksonic.beresta.common.ui_kit.animation.AnimateFadeInOut
import ru.maksonic.beresta.common.ui_kit.bar.system.SystemNavigationBarHeight
import ru.maksonic.beresta.common.ui_theme.Theme
import ru.maksonic.beresta.common.ui_theme.colors.surface
import ru.maksonic.beresta.common.ui_theme.colors.surfaceVariant
import ru.maksonic.beresta.feature.notes_list.ui.api.isBlank
import ru.maksonic.beresta.feature.ui.edit_note.core.Model
import ru.maksonic.beresta.feature.ui.edit_note.core.screen.Send

/**
 * @Author maksonic on 08.09.2023
 */
@Composable
internal fun TopWithBottomBars(
    model: Model,
    send: Send,
    isVisibleBottomBar: State<Boolean>,
    canScrollBackward: State<Boolean>,
    isHiddenNote: Boolean,
    topBarColor: State<Color>,
    // topBarOffset: State<Float>,
    // bottomBarOffset: State<Float>,
    modifier: Modifier = Modifier
) {

    val isInitialColor = topBarColor.value == surface
    val animDelay = Theme.animVelocity.common
    var isAnimated by remember { mutableStateOf(false) }
    val animVelocity = if (isAnimated) animDelay else 0

    LaunchedEffect(Unit) {
        if (!isAnimated) {
            delay(animDelay.toLong())
            isAnimated = true
        }
    }

    val topBarBackgroundColor = animateColorAsState(
        if (canScrollBackward.value)
            if (isInitialColor) {
                surfaceVariant.copy(0.5f).compositeOver(topBarColor.value)
            } else {
                Color.Black.copy(0.15f).compositeOver(topBarColor.value)
            }
        else topBarColor.value, tween(animVelocity), label = ""
    )

    val bottomBarBackgroundColor = rememberUpdatedState(
        if (isInitialColor) {
            surfaceVariant.copy(0.5f).compositeOver(topBarColor.value)
        } else {
            Color.Black.copy(0.15f).compositeOver(topBarColor.value)
        }
    )

    Column(
        modifier = modifier.fillMaxHeight(),
        horizontalAlignment = Alignment.End,
    ) {
        EditNoteTopBar(
            model = model,
            send = send,
            backgroundColor = topBarBackgroundColor,
            /* modifier = modifier.graphicsLayer {
                 translationY = topBarOffset.value
             }*/
        )

        Spacer(modifier.weight(1f))

        AnimateFadeInOut(!model.isVisibleAddFolderDialog) {
            val offset = Theme.size.bottomMainBarHeight.plus(SystemNavigationBarHeight)
            val transition = animateDpAsState(
                if (isVisibleBottomBar.value) 0.dp else offset,
                tween(Theme.animVelocity.common),
                label = ""
            )

            EditorBottomBar(
                send = send,
                isScrollUp = isVisibleBottomBar,
                isBlankNote = model.editableNote.isBlank(),
                isHiddenNote = isHiddenNote,
                backgroundColor = bottomBarBackgroundColor,
                modifier = modifier.graphicsLayer {
                    translationY = transition.value.toPx()
                }
            )
        }
    }
}