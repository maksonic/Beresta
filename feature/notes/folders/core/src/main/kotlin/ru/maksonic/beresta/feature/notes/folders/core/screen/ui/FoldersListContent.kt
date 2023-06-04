package ru.maksonic.beresta.feature.notes.folders.core.screen.ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import ru.maksonic.beresta.feature.notes.folders.api.ui.NoteFolderUi
import ru.maksonic.beresta.feature.notes.folders.api.ui.isDefaultId
import ru.maksonic.beresta.feature.notes.folders.core.screen.core.Model
import ru.maksonic.beresta.feature.notes.folders.core.screen.core.Msg
import ru.maksonic.beresta.feature.notes.folders.core.screen.ui.widget.FoldersLoaderWidgetContent
import ru.maksonic.beresta.feature.notes.list.api.domain.DateFormatter
import ru.maksonic.beresta.language_engine.shell.provider.AppLanguage
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.inversePrimary
import ru.maksonic.beresta.ui.theme.color.inverseSurface
import ru.maksonic.beresta.ui.theme.color.onPrimaryContainer
import ru.maksonic.beresta.ui.theme.color.outline
import ru.maksonic.beresta.ui.theme.color.outlineVariant
import ru.maksonic.beresta.ui.theme.color.primary
import ru.maksonic.beresta.ui.theme.color.primaryContainer
import ru.maksonic.beresta.ui.theme.color.secondary
import ru.maksonic.beresta.ui.theme.color.secondaryContainer
import ru.maksonic.beresta.ui.theme.color.tertiary
import ru.maksonic.beresta.ui.theme.color.tertiaryContainer
import ru.maksonic.beresta.ui.theme.component.Shape
import ru.maksonic.beresta.ui.theme.component.TextDesign
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.theme.component.dp32
import ru.maksonic.beresta.ui.theme.component.dp6
import ru.maksonic.beresta.ui.theme.component.dp8
import ru.maksonic.beresta.ui.theme.icons.AppIcon
import ru.maksonic.beresta.ui.theme.icons.Done
import ru.maksonic.beresta.ui.theme.icons.Folder
import ru.maksonic.beresta.ui.theme.icons.Pin
import ru.maksonic.beresta.ui.widget.button.BoxWithScaleInOutOnClick
import ru.maksonic.beresta.ui.widget.functional.animation.AnimateContent
import ru.maksonic.beresta.ui.widget.functional.animation.AnimateFadeInOut

/**
 * @Author maksonic on 15.05.2023
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun FoldersListContent(
    folders: NoteFolderUi.Collection,
    model: Model,
    send: SendMessage,
    scrollState: LazyListState,
    modifier: Modifier = Modifier
) {
    val defaultPadding = Theme.widgetSize.bottomBarNormalHeight.plus(dp6)
    val bottomContentPadding = animateDpAsState(
        if (model.isSelectionState) defaultPadding else defaultPadding.plus(dp16), label = "",
        animationSpec = tween(Theme.animSpeed.common)
    )
    AnimateContent(model.base.isLoading) { isPlaceholder ->
        if (isPlaceholder) {
            FoldersLoaderWidgetContent(modifier)
        } else {
            LazyColumn(
                state = scrollState,
                contentPadding = PaddingValues(
                    top = dp6,
                    start = dp16,
                    end = dp16,
                    bottom = bottomContentPadding.value
                ),
                modifier = modifier.fillMaxSize()
            ) {
                items(folders.data, key = { it.id }) { folder ->
                    FolderItemContent(
                        modifier = Modifier.animateItemPlacement(),
                        folder = folder,
                        isSelected = model.selectedFolders.contains(folder) && folder.isSelectable,
                        isCurrent = folder.id == model.currentSelectedFolderId,
                        onFolderClicked = { send(Msg.Ui.OnFolderClicked(folder.id)) },
                        onFolderLongPressed = { send(Msg.Ui.OnFolderLongPressed(folder.id)) }
                    )
                }
            }
        }
    }
}

@Composable
internal fun FolderItemContent(
    modifier: Modifier = Modifier,
    isSelected: Boolean,
    isCurrent: Boolean,
    folder: NoteFolderUi,
    onFolderClicked: (id: Long) -> Unit,
    onFolderLongPressed: (id: Long) -> Unit,
    isTrashPlacement: Boolean = false,
    formatter: DateFormatter? = null,
    currentAppLanguage: AppLanguage = AppLanguage.ENGLISH
) {
    val isFocusedItem = rememberSaveable { mutableStateOf(false) }
    val isSelectedColors = if (isFocusedItem.value) tertiary else secondary
    val colors = if (isFocusedItem.value) outlineVariant else primaryContainer
    val backgroundColor = animateColorAsState(
        if (isSelected) isSelectedColors else colors,
        label = "",
        animationSpec = tween(Theme.animSpeed.common)
    )

    val isTrashItem = isTrashPlacement && formatter != null && folder.dateMovedToTrashRaw != null

    val removeDate = if (isTrashItem) rememberUpdatedState(
        formatter!!.fetchFormattedUiDate(
            folder.dateMovedToTrashRaw!!,
            currentAppLanguage
        )
    ) else remember { mutableStateOf("") }

    BoxWithScaleInOutOnClick(
        onClick = { if (folder.isSelectable) onFolderClicked(folder.id) },
        onLongClick = { if (folder.isSelectable) onFolderLongPressed(folder.id) },
        backgroundColor = backgroundColor,
        shape = Shape.cornerNormal,
        modifier = modifier
            .padding(top = dp6, bottom = dp6)
            .onFocusChanged { isFocusedItem.value = it.isFocused }
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
                    text = "${text.trash.hintRemovedDatePrefix} ${removeDate.value}",
                    style = TextDesign.captionSmall.copy(color = inverseSurface),
                    modifier = modifier.padding(start = dp16, top = dp8, bottom = dp16)
                )
            }
        }
    }
}