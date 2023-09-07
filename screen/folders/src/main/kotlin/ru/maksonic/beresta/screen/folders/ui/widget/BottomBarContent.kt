package ru.maksonic.beresta.screen.folders.ui.widget

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SnackbarHost
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.koinInject
import ru.maksonic.beresta.feature.folders_chips.api.FoldersApi
import ru.maksonic.beresta.feature.folders_chips.api.ui.ChipFeature
import ru.maksonic.beresta.feature.folders_chips.api.ui.LocalCurrentSelectedFolderState
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.screen.folders.core.Model
import ru.maksonic.beresta.screen.folders.core.Msg
import ru.maksonic.beresta.screen.folders.ui.SendMessage
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.theme.component.dp8
import ru.maksonic.beresta.ui.theme.icons.AppIcon
import ru.maksonic.beresta.ui.theme.icons.Edit
import ru.maksonic.beresta.ui.theme.icons.MoveTrash
import ru.maksonic.beresta.ui.theme.icons.Pin
import ru.maksonic.beresta.ui.theme.icons.Unpin
import ru.maksonic.beresta.ui.widget.bar.SnackBar
import ru.maksonic.beresta.ui.widget.bar.bottom.BaseBottomBarItem
import ru.maksonic.beresta.ui.widget.bar.bottom.BottomBarOld
import ru.maksonic.beresta.ui.widget.bar.bottom.DisabledBottomBarPlaceholder
import ru.maksonic.beresta.ui.widget.button.PrimaryButton
import ru.maksonic.beresta.ui.widget.functional.animation.AnimateContent
import ru.maksonic.beresta.ui.widget.functional.animation.AnimateFadeInOut
import ru.maksonic.beresta.ui.widget.surface.SurfacePro

/**
 * @Author maksonic on 15.05.2023
 */
@Composable
internal fun BottomBarContent(
    model: State<Model>,
    send: SendMessage,
    modifier: Modifier = Modifier
) {
    val isSelection = rememberUpdatedState(model.value.isSelectionState)
    val isVisibleUnpinBottomBarIcon = rememberUpdatedState(model.value.isVisibleUnpinBottomBarIcon)
    val isDisabledBottomBar = rememberUpdatedState(
        model.value.selectedList.isEmpty() && model.value.isSelectionState
    )

    Column {
        SnackbarHost(
            hostState = model.value.snackState,
            snackbar = { SnackBar(model.value.snackState.currentSnackbarData) },
            modifier = Modifier.padding(bottom = dp8)
        )
        AnimateContent(isSelection.value) {
            if (it) {
                SelectedStateBarContent(
                    send = send,
                    selectedFoldersCount = model.value.selectedList.count(),
                    isVisibleUnpinBtn = isVisibleUnpinBottomBarIcon,
                    isDisabledBottomBar = isDisabledBottomBar
                )
            } else {
                PrimaryButton(
                    action = { send(Msg.Ui.OnAddNewFolderClicked) },
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
private fun SelectedStateBarContent(
    send: SendMessage,
    selectedFoldersCount: Int,
    isVisibleUnpinBtn: State<Boolean>,
    isDisabledBottomBar: State<Boolean>,
    chipsRowApi: FoldersApi.Ui.ChipsRow = koinInject()
) {
    val currentFolder = chipsRowApi.currentSelectedId.state.collectAsStateWithLifecycle()
    CompositionLocalProvider(LocalCurrentSelectedFolderState provides currentFolder.value) {
        val currentSelectedFolderId = rememberUpdatedState(ChipFeature.currentSelectedFolder)

        val actions = arrayOf(
            BaseBottomBarItem(
                label = if (isVisibleUnpinBtn.value) text.shared.btnTitleUnpin else text.shared.btnTitlePin,
                icon = if (isVisibleUnpinBtn.value) AppIcon.Unpin else AppIcon.Pin,
                action = {
                    send(Msg.Ui.OnBottomBarPinSelectedClicked)
                    send(Msg.Ui.CancelSelectionState)
                }),
            BaseBottomBarItem(
                label = text.shared.btnTitleChange,
                icon = AppIcon.Edit,
                action = {
                    send(Msg.Ui.OnBottomBarEditSelectedClicked)
                    send(Msg.Ui.CancelSelectionState)
                },
                isEmpty = selectedFoldersCount > 1
            ),
            BaseBottomBarItem(
                label = text.shared.btnTitleRemove,
                icon = AppIcon.MoveTrash,
                action = {
                    send(Msg.Ui.OnBottomBarRemoveSelectedClicked(currentSelectedFolderId.value))
                }
            )
        )

        SurfacePro(tonalElevation = Theme.tonal.Level4) {
            BottomBarOld(actions)
        }

        AnimateFadeInOut(isDisabledBottomBar.value) {
            DisabledBottomBarPlaceholder()
        }
    }
}