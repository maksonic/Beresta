package ru.maksonic.beresta.common.ui_kit.sheet

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalDensity
import ru.maksonic.beresta.common.ui_kit.animation.AnimateFadeInOut
import ru.maksonic.beresta.common.ui_theme.colors.onSecondaryContainer
import ru.maksonic.beresta.common.ui_theme.colors.secondaryContainer

/**
 * @Author maksonic on 02.06.2023
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModalBottomSheetContainer(
    sheetState: SheetState,
    isEnableDragHandle: Boolean = true,
    shape: Shape = BottomSheetDefaults.ExpandedShape,
    onDismissRequest: () -> Unit,
    sheetContent: @Composable () -> Unit
) {
    val density = LocalDensity.current
    val windowInsets = WindowInsets(
        top = 0,
        bottom = BottomSheetDefaults.windowInsets.getBottom(density)
    )
    ModalBottomSheet(
        sheetState = sheetState,
        containerColor = secondaryContainer,
        shape = shape,
        dragHandle = {
            AnimateFadeInOut(isEnableDragHandle) {
                BottomSheetDefaults.DragHandle(color = onSecondaryContainer)
            }
        },
        windowInsets = WindowInsets(0, 0, 0, 0),
        onDismissRequest = onDismissRequest,
    ) {
        Column(Modifier.windowInsetsPadding(windowInsets)) {
            sheetContent()
        }
    }
}