package ru.maksonic.beresta.screen.trash_list.folders.ui.widget

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import ru.maksonic.beresta.feature.folders_chips.api.FoldersApi
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.screen.trash_list.folders.core.Model
import ru.maksonic.beresta.screen.trash_list.folders.core.Msg
import ru.maksonic.beresta.screen.trash_list.folders.ui.SendMessage
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.component.dp10
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.theme.images.AppImage
import ru.maksonic.beresta.ui.theme.images.EmptyTrash
import ru.maksonic.beresta.ui.widget.functional.animation.animateDp
import ru.maksonic.beresta.ui.widget.functional.isVisibleFirstItemOffset
import ru.maksonic.beresta.ui.widget.placeholder.ScreenPlaceholder

/**
 * @Author maksonic on 12.07.2023
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun FoldersList(
    model: State<Model>,
    send: SendMessage,
    folderItemUi: FoldersApi.FolderItem.Ui,
    placeholderUi: FoldersApi.ListPlaceholder.Ui,
    updateFirstVisibleItemOffset: (Boolean) -> Unit,
    modifier: Modifier
) {
    val scrollState = rememberLazyListState()
    val isVisibleFirstItemOffset = scrollState.isVisibleFirstItemOffset()
    val bottomPadding = Theme.widgetSize.bottomBarNormalHeight.plus(dp10)
    val bottomContentPadding = animateDp(if (model.value.isSelectionState) bottomPadding else dp10)

    LaunchedEffect(isVisibleFirstItemOffset.value) {
        updateFirstVisibleItemOffset(isVisibleFirstItemOffset.value)
    }
    BoxWithConstraints {
        val maxHeight = this.maxHeight

        LazyColumn(
            state = scrollState,
            modifier = modifier,
            contentPadding = PaddingValues(
                top = dp10,
                start = dp16,
                end = dp16,
                bottom = bottomContentPadding.value
            )
        ) {
            when {
                model.value.base.isLoading -> {
                    item {
                        placeholderUi.PlaceholderTrash(Modifier.height(maxHeight))
                    }
                }

                model.value.folders.data.isEmpty() -> {
                    item {
                        ScreenPlaceholder(
                            imageVector = AppImage.EmptyTrash,
                            message = text.trash.messageEmptyTrashFoldersList,
                            modifier = Modifier.fillParentMaxHeight()
                        )
                    }
                }

                else -> {
                    items(model.value.folders.data) { folder ->
                        folderItemUi.Widget(
                            isSelected = model.value.selectedList.contains(folder),
                            isCurrent = false,
                            folder = folder,
                            isTrashPlacement = true,
                            onFolderClicked = { send(Msg.Ui.OnFolderClicked(folder.id)) },
                            onFolderLongPressed = { send(Msg.Ui.OnFolderLongClicked(folder.id)) },
                            modifier = modifier.animateItemPlacement()
                        )
                    }
                }
            }
        }
    }
}
