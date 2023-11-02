package ru.maksonic.beresta.feature.folders_list.ui.core.list

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import org.koin.compose.koinInject
import ru.maksonic.beresta.common.core.VibrationPerformer
import ru.maksonic.beresta.common.ui_kit.animation.AnimateFadeInOut
import ru.maksonic.beresta.common.ui_kit.button.base.ScalableClickBox
import ru.maksonic.beresta.common.ui_kit.icons.AppIcon
import ru.maksonic.beresta.common.ui_kit.icons.Done
import ru.maksonic.beresta.common.ui_kit.icons.Folder
import ru.maksonic.beresta.common.ui_kit.icons.Pin
import ru.maksonic.beresta.common.ui_theme.Theme
import ru.maksonic.beresta.common.ui_theme.colors.inversePrimary
import ru.maksonic.beresta.common.ui_theme.colors.inverseSurface
import ru.maksonic.beresta.common.ui_theme.colors.onPrimaryContainer
import ru.maksonic.beresta.common.ui_theme.colors.outline
import ru.maksonic.beresta.common.ui_theme.colors.outlineVariant
import ru.maksonic.beresta.common.ui_theme.colors.primary
import ru.maksonic.beresta.common.ui_theme.colors.primaryContainer
import ru.maksonic.beresta.common.ui_theme.colors.secondary
import ru.maksonic.beresta.common.ui_theme.colors.secondaryContainer
import ru.maksonic.beresta.common.ui_theme.colors.tertiary
import ru.maksonic.beresta.common.ui_theme.colors.tertiaryContainer
import ru.maksonic.beresta.common.ui_theme.provide.dp16
import ru.maksonic.beresta.common.ui_theme.provide.dp32
import ru.maksonic.beresta.common.ui_theme.provide.dp6
import ru.maksonic.beresta.common.ui_theme.provide.dp8
import ru.maksonic.beresta.common.ui_theme.typography.TextDesign
import ru.maksonic.beresta.feature.folders_list.ui.api.FolderUi
import ru.maksonic.beresta.feature.folders_list.ui.api.isDefaultId
import ru.maksonic.beresta.language_engine.shell.provider.text

/**
 * @Author maksonic on 20.10.2023
 */
@Composable
internal fun FolderItem(
    modifier: Modifier = Modifier,
    folder: FolderUi,
    isSelected: Boolean,
    isCurrent: Boolean,
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

    ScalableClickBox(
        onClick = { onFolderClicked(folder.id) },
        onLongClick = {
            onFolderLongPressed(folder.id).run {
                vibrationPerformer.keyboardTapVibration(view)
            }
        },
        backgroundColor = backgroundColor,
        shape = Theme.shape.cornerNormal,
        modifier = modifier
            .padding(top = dp6, bottom = dp6)
            .onFocusChanged { isFocusedItem.value = it.isFocused }
    ) {
        Content(folder, isSelected, isCurrent, isTrashPlacement = isTrashPlacement)
    }
}

@Composable
private fun Content(
    folder: FolderUi,
    isSelected: Boolean,
    isCurrent: Boolean,
    modifier: Modifier = Modifier,
    isTrashPlacement: Boolean = false,
) {
    Column {
        Row(
            Modifier
                .fillMaxWidth()
                .height(Theme.size.minimumTouchTargetSize),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (isCurrent) {
                Icon(
                    imageVector = AppIcon.Done,
                    tint = tertiaryContainer,
                    contentDescription = "",
                    modifier = Modifier.padding(start = dp16)
                )
            }

            if (isTrashPlacement) {
                val iconBackgroundColor = animateColorAsState(
                    if (isSelected) inversePrimary else secondaryContainer,
                    label = ""
                )

                Box(
                    modifier
                        .padding(start = dp16)
                        .size(dp32)
                        .clip(CircleShape)
                        .drawBehind { drawRect(iconBackgroundColor.value) },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = AppIcon.Folder,
                        tint = tertiaryContainer,
                        contentDescription = "",
                        modifier = modifier.size(22.dp)
                    )
                }
            }

            Text(
                text = folder.title,
                style = TextDesign.bodyLarge.copy(color = onPrimaryContainer),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = dp16, end = dp8)
            )

            Text(
                text = folder.notesCount.toString(),
                style = TextDesign.bodyMedium.copy(color = outline),
                maxLines = 1,
                modifier = Modifier.padding(end = dp16)
            )

            if (!isTrashPlacement) {
                Box(
                    Modifier
                        .padding(end = dp8)
                        .size(dp16)
                ) {
                    AnimateFadeInOut(
                        visible = folder.isPinned && !folder.isDefaultId()
                    ) {
                        Icon(
                            imageVector = AppIcon.Pin,
                            modifier = modifier.size(dp16),
                            tint = primary,
                            contentDescription = null
                        )
                    }
                }
            }
        }

        if (isTrashPlacement) {
            Text(
                text = "${text.trash.hintRemovedDatePrefix} ${folder.dateMovedToTrash}",
                style = TextDesign.labelSmall.copy(color = inverseSurface),
                modifier = modifier.padding(start = dp16, top = dp8, bottom = dp16)
            )
        }
    }

}