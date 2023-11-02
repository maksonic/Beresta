package ru.maksonic.beresta.screen.settings.security.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import org.koin.compose.koinInject
import ru.maksonic.beresta.common.ui_kit.bar.top.TopAppBarCollapsingLarge
import ru.maksonic.beresta.common.ui_theme.colors.background
import ru.maksonic.beresta.feature.hidden_notes_dialog.ui.api.HiddenNotesDialogUiApi
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.screen.settings.security.core.Model
import ru.maksonic.beresta.screen.settings.security.core.Msg
import ru.maksonic.beresta.screen.settings.security.ui.items.HiddenNotesSettingItem
import ru.maksonic.beresta.screen.settings.security.ui.items.PinCodeSettingsItem

/**
 * @Author maksonic on 03.08.2023
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun Content(
    model: Model,
    send: Send,
    modifier: Modifier = Modifier,
    hiddenNotesNotPinCreatedDialogApi: HiddenNotesDialogUiApi.NotCreatedPinDialog = koinInject(),
    hiddenNotesPinInputDialogUiApi: HiddenNotesDialogUiApi.PinInputDialog = koinInject(),
) {
    val scrollState = rememberScrollState()
    val scrollBehavior =
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())

    Box(
        modifier
            .fillMaxSize()
            .navigationBarsPadding()
    ) {
        Scaffold(
            topBar = {
                TopAppBarCollapsingLarge(
                    scrollBehavior = scrollBehavior,
                    title = text.settingsSecurity.topBarTitle,
                    onBackAction = { send(Msg.Ui.OnTopBarBackPressed) }
                )
            },
            containerColor = background,
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
        ) { paddings ->
            Column(
                modifier
                    .verticalScroll(scrollState)
                    .padding(paddings)
            ) {
                PinCodeSettingsItem(model, send)
                HiddenNotesSettingItem(model, send)
            }
        }

        hiddenNotesNotPinCreatedDialogApi.Widget(
            isVisible = model.isVisibleHiddenNotesNotCreatedPinDialog,
            hideDialog = { send(Msg.Inner.UpdatedHiddenNotesNotCreatedPinDialogVisibility(false)) },
            onAcceptClicked = { send(Msg.Ui.OnCreateHiddenNotesPinClicked) }
        )

        hiddenNotesPinInputDialogUiApi.Widget(
            isVisible = model.isVisibleHiddenNotesDialog,
            hideDialog = { send(Msg.Inner.UpdatedHiddenNotesDialogVisibility(false)) },
            onSuccessPin = { send(Msg.Inner.UpdatedHiddenNotesDialogVisibility(false)) },
            isBlocked = false,
            onBlockedBackPressed = {}
        )
    }
}

