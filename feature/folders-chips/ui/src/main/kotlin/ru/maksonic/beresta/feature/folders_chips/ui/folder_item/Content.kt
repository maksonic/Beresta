package ru.maksonic.beresta.feature.folders_chips.ui.folder_item

import androidx.compose.animation.animateColorAsState
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import ru.maksonic.beresta.feature.folders_chips.api.ui.FolderUi
import ru.maksonic.beresta.feature.folders_chips.api.ui.isDefaultId
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.inversePrimary
import ru.maksonic.beresta.ui.theme.color.inverseSurface
import ru.maksonic.beresta.ui.theme.color.onPrimaryContainer
import ru.maksonic.beresta.ui.theme.color.outline
import ru.maksonic.beresta.ui.theme.color.primary
import ru.maksonic.beresta.ui.theme.color.secondaryContainer
import ru.maksonic.beresta.ui.theme.color.tertiaryContainer
import ru.maksonic.beresta.ui.theme.component.TextDesign
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.theme.component.dp32
import ru.maksonic.beresta.ui.theme.component.dp8
import ru.maksonic.beresta.ui.theme.icons.AppIcon
import ru.maksonic.beresta.ui.theme.icons.Done
import ru.maksonic.beresta.ui.theme.icons.Folder
import ru.maksonic.beresta.ui.theme.icons.Pin
import ru.maksonic.beresta.ui.widget.functional.animation.AnimateFadeInOut

/**
 * @Author maksonic on 10.09.2023
 */
@Composable
internal fun Content(
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
                .height(Theme.widgetSize.minimumTouchTargetSize),
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
                style = TextDesign.title.copy(color = onPrimaryContainer),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = dp16, end = dp8)
            )

            Text(
                text = folder.notesCount.toString(),
                style = TextDesign.bodyPrimaryMedium.copy(color = outline),
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
                style = TextDesign.captionSmall.copy(color = inverseSurface),
                modifier = modifier.padding(start = dp16, top = dp8, bottom = dp16)
            )
        }
    }

}