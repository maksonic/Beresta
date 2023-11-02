package ru.maksonic.beresta.feature.ui.search_bar.core

import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import ru.maksonic.beresta.common.ui_kit.animation.AnimateFadeInOut
import ru.maksonic.beresta.common.ui_theme.Theme
import ru.maksonic.beresta.feature.ui.search_bar.api.SearchBarUiApi
import ru.maksonic.beresta.feature.ui.search_bar.api.SearchBarUiState
import ru.maksonic.beresta.feature.ui.search_bar.api.isExpanded

/**
 * @Author maksonic on 25.07.2023
 */
@Composable
internal fun Content(
    state: SearchBarUiState,
    isColoredBackplate: State<Boolean>,
    actions: Map<SearchBarUiApi.ActionKey, () -> Unit>,
    onNoteClicked: (Long) -> Unit,
    modifier: Modifier = Modifier
) {

    BackHandler(state.barState.isExpanded) {
        actions[SearchBarUiApi.ActionKey.OnCollapseBar]?.invoke()
    }

    BoxWithConstraints(modifier.fillMaxWidth()) {
        val animSpeed = Theme.animVelocity.searchBarTransform
        val initialPadding = 112.dp.value
        val targetStartPadding = 56.dp.value
        val targetEndPadding = 16.dp.value
        val paddingStart = remember { Animatable(initialPadding) }
        val paddingEnd = remember { Animatable(initialPadding) }

        LaunchedEffect(state.barState.isExpanded) {
            awaitAll(
                async {
                    paddingStart.animateTo(
                        if (state.barState.isExpanded) targetStartPadding else initialPadding,
                        animationSpec = tween(durationMillis = animSpeed, easing = LinearEasing)
                    )
                },
                async {
                    paddingEnd.animateTo(
                        if (state.barState.isExpanded) targetEndPadding else initialPadding,
                        animationSpec = tween(durationMillis = animSpeed, easing = LinearEasing)
                    )
                }
            )
        }

        InnerContainer(
            state = state,
            isColoredBackplate = isColoredBackplate,
            actions = actions,
            maxHeight = this.maxHeight
        )

        AnimateFadeInOut(state.barState.isExpanded) {
            ExpandedContent(
                state = state,
                actions = actions,
                onNoteClicked = onNoteClicked,
                queryModifier = modifier.padding(
                    start = paddingStart.value.dp,
                    end = paddingEnd.value.dp
                )
            )
        }

        InnerContent(state, actions)
    }
}