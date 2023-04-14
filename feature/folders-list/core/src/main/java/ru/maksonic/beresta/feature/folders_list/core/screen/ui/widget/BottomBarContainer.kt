package ru.maksonic.beresta.feature.folders_list.core.screen.ui.widget

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import ru.maksonic.beresta.feature.folders_list.core.screen.core.Msg
import ru.maksonic.beresta.feature.folders_list.core.screen.ui.SendMessage
import ru.maksonic.beresta.feature.language_selector.api.provider.text
import ru.maksonic.beresta.feature.selected_items_counter_panel.api.SelectedItemsPanelUiApi
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.widget.SurfacePro
import ru.maksonic.beresta.ui.widget.SystemNavigationBarHeight
import ru.maksonic.beresta.ui.widget.button.PrimaryButton

/**
 * @Author maksonic on 05.04.2023
 */
@Composable
internal fun BottomBarContainer(
    send: SendMessage,
    panelCounterApi: SelectedItemsPanelUiApi,
    selectedCount: () -> Int,
    isShowUnpin: Boolean,
    isScrolledToBottom: () -> Boolean,
    isSelectionState: Boolean,
    modifier: Modifier = Modifier
) {
    val panelTransition = animateDpAsState(
        if (isSelectionState) 0.dp else
            Theme.widgetSize.bottomPanelHeightSelected.plus(SystemNavigationBarHeight)
    )

    val btnElevation = animateDpAsState(
        if (isScrolledToBottom()) Theme.elevation.Level0 else Theme.elevation.Level2
    )

    Box(modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {

        PrimaryButton(
            action = { send(Msg.Ui.OnAddFolderClicked) },
            title = text.folders.titleNewFolder,
            elevation = ButtonDefaults.elevation(
                defaultElevation = btnElevation.value
            ),
            modifier = modifier
                .fillMaxWidth()
                .systemBarsPadding()
                .padding(bottom = dp16, start = dp16, end = dp16)
        )

        SurfacePro(
            tonalElevation = Theme.tonal.Level4,
            modifier = modifier
                .fillMaxWidth()
                .graphicsLayer {
                    translationY = panelTransition.value.toPx()
                }

        ) { color ->
            BottomBarContent(
                send = send,
                panelCounterApi = panelCounterApi,
                selectedCount = selectedCount,
                isShowUnpin = isShowUnpin,
                backgroundColor = { color },
            )
        }
    }
}