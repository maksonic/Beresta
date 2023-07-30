package ru.maksonic.beresta.feature.search_bar.ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import ru.maksonic.beresta.feature.search_bar.api.SearchBarApi
import ru.maksonic.beresta.feature.search_bar.api.SearchBarState
import ru.maksonic.beresta.feature.search_bar.api.SearchBarUiState
import ru.maksonic.beresta.feature.search_bar.api.isCollapsed
import ru.maksonic.beresta.feature.search_bar.api.isExpanded
import ru.maksonic.beresta.feature.search_bar.api.isSelection
import ru.maksonic.beresta.feature.search_bar.ui.widget.state.CollapsedContent
import ru.maksonic.beresta.feature.search_bar.ui.widget.state.ExpandedContent
import ru.maksonic.beresta.feature.search_bar.ui.widget.state.SelectedContent
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.onSurfaceVariant
import ru.maksonic.beresta.ui.theme.color.surface
import ru.maksonic.beresta.ui.theme.color.surfaceVariant
import ru.maksonic.beresta.ui.theme.component.dp56
import ru.maksonic.beresta.ui.widget.bar.system.SystemStatusBarHeight
import ru.maksonic.beresta.ui.widget.functional.animation.AnimateContent
import ru.maksonic.beresta.ui.widget.functional.animation.animateDp
import ru.maksonic.beresta.ui.widget.surface.SurfacePro

/**
 * @Author maksonic on 25.07.2023
 */
@Composable
internal fun Content(
    uiState: State<SearchBarUiState>,
    isColoredBackplate: State<Boolean>,
    actions: Map<SearchBarApi.ActionKey, () -> Unit>,
    onSearchResultNoteClicked: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    val isCollapsed = rememberUpdatedState(uiState.value.barState.isCollapsed)
    val isExpanded = rememberUpdatedState(uiState.value.barState.isExpanded)
    val animSpeed = Theme.animVelocity.searchBarTransform
    val statusBarHeight = SystemStatusBarHeight.plus(8.dp)
    val cornerRadius = animateIntAsState(if (isCollapsed.value) 50 else 0, tween(animSpeed))
    val tonal = animateDp(
        if (isExpanded.value) {
            Theme.tonal.Level0
        } else {
            if (isColoredBackplate.value) Theme.tonal.Level2 else Theme.tonal.Level0
        },
        Theme.animVelocity.common
    )
    val paddingEdge = animateDp(if (isCollapsed.value) dp56 else 0.dp, animSpeed)
    val isDisabledTopPaddingAnimation = rememberSaveable { mutableStateOf(false) }
    val paddingTop = if (isDisabledTopPaddingAnimation.value) rememberUpdatedState(statusBarHeight)
    else animateDp(if (isExpanded.value) 0.dp else statusBarHeight, animSpeed)

    LaunchedEffect(Unit) {
        updateTopPaddingAnimation(isDisabledTopPaddingAnimation)
    }

    BoxWithConstraints(modifier.fillMaxWidth()) {
        val barHeight = animateDp(
            if (isExpanded.value) maxHeight
            else Theme.widgetSize.searchBarCollapsedHeight, animSpeed
        )

        SurfacePro(
            tonalElevation = tonal.value,
            modifier = modifier
                .fillMaxWidth()
                .padding(
                    top = paddingTop.value,
                    start = paddingEdge.value,
                    end = paddingEdge.value
                )
                .height(barHeight.value)
                .clip(RoundedCornerShape(cornerRadius.value))
        ) { resultColor ->

            val tween = when (uiState.value.barState) {
                SearchBarState.Selected -> Theme.animVelocity.disabled
                else -> Theme.animVelocity.common
            }

            val color = animateColorAsState(
                when (uiState.value.barState) {
                    SearchBarState.Expanded -> surface
                    SearchBarState.Selected -> resultColor
                    SearchBarState.Collapsed -> {
                        if (!isColoredBackplate.value) surfaceVariant else onSurfaceVariant
                    }
                }, tween(tween)
            )
            Box(modifier.drawBehind { drawRect(color.value) }) {
                AnimateContent(uiState.value.barState) { state ->
                    when {
                        state.isSelection -> SelectedContent(actions)
                        state.isExpanded -> {
                            ExpandedContent(
                                uiState = uiState,
                                actions = actions,
                                onSearchResultNoteClicked = onSearchResultNoteClicked
                            )
                        }

                        state.isCollapsed -> {
                            CollapsedContent(
                                isVisibleGridViewClickableIcon = uiState.value.isVisibleGridIcon,
                                onExpandClicked = {
                                    actions[SearchBarApi.ActionKey.OnExpandBar]?.invoke()
                                },
                                onChangeGridClicked = {
                                    actions[SearchBarApi.ActionKey.OnChangeGridClicked]?.invoke()
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

private suspend fun updateTopPaddingAnimation(state: MutableState<Boolean>) {
    state.value = true
    delay(50)
    state.value = false
}
