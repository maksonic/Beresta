package ru.maksonic.beresta.feature.notes.folders.core.screen.ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.style.TextOverflow
import ru.maksonic.beresta.feature.notes.folders.api.ui.NoteFolderUi
import ru.maksonic.beresta.feature.notes.folders.api.ui.isDefaultId
import ru.maksonic.beresta.feature.notes.folders.core.screen.core.Model
import ru.maksonic.beresta.feature.notes.folders.core.screen.core.Msg
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.onPrimaryContainer
import ru.maksonic.beresta.ui.theme.color.outline
import ru.maksonic.beresta.ui.theme.color.outlineVariant
import ru.maksonic.beresta.ui.theme.color.primary
import ru.maksonic.beresta.ui.theme.color.primaryContainer
import ru.maksonic.beresta.ui.theme.color.secondary
import ru.maksonic.beresta.ui.theme.color.tertiary
import ru.maksonic.beresta.ui.theme.color.tertiaryContainer
import ru.maksonic.beresta.ui.theme.component.Shape
import ru.maksonic.beresta.ui.theme.component.TextDesign
import ru.maksonic.beresta.ui.theme.component.dp12
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.theme.component.dp32
import ru.maksonic.beresta.ui.theme.component.dp6
import ru.maksonic.beresta.ui.theme.component.dp8
import ru.maksonic.beresta.ui.theme.icons.AppIcon
import ru.maksonic.beresta.ui.theme.icons.Done
import ru.maksonic.beresta.ui.theme.icons.Pin
import ru.maksonic.beresta.ui.widget.button.BoxWithScaleInOutOnClick
import ru.maksonic.beresta.ui.widget.functional.animation.AnimateFadeInOut

/**
 * @Author maksonic on 15.05.2023
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FoldersListContent(
    model: Model,
    send: SendMessage,
    scrollState: LazyListState,
    modifier: Modifier
) {
    val defaultPadding = Theme.widgetSize.bottomBarHeightDefault.plus(dp6)
    val bottomContentPadding = animateDpAsState(
        if (model.isSelectionState) defaultPadding else defaultPadding.plus(dp16), label = "",
        animationSpec = tween(Theme.animSpeed.common)
    )
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
        items(model.folders.data, key = { it.id }) { folder ->
            val update = folder.copy(
                isCurrent = folder.id == model.currentSelectedFolderId,
                isSelected = model.selectedFolders.contains(folder),
            )
            FolderItem(
                folder = update,
                onFolderClicked = { send(Msg.Ui.OnFolderClicked(folder.id)) },
                onFolderLongPressed = { send(Msg.Ui.OnFolderLongPressed(folder.id)) },
                animPlacementModifier = Modifier.animateItemPlacement()
            )
        }
    }
}

@Composable
private fun FolderItem(
    folder: NoteFolderUi,
    onFolderClicked: (id: Long) -> Unit,
    onFolderLongPressed: (id: Long) -> Unit,
    animPlacementModifier: Modifier,
    modifier: Modifier = Modifier
) {
    val isFocusedItem = rememberSaveable { mutableStateOf(false) }
    val isSelectedColors = if (isFocusedItem.value) tertiary else secondary
    val colors = if (isFocusedItem.value) outlineVariant else primaryContainer
    val backgroundColor = animateColorAsState(
        if (folder.isSelected) isSelectedColors else colors,
        label = "",
        animationSpec = tween(Theme.animSpeed.common)
    )

    BoxWithScaleInOutOnClick(
        onClick = { onFolderClicked(folder.id) },
        onLongClick = { onFolderLongPressed(folder.id) },
        backgroundColor = backgroundColor,
        shape = Shape.cornerNormal,
        modifier = animPlacementModifier
            .padding(top = dp6, bottom = dp6)
            .onFocusChanged { isFocusedItem.value = it.isFocused }
    ) {
        Row(
            modifier
                .fillMaxWidth()
                .height(Theme.widgetSize.minimumTouchTargetSize)
                .padding(start = dp8),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(modifier.size(dp32), contentAlignment = Alignment.Center) {
                if (folder.isCurrent) {
                    Icon(
                        imageVector = AppIcon.Done,
                        tint = tertiaryContainer,
                        contentDescription = ""
                    )
                }
            }

            Text(
                text = folder.title,
                style = TextDesign.title.copy(color = onPrimaryContainer),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = modifier
                    .weight(1f)
                    .padding(start = dp8, end = dp8)
            )

            Text(
                text = "0",
                style = TextDesign.bodyPrimaryMedium.copy(color = outline),
                maxLines = 1,
                modifier = modifier.padding(end = dp8)
            )

            Box(
                modifier
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
}