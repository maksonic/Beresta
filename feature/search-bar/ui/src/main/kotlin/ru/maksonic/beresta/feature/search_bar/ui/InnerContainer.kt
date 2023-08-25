package ru.maksonic.beresta.feature.search_bar.ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import ru.maksonic.beresta.feature.search_bar.api.SearchBarApi
import ru.maksonic.beresta.feature.search_bar.api.SearchBarState
import ru.maksonic.beresta.feature.search_bar.api.SearchBarUiState
import ru.maksonic.beresta.feature.search_bar.api.isCollapsed
import ru.maksonic.beresta.feature.search_bar.api.isExpanded
import ru.maksonic.beresta.feature.search_bar.api.isSelection
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.onSurfaceVariant
import ru.maksonic.beresta.ui.theme.color.primary
import ru.maksonic.beresta.ui.theme.color.surface
import ru.maksonic.beresta.ui.theme.color.surfaceVariant
import ru.maksonic.beresta.ui.theme.component.dp56
import ru.maksonic.beresta.ui.widget.bar.system.SystemStatusBarHeight
import ru.maksonic.beresta.ui.widget.functional.animation.animateDp
import ru.maksonic.beresta.ui.widget.functional.noRippleClick
import ru.maksonic.beresta.ui.widget.functional.rippledClick
import ru.maksonic.beresta.ui.widget.surface.SurfacePro

/**
 * @Author maksonic on 25.08.2023
 */
@Composable
internal fun InnerContainer(
    uiState: State<SearchBarUiState>,
    isColoredBackplate: State<Boolean>,
    actions: Map<SearchBarApi.ActionKey, () -> Unit>,
    maxHeight: Dp,
    modifier: Modifier = Modifier
) {
    val isCollapsed = rememberUpdatedState(uiState.value.barState.isCollapsed)
    val isExpanded = rememberUpdatedState(uiState.value.barState.isExpanded)
    val animSpeed = Theme.animVelocity.searchBarTransform
    val statusBarHeight = SystemStatusBarHeight.plus(8.dp)
    val barHeight = animateDp(
        if (isExpanded.value) maxHeight
        else Theme.widgetSize.searchBarCollapsedHeight, animSpeed
    )
    val cornerRadius =
        animateIntAsState(if (isCollapsed.value) 50 else 0, tween(animSpeed), label = "")
    val paddingEdge = animateDp(if (isCollapsed.value) dp56 else 0.dp, animSpeed)
    val isDisabledTopPaddingAnimation = rememberSaveable { mutableStateOf(false) }

    val paddingTop = if (isDisabledTopPaddingAnimation.value) rememberUpdatedState(statusBarHeight)
    else animateDp(if (isExpanded.value) 0.dp else statusBarHeight, animSpeed)

    val tonal = animateDp(
        if (isExpanded.value) {
            Theme.tonal.Level0
        } else {
            if (isColoredBackplate.value) Theme.tonal.Level2 else Theme.tonal.Level0
        },
        Theme.animVelocity.common
    )


    LaunchedEffect(Unit) {
        updateTopPaddingAnimation(isDisabledTopPaddingAnimation)
    }

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
        val colorTween = when (uiState.value.barState) {
            SearchBarState.Selected -> Theme.animVelocity.disabled
            else -> Theme.animVelocity.common
        }

        val color = animateColorAsState(
            when {
                uiState.value.barState.isExpanded -> surface
                uiState.value.barState.isSelection -> resultColor
                else -> if (!isColoredBackplate.value) surfaceVariant else onSurfaceVariant
            }, tween(colorTween), label = ""
        )

        val actionModifier = if (uiState.value.barState.isCollapsed)
            Modifier.rippledClick(rippleColor = primary) {
                actions[SearchBarApi.ActionKey.OnExpandBar]?.invoke()
            }
        else Modifier.noRippleClick { }

        Box(
            modifier
                .drawBehind { drawRect(color.value) }
                .then(actionModifier))

    }
}

private suspend fun updateTopPaddingAnimation(state: MutableState<Boolean>) {
    state.value = true
    delay(50)
    state.value = false
}