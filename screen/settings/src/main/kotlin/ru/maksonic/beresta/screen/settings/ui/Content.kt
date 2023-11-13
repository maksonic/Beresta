package ru.maksonic.beresta.screen.settings.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import ru.maksonic.beresta.common.ui_kit.bar.top.TopAppBarCollapsingLarge
import ru.maksonic.beresta.common.ui_kit.sheet.ModalBottomSheetContainer
import ru.maksonic.beresta.common.ui_theme.colors.background
import ru.maksonic.beresta.common.ui_theme.provide.dp16
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.screen.settings.core.Model
import ru.maksonic.beresta.screen.settings.core.Msg
import ru.maksonic.beresta.screen.settings.ui.widget.MultipleModalBottomSheetContent
import ru.maksonic.beresta.screen.settings.ui.widget.items.AccountSettingsItem
import ru.maksonic.beresta.screen.settings.ui.widget.items.GeneralSettingsItem
import ru.maksonic.beresta.screen.settings.ui.widget.items.SupportSettingsItem
import ru.maksonic.beresta.screen.settings.ui.widget.items.TagsSettingsItem

/**
 * @Author maksonic on 26.07.2023
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun Content(
    model: Model,
    send: Send,
    modalBottomSheetState: SheetState,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    val scrollBehavior =
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())

    Box {
        Scaffold(
            topBar = {
                TopAppBarCollapsingLarge(
                    scrollBehavior = scrollBehavior,
                    title = text.settings.topBarTitle,
                    onBackAction = { send(Msg.Ui.OnTopBarBackPressed) }
                )
            },
            containerColor = background,
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
        ) { paddings ->
            Column(
                modifier = modifier
                    .verticalScroll(scrollState)
                    .padding(paddings)
                    .fillMaxSize()
            ) {
                GeneralSettingsItem(send, model.currentTheme, model.isDarkTheme)
                AccountSettingsItem(send)
                TagsSettingsItem(send)
                SupportSettingsItem(send)
                Spacer(modifier.size(dp16))
            }
        }
        if (model.modalSheet.isVisible) {
            ModalBottomSheetContainer(
                sheetState = modalBottomSheetState,
                onDismissRequest = { send(Msg.Inner.HiddenModalBottomSheet) },
            ) {
                MultipleModalBottomSheetContent(model)
            }
        }
    }
}