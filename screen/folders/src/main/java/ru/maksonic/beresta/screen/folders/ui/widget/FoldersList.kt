package ru.maksonic.beresta.screen.folders.ui.widget

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import ru.maksonic.beresta.feature.folders_chips.api.FoldersApi
import ru.maksonic.beresta.screen.folders.core.Model
import ru.maksonic.beresta.screen.folders.core.Msg
import ru.maksonic.beresta.screen.folders.ui.SendMessage
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.theme.component.dp6
import ru.maksonic.beresta.ui.theme.images.AppImage
import ru.maksonic.beresta.ui.theme.images.ErrorFolderPlaceholder
import ru.maksonic.beresta.ui.widget.functional.animation.animateDp
import ru.maksonic.beresta.ui.widget.placeholder.ScreenPlaceholder

/**
 * @Author maksonic on 15.05.2023
 */
@Composable
internal fun FoldersList(
    scrollState: LazyListState,
    foldersUiItemApi: FoldersApi.Ui.FolderItem,
    model: State<Model>,
    send: SendMessage,
    modifier: Modifier = Modifier
) {
    Box(modifier.fillMaxSize()) {
        when {
            model.value.base.isLoading -> PlaceholderContent(modifier)
            model.value.base.successAfterLoading -> {
                FetchedSuccess(
                    scrollState = scrollState,
                    foldersUiItemApi = foldersUiItemApi,
                    model = model,
                    send = send,
                    modifier = modifier
                )
            }

            model.value.base.failAfterLoading -> {
                ScreenPlaceholder(
                    imageVector = AppImage.ErrorFolderPlaceholder,
                    message = model.value.base.failMessage,
                    isError = true,
                    onErrorBtnClicked = { send(Msg.Ui.RetryFetchData) }
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun FetchedSuccess(
    scrollState: LazyListState,
    foldersUiItemApi: FoldersApi.Ui.FolderItem,
    model: State<Model>,
    send: SendMessage,
    modifier: Modifier
) {
    val defaultPadding = Theme.widgetSize.bottomBarNormalHeight.plus(dp6)
    val bottomContentPadding = animateDp(
        if (model.value.isSelectionState) defaultPadding else defaultPadding.plus(dp16)
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
        itemsIndexed(
            items = model.value.folders.data,
            key = { index, item -> if (index == 0) index else item.id }) { _, folder ->
            foldersUiItemApi.Widget(
                isSelected = model.value.selectedList.contains(folder) && folder.isSelectable,
                folder = folder,
                isTrashPlacement = false,
                onFolderClicked = { send(Msg.Ui.OnFolderClicked(folder.id)) },
                onFolderLongPressed = { send(Msg.Ui.OnFolderLongPressed(folder.id)) },
                modifier = modifier.animateItemPlacement()
            )
        }
    }
}