package ru.maksonic.beresta.screen.folders.ui.widget

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.maksonic.beresta.feature.folders_chips.api.ui.ChipFeature
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.screen.folders.core.Model
import ru.maksonic.beresta.screen.folders.core.Msg
import ru.maksonic.beresta.screen.folders.ui.SendMessage
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.background
import ru.maksonic.beresta.ui.theme.color.onTertiaryContainer
import ru.maksonic.beresta.ui.theme.color.tertiaryContainer
import ru.maksonic.beresta.ui.theme.component.TextDesign
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.theme.icons.AppIcon
import ru.maksonic.beresta.ui.theme.icons.Edit
import ru.maksonic.beresta.ui.theme.icons.MoveTrash
import ru.maksonic.beresta.ui.theme.icons.Pin
import ru.maksonic.beresta.ui.theme.icons.Unpin
import ru.maksonic.beresta.ui.widget.bar.bottom.BaseBottomBarItem
import ru.maksonic.beresta.ui.widget.bar.bottom.BottomBarOld
import ru.maksonic.beresta.ui.widget.bar.bottom.DisabledBottomBarPlaceholder
import ru.maksonic.beresta.ui.widget.bar.system.SystemNavigationBarHeight
import ru.maksonic.beresta.ui.widget.functional.animation.AnimateContent
import ru.maksonic.beresta.ui.widget.functional.animation.AnimateFadeInOut
import ru.maksonic.beresta.ui.widget.functional.animation.animateDp
import ru.maksonic.beresta.ui.widget.functional.clickAction
import ru.maksonic.beresta.ui.widget.surface.SurfacePro

/**
 * @Author maksonic on 15.05.2023
 */
@Composable
internal fun BottomBarContent(
    model: State<Model>,
    send: SendMessage,
    isVisibleFirstFolderOffset: State<Boolean>,
    isCanScrollForward: State<Boolean>,
    modifier: Modifier = Modifier
) {
    val isSelection = rememberUpdatedState(model.value.isSelectionState)
    val isVisibleUnpinBottomBarIcon = rememberUpdatedState(model.value.isVisibleUnpinBottomBarIcon)
    val isDisabledBottomBar = rememberUpdatedState(
        model.value.selectedList.isEmpty() && model.value.isSelectionState
    )
    val containerElevation = animateDpAsState(
        if (isSelection.value) Theme.elevation.Level0
        else if (!isCanScrollForward.value) Theme.elevation.Level0 else Theme.elevation.Level2,
        label = ""
    )

    val selectionHeight = Theme.widgetSize.btnPrimaryHeight.plus(SystemNavigationBarHeight)
    val containerHeight = animateDp(
        if (isSelection.value) selectionHeight else Theme.widgetSize.bottomBarNormalHeight
    )
    val paddingEdge = animateDp(if (isSelection.value) 0.dp else dp16)
    val paddingBottom = animateDp(
        if (isSelection.value) 0.dp else SystemNavigationBarHeight.plus(dp16)
    )
    val tonal = animateDp(
        if (isVisibleFirstFolderOffset.value) Theme.tonal.Level0 else Theme.tonal.Level4
    )
    val color = animateColorAsState(
        targetValue = if (isSelection.value) background else tertiaryContainer, label = ""
    )
    val shape = animateDp(if (isSelection.value) 0.dp else 50.dp)


    SurfacePro(
        tonalElevation = tonal.value,
        shadowElevation = containerElevation.value,
        color = color.value,
        shape = RoundedCornerShape(shape.value),
        modifier = modifier
            .padding(
                start = paddingEdge.value,
                end = paddingEdge.value,
                bottom = paddingBottom.value
            )
            .fillMaxWidth()
            .height(containerHeight.value)
    ) {
        AnimateContent(isSelection.value) {
            if (it) {
                SelectedStateBarContent(
                    send = send,
                    selectedFoldersCount = model.value.selectedList.count(),
                    isVisibleUnpinBtn = isVisibleUnpinBottomBarIcon,
                    isDisabledBottomBar = isDisabledBottomBar
                )
            } else {
                Box(
                    modifier
                        .fillMaxWidth()
                        .height(Theme.widgetSize.btnPrimaryHeight)
                        .clickAction(onTertiaryContainer) {
                            send(Msg.Ui.OnAddNewFolderClicked)
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text.folders.titleDialogNewFolder,
                        style = TextDesign.title.copy(color = onTertiaryContainer)
                    )
                }
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
) {
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

    BottomBarOld(actions)

    AnimateFadeInOut(isDisabledBottomBar.value) {
        DisabledBottomBarPlaceholder()
    }
}