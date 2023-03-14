package ru.maksonic.beresta.feature.edit_note.core.screen.ui.widget.sheet

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp

/**
 * @Author maksonic on 12.03.2023
 */
enum class BottomSheetEditorState {
    EXPANDED, COLLAPSED
}

@Composable
internal fun BottomSheetContainer(
    modifier: Modifier = Modifier,
    sheetState: BottomSheetEditorState,
    sheetContent: @Composable () -> Unit
) {
    val statusBarHeight = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()
    val navigationBarHeight = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()
    val screenHeight =
        LocalConfiguration.current.screenHeightDp.dp.plus(statusBarHeight).plus(navigationBarHeight)
    val offset = animateDpAsState(
        if (sheetState == BottomSheetEditorState.EXPANDED) 0.dp else screenHeight,
        animationSpec = tween(450)
    )

    Box(
        modifier
            .fillMaxSize()
            .graphicsLayer {
                translationY = offset.value.toPx()
            }) {
        sheetContent()
    }
}
