package ru.maksonic.beresta.screen.folders.ui.widget

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import org.koin.compose.koinInject
import ru.maksonic.beresta.feature.folders_chips.api.FoldersApi
import ru.maksonic.beresta.feature.folders_chips.api.ui.FoldersSorter
import ru.maksonic.beresta.feature.sorting_sheet.api.LocalListSortState
import ru.maksonic.beresta.feature.sorting_sheet.api.SortingSheetApi
import ru.maksonic.beresta.feature.sorting_sheet.api.listUiSortState
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.screen.folders.core.Model
import ru.maksonic.beresta.screen.folders.core.Msg
import ru.maksonic.beresta.screen.folders.ui.SendMessage
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.theme.component.dp6
import ru.maksonic.beresta.ui.theme.icons.AppIcon
import ru.maksonic.beresta.ui.theme.icons.PhonelinkLock
import ru.maksonic.beresta.ui.theme.images.AppImage
import ru.maksonic.beresta.ui.theme.images.ErrorFolderPlaceholder
import ru.maksonic.beresta.ui.widget.button.QuaternaryButton
import ru.maksonic.beresta.ui.widget.functional.animation.animateDp
import ru.maksonic.beresta.ui.widget.placeholder.ScreenPlaceholder

/**
 * @Author maksonic on 15.05.2023
 */
@Composable
internal fun FoldersList(
    model: State<Model>,
    send: SendMessage,
    modifier: Modifier = Modifier,
    foldersPlaceholderUi: FoldersApi.ListPlaceholder.Ui = koinInject()
) {
    Box(modifier.fillMaxSize()) {
        when {
            model.value.base.isLoading -> foldersPlaceholderUi.Placeholder(modifier)
            model.value.base.successAfterLoading -> FetchedSuccess(model, send, modifier)
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
    model: State<Model>,
    send: SendMessage,
    modifier: Modifier,
    listSortUi: SortingSheetApi.Ui = koinInject(),
    folderItemUi: FoldersApi.FolderItem.Ui = koinInject(),
    chipsRowUi: FoldersApi.ChipsRow.Ui = koinInject(),
) {
    val scrollState = rememberLazyListState()
    val defaultPadding = Theme.widgetSize.bottomBarNormalHeight.plus(dp6)
    val bottomContentPadding = animateDp(
        if (model.value.isSelectionState) defaultPadding else defaultPadding.plus(dp16)
    )

    CompositionLocalProvider(LocalListSortState provides listSortUi.sharedState.value) {
        val foldersSorter = rememberUpdatedState(
            FoldersSorter(
                list = model.value.folders.data,
                order = listUiSortState.folders.order,
                isSortPinned = listUiSortState.folders.isSortPinned,
                sort = listUiSortState.folders.sort
            )
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

            if (!model.value.isMoveNotesToFolder) {
                item {
                    QuaternaryButton(
                        isEnabled = !model.value.isSelectionState,
                        title = text.hiddenNotes.topBarTitle,
                        prefixIcon = AppIcon.PhonelinkLock,
                        onNavigateToFoldersClicked = { send(Msg.Ui.OnToHiddenNotesClicked) }
                    )
                }
            }

            itemsIndexed(
                items = foldersSorter.value.sortedList,
                key = { index, item -> if (index == 0) index else item.id }) { _, folder ->
                folderItemUi.Widget(
                    isSelected = model.value.selectedList.contains(folder) && folder.isSelectable,
                    isCurrent = chipsRowUi.currentSelectedId.value == folder.id,
                    folder = folder,
                    isTrashPlacement = false,
                    onFolderClicked = { send(Msg.Ui.OnFolderClicked(folder.id)) },
                    onFolderLongPressed = { send(Msg.Ui.OnFolderLongPressed(folder.id)) },
                    modifier = modifier.animateItemPlacement()
                )
            }
        }
    }
}