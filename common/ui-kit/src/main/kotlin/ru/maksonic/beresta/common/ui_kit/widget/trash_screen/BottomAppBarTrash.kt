package ru.maksonic.beresta.common.ui_kit.widget.trash_screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.maksonic.beresta.common.ui_kit.bar.bottom.BottomAppBar
import ru.maksonic.beresta.common.ui_kit.bar.bottom.BottomBarItem
import ru.maksonic.beresta.common.ui_kit.bar.system.SystemNavigationBarHeight
import ru.maksonic.beresta.common.ui_kit.button.ButtonFloatingIconFab
import ru.maksonic.beresta.common.ui_kit.helpers.PreviewContainer
import ru.maksonic.beresta.common.ui_kit.icons.AppIcon
import ru.maksonic.beresta.common.ui_kit.icons.Delete
import ru.maksonic.beresta.common.ui_kit.icons.Restart
import ru.maksonic.beresta.common.ui_kit.surface.SurfacePro
import ru.maksonic.beresta.common.ui_theme.Theme
import ru.maksonic.beresta.common.ui_theme.provide.dp16
import ru.maksonic.beresta.language_engine.shell.provider.text

/**
 * @Author maksonic on 20.10.2023
 */
@Composable
fun BottomAppBarTrash(
    isEmptyList: Boolean,
    isSelection: Boolean,
    isDisabled: Boolean,
    onRestoreClicked: () -> Unit,
    onDeleteClicked: () -> Unit,
    onDeleteAllClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    val offset = Theme.size.bottomMainBarHeight.plus(SystemNavigationBarHeight)
    val panelOffset = animateDpAsState(
        if (isSelection) 0.dp else offset, tween(Theme.animVelocity.common), label = ""
    )
    val items = arrayOf(
        BottomBarItem(
            label = text.shared.btnTitleRestore,
            icon = AppIcon.Restart,
            onClick = onRestoreClicked
        ),
        BottomBarItem(
            label = text.shared.btnTitleDelete,
            icon = AppIcon.Delete,
            onClick = onDeleteClicked
        )
    )

    Column(modifier = modifier
        .graphicsLayer { translationY = panelOffset.value.toPx() }
        .fillMaxWidth(),
        horizontalAlignment = Alignment.End
    ) {
        AnimatedVisibility(
            visible = !isSelection && !isEmptyList,
            enter = scaleIn(tween(Theme.animVelocity.common)) + fadeIn(tween(Theme.animVelocity.common)),
            exit = scaleOut(tween(Theme.animVelocity.common)) + fadeOut(tween(Theme.animVelocity.common))
        ) {
            ButtonFloatingIconFab(
                icon = AppIcon.Delete,
                onClick = onDeleteAllClicked,
                shape = Theme.shape.cornerRound,
                modifier = modifier.navigationBarsPadding().padding(end = dp16, bottom = dp16)
            )
        }

        SurfacePro(
            tonalElevation = Theme.tonal.level4,
        ) {
            BottomAppBar(items, isDisabled = isDisabled)
        }
    }
}

@Preview
@Composable
private fun BottomAppBarTrashPreview() {
    PreviewContainer {
        BottomAppBarTrash(
            isEmptyList = false,
            isSelection = true,
            isDisabled = false,
            onRestoreClicked = {},
            onDeleteClicked = {},
            onDeleteAllClicked = {}
        )
    }
}