package ru.maksonic.beresta.screen.settings

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import kotlinx.coroutines.flow.Flow
import org.koin.androidx.compose.koinViewModel
import ru.maksonic.beresta.navigation.router.router.SettingsScreenRouter
import ru.maksonic.beresta.screen.settings.core.*
import ru.maksonic.beresta.ui.theme.color.background
import ru.maksonic.beresta.ui.theme.color.tertiaryContainer
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.widget.SystemStatusBar
import ru.maksonic.beresta.ui.widget.bar.TopAppBarNormal
import ru.maksonic.beresta.ui.widget.functional.HandleEffectsWithLifecycle
import ru.maksonic.beresta.ui.widget.functional.isVisibleFirstItem

/**
 * @Author maksonic on 16.01.2023
 */
internal typealias SendMessage = (Msg) -> Unit

@Composable
fun SettingsScreen(router: SettingsScreenRouter, sandbox: SettingsSandbox = koinViewModel()) {
    val model = sandbox.model.collectAsState().value

    HandleUiEffects(sandbox.effects, router)

    Content(model, sandbox::sendMsg)
}

@Composable
private fun Content(model: Model, send: SendMessage, modifier: Modifier = Modifier) {
    val scrollState = rememberLazyListState()
    val firstVisibleItem = scrollState.isVisibleFirstItem()
    val topBarColor = animateColorAsState(
        targetValue = if (firstVisibleItem.value) background else tertiaryContainer
    )

    Column {
        SystemStatusBar(changeableBackgroundColor = { topBarColor.value })
        TopAppBarNormal(
            title = stringResource(id = R.string.txt_title_setting),
            backgroundColor = { topBarColor.value },
            backAction = { send(Msg.Ui.OnTopBarBackPressed) }
        )
        LazyColumn(state = scrollState, modifier = modifier.weight(1f)) {
            items(50) {
                Text("Setting title", modifier = modifier.padding(top = dp16))
            }
        }
    }
}

@Composable
private fun HandleUiEffects(effects: Flow<Eff>, router: SettingsScreenRouter) {
    HandleEffectsWithLifecycle(effects) { eff ->
        when (eff) {
            is Eff.NavigateBack -> router.onBack()
        }
    }
}