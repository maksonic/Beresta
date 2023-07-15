package ru.maksonic.beresta.ui.widget.bar.bottom

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.icons.AppIcon
import ru.maksonic.beresta.ui.theme.icons.Delete
import ru.maksonic.beresta.ui.theme.icons.Restart
import ru.maksonic.beresta.ui.widget.bar.system.SystemNavigationBarHeight
import ru.maksonic.beresta.ui.widget.functional.animation.AnimateFadeInOut
import ru.maksonic.beresta.ui.widget.functional.animation.animateDp
import ru.maksonic.beresta.ui.widget.surface.SurfacePro

/**
 * @Author maksonic on 30.05.2023
 */
@Composable
fun TrashBottomBar(
    onRestoreClicked: () -> Unit,
    onDeleteClicked: () -> Unit,
    isSelectionState: Boolean,
    isVisibleFirstItemOffset: State<Boolean>,
    isDisabledBottomBar: Boolean,
    modifier: Modifier = Modifier
) {

    val offset = Theme.widgetSize.bottomBarNormalHeight.plus(SystemNavigationBarHeight)
    val panelOffset = animateDp(if (isSelectionState) 0.dp else offset)
    val items = arrayOf(
        BaseBottomBarItem(
            label = text.shared.btnTitleRestore,
            icon = AppIcon.Restart,
            action = onRestoreClicked
        ),
        BaseBottomBarItem(
            label = text.shared.btnTitleDelete,
            icon = AppIcon.Delete,
            action = onDeleteClicked
        )
    )
    val tonal = animateDp(
        if (isVisibleFirstItemOffset.value) Theme.tonal.Level0 else Theme.tonal.Level4
    )

    SurfacePro(
        tonalElevation = tonal.value,
        modifier = modifier.graphicsLayer { translationY = panelOffset.value.toPx() }
    ) {
        BottomBarOld(items)

        AnimateFadeInOut(isDisabledBottomBar) {
            DisabledBottomBarPlaceholder()
        }
    }
}