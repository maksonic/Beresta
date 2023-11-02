package ru.maksonic.beresta.feature.ui.search_bar.core

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
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
import ru.maksonic.beresta.common.ui_kit.bar.system.SystemStatusBarHeight
import ru.maksonic.beresta.common.ui_kit.helpers.modifier.noRippleClick
import ru.maksonic.beresta.common.ui_kit.helpers.modifier.rippledClick
import ru.maksonic.beresta.common.ui_kit.surface.SurfacePro
import ru.maksonic.beresta.feature.ui.search_bar.api.SearchBarUiApi
import ru.maksonic.beresta.feature.ui.search_bar.api.SearchBarState
import ru.maksonic.beresta.feature.ui.search_bar.api.SearchBarUiState
import ru.maksonic.beresta.feature.ui.search_bar.api.isCollapsed
import ru.maksonic.beresta.feature.ui.search_bar.api.isExpanded
import ru.maksonic.beresta.feature.ui.search_bar.api.isSelection
import ru.maksonic.beresta.common.ui_theme.Theme
import ru.maksonic.beresta.common.ui_theme.colors.onSurfaceVariant
import ru.maksonic.beresta.common.ui_theme.colors.primary
import ru.maksonic.beresta.common.ui_theme.colors.surface
import ru.maksonic.beresta.common.ui_theme.colors.surfaceVariant
import ru.maksonic.beresta.common.ui_theme.provide.dp56

/**
 * @Author maksonic on 25.08.2023
 */
@Composable
internal fun InnerContainer(
    state: SearchBarUiState,
    isColoredBackplate: State<Boolean>,
    actions: Map<SearchBarUiApi.ActionKey, () -> Unit>,
    maxHeight: Dp,
    modifier: Modifier = Modifier
) {
    val isCollapsed = rememberUpdatedState(state.barState.isCollapsed)
    val isExpanded = rememberUpdatedState(state.barState.isExpanded)
    val animSpeed = Theme.animVelocity.searchBarTransform
    val statusBarHeight = SystemStatusBarHeight.plus(8.dp)
    val barHeight = animateDpAsState(
        if (isExpanded.value) maxHeight
        else Theme.size.searchBarCollapsedHeight, tween(animSpeed), label = ""
    )
    val cornerRadius =
        animateIntAsState(if (isCollapsed.value) 50 else 0, tween(animSpeed), label = "")
    val paddingEdge =
        animateDpAsState(if (isCollapsed.value) dp56 else 0.dp, tween(animSpeed), label = "")
    val isDisabledTopPaddingAnimation = rememberSaveable { mutableStateOf(false) }

    val paddingTop = if (isDisabledTopPaddingAnimation.value) rememberUpdatedState(statusBarHeight)
    else animateDpAsState(
        if (isExpanded.value) 0.dp else statusBarHeight, tween(animSpeed), label = ""
    )

    val tonal = animateDpAsState(
        if (isExpanded.value) {
            Theme.tonal.level0
        } else {
            if (isColoredBackplate.value) Theme.tonal.level2 else Theme.tonal.level0
        },
        tween(Theme.animVelocity.common), label = ""
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
        val colorTween = when (state.barState) {
            SearchBarState.Selected -> Theme.animVelocity.disabled
            else -> Theme.animVelocity.common
        }

        val color = animateColorAsState(
            when {
                state.barState.isExpanded -> surface
                state.barState.isSelection -> resultColor
                else -> if (!isColoredBackplate.value) surfaceVariant else onSurfaceVariant
            }, tween(colorTween), label = ""
        )

        val actionModifier = if (state.barState.isCollapsed)
            Modifier.rippledClick(rippleColor = primary) {
                actions[SearchBarUiApi.ActionKey.OnExpandBar]?.invoke()
            }
        else Modifier.noRippleClick()

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