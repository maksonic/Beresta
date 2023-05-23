package ru.maksonic.beresta.feature.notes.folders.core.screen.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.maksonic.beresta.feature.notes.folders.core.screen.core.Model
import ru.maksonic.beresta.feature.notes.folders.core.screen.core.Msg
import ru.maksonic.beresta.feature.notes.list.api.BaseBottomBarItem
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.background
import ru.maksonic.beresta.ui.theme.color.onBackground
import ru.maksonic.beresta.ui.theme.color.onTertiaryContainer
import ru.maksonic.beresta.ui.theme.color.outline
import ru.maksonic.beresta.ui.theme.color.tertiaryContainer
import ru.maksonic.beresta.ui.theme.color.transparent
import ru.maksonic.beresta.ui.theme.component.TextDesign
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.theme.icons.AppIcon
import ru.maksonic.beresta.ui.theme.icons.Edit
import ru.maksonic.beresta.ui.theme.icons.MoveTrash
import ru.maksonic.beresta.ui.theme.icons.Pin
import ru.maksonic.beresta.ui.theme.icons.Unpin
import ru.maksonic.beresta.ui.widget.SurfacePro
import ru.maksonic.beresta.ui.widget.SystemNavigationBarHeight
import ru.maksonic.beresta.ui.widget.bar.BottomRippleBar
import ru.maksonic.beresta.ui.widget.bar.SnackBarAction
import ru.maksonic.beresta.ui.widget.functional.animation.AnimateContent
import ru.maksonic.beresta.ui.widget.functional.clickAction
import ru.maksonic.beresta.ui.widget.functional.isVisibleFirstItemOffset

/**
 * @Author maksonic on 15.05.2023
 */
@Composable
fun BottomBarContent(
    model: Model,
    send: SendMessage,
    scrollState: LazyListState,
    modifier: Modifier = Modifier
) {
    val isVisibleFirstFolderOffset = scrollState.isVisibleFirstItemOffset()
    val containerElevation = animateDpAsState(
        if (model.isSelectionState) Theme.elevation.Level0
        else if (!scrollState.canScrollForward) Theme.elevation.Level0 else Theme.elevation.Level2,
        label = ""
    )

    val selectionHeight = Theme.widgetSize.btnPrimaryHeight.plus(SystemNavigationBarHeight)
    val containerHeight = animateDpAsState(
        if (model.isSelectionState) selectionHeight else Theme.widgetSize.bottomBarNormalHeight,
        animationSpec = tween(Theme.animSpeed.common),
        label = ""
    )
    val paddingEdge = animateDpAsState(
        if (model.isSelectionState) 0.dp else dp16,
        animationSpec = tween(Theme.animSpeed.common), label = ""
    )
    val paddingBottom = animateDpAsState(
        if (model.isSelectionState) 0.dp else SystemNavigationBarHeight.plus(dp16),
        animationSpec = tween(Theme.animSpeed.common), label = ""
    )
    val tonal = animateDpAsState(
        if (isVisibleFirstFolderOffset.value) Theme.tonal.Level0 else Theme.tonal.Level4,
        animationSpec = tween(Theme.animSpeed.common), label = ""
    )
    val color = animateColorAsState(
        targetValue = if (model.isSelectionState) background else tertiaryContainer, label = ""
    )
    val shape = animateDpAsState(
        if (model.isSelectionState) 0.dp else dp16, animationSpec = tween(), label = ""
    )


    Box(modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
        Column {
            AnimatedVisibility(model.isVisibleRemovedSnackBar) {
                SnackBarAction(
                    message = text.folders.hintRemovedFoldersCount.plus(
                        " ${model.removedFolders.count()}"
                    ),
                    actionTitle = text.shared.btnTitleCancel,
                    onClick = { send(Msg.Ui.OnSnackUndoRemoveFoldersClicked) }
                )
            }

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
                AnimateContent(model.isSelectionState) {
                    if (it) {
                        SelectedStateBarContent(
                            send = send,
                            selectedFoldersCount = model.selectedFolders.count(),
                            isShowUnpinBtn = model.isShowUnpinBottomBarIcon
                        )
                    } else {
                        Box(
                            modifier
                                .fillMaxWidth()
                                .height(Theme.widgetSize.btnPrimaryHeight)
                                .clickAction(onTertiaryContainer) { send(Msg.Ui.OnAddNewFolderClicked) },
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
    }
}

@Composable
fun SelectedStateBarContent(
    send: SendMessage,
    selectedFoldersCount: Int,
    isShowUnpinBtn: Boolean,
    modifier: Modifier = Modifier
) {

    val editFolderItem = if (selectedFoldersCount > 1) null else BaseBottomBarItem(
        label = text.shared.btnTitleChange,
        icon = AppIcon.Edit,
        action = {
            send(Msg.Ui.OnBarEditClicked)
            send(Msg.Ui.CancelSelectionState)
        }
    )
    val actions = arrayOf(
        BaseBottomBarItem(
            label = if (isShowUnpinBtn) text.shared.btnTitleUnpin else text.shared.btnTitlePin,
            icon = if (isShowUnpinBtn) AppIcon.Unpin else AppIcon.Pin,
            action = {
                send(Msg.Ui.OnBarPinClicked)
                send(Msg.Ui.CancelSelectionState)
            }),
        editFolderItem,
        BaseBottomBarItem(
            label = text.shared.btnTitleRemove,
            icon = AppIcon.MoveTrash,
            action = { send(Msg.Ui.OnBarRemoveClicked) }
        )
    )

    BottomAppBar(
        modifier
            .fillMaxWidth()
            .navigationBarsPadding(),
        backgroundColor = transparent,
        elevation = Theme.elevation.Level0,
    ) {
        actions.forEach { item ->
            item?.let {
                BottomRippleBar(
                    selected = true,
                    onClick = item.action,
                    label = { Text(item.label) },
                    icon = { Icon(item.icon, contentDescription = "") },
                    unselectedContentColor = outline,
                    selectedContentColor = onBackground,
                )
            }
        }
    }
}