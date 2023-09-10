package ru.maksonic.beresta.feature.folders_chips.ui.folder_item

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalView
import org.koin.compose.koinInject
import ru.maksonic.beresta.core.system.VibrationPerformer
import ru.maksonic.beresta.feature.folders_chips.api.ui.FolderUi
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.outlineVariant
import ru.maksonic.beresta.ui.theme.color.primaryContainer
import ru.maksonic.beresta.ui.theme.color.secondary
import ru.maksonic.beresta.ui.theme.color.tertiary
import ru.maksonic.beresta.ui.theme.component.Shape
import ru.maksonic.beresta.ui.theme.component.dp6
import ru.maksonic.beresta.ui.widget.button.BoxWithScaleInOutOnClick

/**
 * @Author maksonic on 10.09.2023
 */
@Composable
internal fun Container(
    modifier: Modifier = Modifier,
    isSelected: Boolean,
    isCurrent: Boolean,
    folder: FolderUi,
    isTrashPlacement: Boolean = false,
    onFolderClicked: (id: Long) -> Unit,
    onFolderLongPressed: (id: Long) -> Unit,
    vibrationPerformer: VibrationPerformer = koinInject()
) {
    val isFocusedItem = rememberSaveable { mutableStateOf(false) }
    val isSelectedColors = if (isFocusedItem.value) tertiary else secondary
    val colors = if (isFocusedItem.value) outlineVariant else primaryContainer
    val backgroundColor = animateColorAsState(
        if (isSelected) isSelectedColors else colors,
        label = "",
        animationSpec = tween(Theme.animVelocity.common)
    )
    val view = LocalView.current

    BoxWithScaleInOutOnClick(
        onClick = { onFolderClicked(folder.id) },
        onLongClick = {
            onFolderLongPressed(folder.id).run {
                vibrationPerformer.keyboardTapVibration(view)
            }
        },
        backgroundColor = backgroundColor,
        shape = Shape.cornerNormal,
        modifier = modifier
            .padding(top = dp6, bottom = dp6)
            .onFocusChanged { isFocusedItem.value = it.isFocused }
    ) {
        Content(folder, isSelected, isCurrent, isTrashPlacement = isTrashPlacement)
    }
}