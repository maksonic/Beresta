package ru.maksonic.beresta.screen.folders.ui.widget

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import ru.maksonic.beresta.common.ui_kit.animation.AnimateContent
import ru.maksonic.beresta.common.ui_kit.bar.bottom.BottomAppBar
import ru.maksonic.beresta.common.ui_kit.bar.bottom.BottomBarItem
import ru.maksonic.beresta.common.ui_kit.bar.snackbar.SnackBar
import ru.maksonic.beresta.common.ui_kit.bar.snackbar.SnackBarHost
import ru.maksonic.beresta.common.ui_kit.button.ButtonPrimary
import ru.maksonic.beresta.common.ui_kit.icons.AppIcon
import ru.maksonic.beresta.common.ui_kit.icons.Edit
import ru.maksonic.beresta.common.ui_kit.icons.MoveTrash
import ru.maksonic.beresta.common.ui_kit.icons.Pin
import ru.maksonic.beresta.common.ui_kit.icons.Unpin
import ru.maksonic.beresta.common.ui_kit.surface.SurfacePro
import ru.maksonic.beresta.common.ui_theme.Theme
import ru.maksonic.beresta.common.ui_theme.provide.dp16
import ru.maksonic.beresta.common.ui_theme.provide.dp8
import ru.maksonic.beresta.feature.folders_list.ui.api.FoldersFeature
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.screen.folders.core.Model
import ru.maksonic.beresta.screen.folders.core.Msg
import ru.maksonic.beresta.screen.folders.ui.Send

/**
 * @Author maksonic on 15.05.2023
 */
@Composable
internal fun BottomBar(
    model: Model,
    send: Send,
    modifier: Modifier = Modifier
) {
    Column {
        SnackBarHost(
            hostState = model.snackState,
            snackbar = { SnackBar(model.snackState.currentSnackBarData) },
            modifier = Modifier.padding(bottom = dp8)
        )
        AnimateContent(model.folders.isSelection) {
            if (it) {
                SelectedStateBarContent(model, send)
            } else {
                ButtonPrimary(
                    onClick = { send(Msg.Ui.OnAddNewFolderClicked) },
                    title = text.folders.titleDialogNewFolder,
                    modifier = modifier
                        .fillMaxWidth()
                        .navigationBarsPadding()
                        .padding(start = dp16, end = dp16, bottom = dp16)
                )
            }
        }
    }
}

@Composable
private fun SelectedStateBarContent(model: Model, send: Send) {
    val selectedList = rememberUpdatedState(model.folders.collection.data.filter { it.isSelected })
    val pinItemLabel = rememberUpdatedState(
        if (model.isVisibleUnpinBottomBarIcon) text.shared.btnTitleUnpin
        else text.shared.btnTitlePin
    )
    val pinItemIcon = rememberUpdatedState(
        if (model.isVisibleUnpinBottomBarIcon) AppIcon.Unpin else AppIcon.Pin
    )
    val currentSelectedFolderId = rememberUpdatedState(FoldersFeature.currentSelected)
    val items = arrayOf(
        BottomBarItem(
            label = pinItemLabel.value,
            icon = pinItemIcon.value,
            onClick = {
                send(Msg.Ui.OnBottomBarPinSelectedClicked).run { send(Msg.Ui.CancelSelectionState) }
            }),
        BottomBarItem(
            label = text.shared.btnTitleChange,
            icon = AppIcon.Edit,
            onClick = {
                send(Msg.Ui.OnBottomBarEditSelectedClicked)
                send(Msg.Ui.CancelSelectionState)
            },
            isEmpty = selectedList.value.count() > 1
        ),
        BottomBarItem(
            label = text.shared.btnTitleRemove,
            icon = AppIcon.MoveTrash,
            onClick = {
                send(Msg.Ui.OnBottomBarRemoveSelectedClicked(currentSelectedFolderId.value))
            }
        )
    )

    SurfacePro(tonalElevation = Theme.tonal.level4) {
        BottomAppBar(
            items = items,
            isDisabled = selectedList.value.isEmpty() && model.folders.isSelection
        )
    }
}