package ru.maksonic.beresta.screen.settings.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import ru.maksonic.beresta.feature.language_selector.api.provider.text
import ru.maksonic.beresta.navigation.router.router.SettingsScreenRouter
import ru.maksonic.beresta.screen.settings.Eff
import ru.maksonic.beresta.screen.settings.Model
import ru.maksonic.beresta.screen.settings.Msg
import ru.maksonic.beresta.screen.settings.SettingsSandbox
import ru.maksonic.beresta.screen.settings.presentation.widget.ModalSheetContent
import ru.maksonic.beresta.screen.settings.presentation.widget.MultipleModalBottomSheetContent
import ru.maksonic.beresta.screen.settings.presentation.widget.setting_item.AccountSettingsItem
import ru.maksonic.beresta.screen.settings.presentation.widget.setting_item.GeneralSettingsItem
import ru.maksonic.beresta.screen.settings.presentation.widget.setting_item.SupportSettingsItem
import ru.maksonic.beresta.ui.theme.AppTheme
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.background
import ru.maksonic.beresta.ui.theme.color.transparent
import ru.maksonic.beresta.ui.widget.bar.TopAppBarCollapsingLarge
import ru.maksonic.beresta.ui.widget.functional.HandleEffectsWithLifecycle

/**
 * @Author maksonic on 16.01.2023
 */
internal typealias SendMessage = (Msg) -> Unit

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SettingsScreen(router: SettingsScreenRouter, sandbox: SettingsSandbox = koinViewModel()) {

    val model = sandbox.model.collectAsStateWithLifecycle().value

    HandleUiEffects(sandbox.effects, router, modalSheetState = model.modalBottomSheetState)

    Content(
        model = model,
        send = sandbox::send,
        modalSheetState = { model.modalBottomSheetState },
        currentSheetContent = model.currentSheetContent
    )
}

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
private fun Content(
    model: Model,
    send: SendMessage,
    currentSheetContent: ModalSheetContent,
    modalSheetState: () -> ModalBottomSheetState,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberLazyListState()
    val scrollBehavior =
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())

    ModalBottomSheetLayout(
        sheetState = modalSheetState(),
        sheetBackgroundColor = transparent,
        sheetContentColor = transparent,
        sheetElevation = Theme.elevation.Level0,
        sheetContent = {
            MultipleModalBottomSheetContent(
                send = send,
                currentSheetContent = currentSheetContent,
                modalSheetState = modalSheetState,
                modifier = modifier
            )
        }
    ) {

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
            val themeHint = when (model.currentTheme) {
                AppTheme.SYSTEM -> text.settings.titleThemeSystem
                AppTheme.LIGHT -> text.settings.titleThemeLight
                AppTheme.DARK -> text.settings.themeTitleNight
                AppTheme.HIGH_CONTRAST -> text.settings.themeTitleHighContrast
            }

            LazyColumn(
                state = scrollState,
                modifier = modifier
                    .fillMaxSize()
                    .padding(paddingValues = paddings)
            ) {
                item {
                    GeneralSettingsItem(send, themeHint)
                    AccountSettingsItem(send)
                    SupportSettingsItem(send)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun HandleUiEffects(
    effects: Flow<Eff>,
    router: SettingsScreenRouter,
    modalSheetState: ModalBottomSheetState,
) {
    val scope = rememberCoroutineScope()

    HandleEffectsWithLifecycle(effects) { eff ->
        when (eff) {
            is Eff.NavigateBack -> router.onBack()
            is Eff.HideModalSheet -> {
                scope.launch {
                    modalSheetState.hide()
                }
            }
            is Eff.ShowModalSheet -> {
                scope.launch {
                    modalSheetState.show()
                }
            }
        }
    }
}
