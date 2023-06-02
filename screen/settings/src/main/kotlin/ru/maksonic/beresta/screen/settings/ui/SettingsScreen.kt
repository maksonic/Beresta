package ru.maksonic.beresta.screen.settings.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.navigation.router.router.SettingsScreenRouter
import ru.maksonic.beresta.screen.settings.Eff
import ru.maksonic.beresta.screen.settings.Model
import ru.maksonic.beresta.screen.settings.Msg
import ru.maksonic.beresta.screen.settings.SettingsSandbox
import ru.maksonic.beresta.screen.settings.ui.widget.MultipleModalBottomSheetContent
import ru.maksonic.beresta.screen.settings.ui.widget.item.AccountSettingsItem
import ru.maksonic.beresta.screen.settings.ui.widget.item.GeneralSettingsItem
import ru.maksonic.beresta.screen.settings.ui.widget.item.SupportSettingsItem
import ru.maksonic.beresta.ui.theme.color.background
import ru.maksonic.beresta.ui.widget.bar.TopAppBarCollapsingLarge
import ru.maksonic.beresta.ui.widget.functional.HandleEffectsWithLifecycle
import ru.maksonic.beresta.ui.widget.sheet.ModalBottomSheetDefault

/**
 * @Author maksonic on 16.01.2023
 */
internal typealias SendMessage = (Msg) -> Unit

@Composable
fun SettingsScreen(router: SettingsScreenRouter) {
    Container(router = router)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Container(router: SettingsScreenRouter, sandbox: SettingsSandbox = koinViewModel()) {
    val model = sandbox.model.collectAsStateWithLifecycle()
    val currentSheetContent = rememberUpdatedState(model.value.currentSheetContent)

    HandleUiEffects(
        effects = sandbox.effects,
        hideSheet = { sandbox.send(Msg.Inner.UpdatedModalSheetState(false)) },
        router = router,
        modalBottomSheetState = model.value.modalBottomSheetState
    )
    Content(model.value, sandbox::send)

    if (model.value.isVisibleModalSheet) {
        ModalBottomSheetDefault(
            sheetState = model.value.modalBottomSheetState,
            onDismissRequest = { sandbox.send(Msg.Inner.UpdatedModalSheetState(false)) },
        ) {
            MultipleModalBottomSheetContent(sandbox::send, currentSheetContent)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Content(model: Model, send: SendMessage, modifier: Modifier = Modifier) {
    val scrollState = rememberScrollState()
    val scrollBehavior =
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())

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
                .fillMaxSize()
                .padding(paddingValues = paddings)
        ) {
            GeneralSettingsItem(send, model.currentTheme, model.isDarkTheme)
            AccountSettingsItem(send)
            SupportSettingsItem(send)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HandleUiEffects(
    effects: Flow<Eff>,
    hideSheet: () -> Unit,
    router: SettingsScreenRouter,
    modalBottomSheetState: SheetState,
) {
    val scope = rememberCoroutineScope()

    HandleEffectsWithLifecycle(effects) { eff ->
        when (eff) {
            is Eff.NavigateBack -> router.onBack()
            is Eff.HideModalSheet -> {
                scope.launch { modalBottomSheetState.hide() }.invokeOnCompletion {
                    if (!modalBottomSheetState.isVisible) {
                        hideSheet()
                    }
                }
            }
        }
    }
}
