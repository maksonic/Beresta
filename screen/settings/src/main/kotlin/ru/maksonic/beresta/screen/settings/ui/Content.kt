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
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.screen.settings.Model
import ru.maksonic.beresta.screen.settings.Msg
import ru.maksonic.beresta.screen.settings.ui.widget.MultipleModalBottomSheetContent
import ru.maksonic.beresta.screen.settings.ui.widget.items.AccountSettingsItem
import ru.maksonic.beresta.screen.settings.ui.widget.items.GeneralSettingsItem
import ru.maksonic.beresta.screen.settings.ui.widget.items.SupportSettingsItem
import ru.maksonic.beresta.ui.theme.color.background
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.widget.bar.top.TopAppBarCollapsingLarge
import ru.maksonic.beresta.ui.widget.sheet.ModalBottomSheetDefault

/**
 * @Author maksonic on 26.07.2023
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun Content(
    model: Model,
    send: SendMessage,
    modifier: Modifier = Modifier
) {
    val currentSheetContent = rememberUpdatedState(model.currentSheetContent)
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

            Box(modifier.padding(paddings)) {
                Column(
                    modifier = modifier
                        .verticalScroll(scrollState)
                        .fillMaxSize()
                ) {
                    GeneralSettingsItem(send, model.currentTheme, model.isDarkTheme)
                    AccountSettingsItem(send)
                    SupportSettingsItem(send)
                    Spacer(modifier.size(dp16))
                }
            }
        }
        if (model.isVisibleModalSheet) {
            ModalBottomSheetDefault(
                sheetState = model.modalBottomSheetState,
                onDismissRequest = { send(Msg.Inner.HiddenModalBottomSheet) },
            ) {
                MultipleModalBottomSheetContent(send, currentSheetContent)
            }
        }
    }
}