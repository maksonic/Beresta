package ru.maksonic.beresta.screen.folders.ui

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import ru.maksonic.beresta.feature.folders_chips.api.FoldersApi
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.screen.folders.core.Model
import ru.maksonic.beresta.screen.folders.core.Msg
import ru.maksonic.beresta.screen.folders.ui.widget.BottomBarContent
import ru.maksonic.beresta.screen.folders.ui.widget.FoldersList
import ru.maksonic.beresta.screen.folders.ui.widget.TopBar
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.background
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.widget.bar.SnackBarAction
import ru.maksonic.beresta.ui.widget.bar.system.SystemNavigationBarHeight
import ru.maksonic.beresta.ui.widget.functional.animation.AnimateFadeInOut
import ru.maksonic.beresta.ui.widget.functional.animation.animateDp
import ru.maksonic.beresta.ui.widget.functional.isVisibleFirstItemOffset

/**
 * @Author maksonic on 15.05.2023
 */
internal typealias SendMessage = (Msg) -> Unit

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun Content(
    foldersUiItemApi: FoldersApi.Ui.FolderItem,
    chipsDialogApi: FoldersApi.Ui.AddChipDialog,
    model: State<Model>,
    send: SendMessage,
    modifier: Modifier = Modifier
) {
    val scrollBehavior =
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())
    val scrollState = rememberLazyListState()
    val isVisibleFirstFolderOffset = scrollState.isVisibleFirstItemOffset()
    val isCanScrollForward = rememberUpdatedState(scrollState.canScrollForward)
    val isSelectionState = rememberUpdatedState(model.value.isSelectionState)
    val idlePadding = Theme.widgetSize.btnPrimaryHeight.plus(dp16).plus(SystemNavigationBarHeight)
    val selectionPadding = Theme.widgetSize.bottomBarNormalHeight.plus(SystemNavigationBarHeight)
    val snackBarPadding = animateDp(if (isSelectionState.value) selectionPadding else idlePadding)

    BackHandler(model.value.isSelectionState) {
        send(Msg.Ui.CancelSelectionState)
    }

    Box(modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
        Scaffold(
            topBar = { TopBar(scrollBehavior, model, send) },
            containerColor = background,
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
        ) { paddings ->
            Box(modifier.padding(paddings)) {
                FoldersList(
                    scrollState = scrollState,
                    foldersUiItemApi = foldersUiItemApi, model = model, send = send
                )
            }
        }

        AnimateFadeInOut(!model.value.base.isLoading) {
            BottomBarContent(model, send, isVisibleFirstFolderOffset, isCanScrollForward)
        }

        AnimatedVisibility(model.value.isVisibleRemovedSnackBar) {
            SnackBarAction(
                message = text.folders.hintRemovedFoldersCount.plus(
                    " ${model.value.removedList.count()}"
                ),
                actionTitle = text.shared.btnTitleCancel,
                onClick = { send(Msg.Ui.OnSnackUndoRemoveFoldersClicked) },
                modifier = Modifier.padding(bottom = snackBarPadding.value)
            )
        }

        chipsDialogApi.Widget()
    }
}