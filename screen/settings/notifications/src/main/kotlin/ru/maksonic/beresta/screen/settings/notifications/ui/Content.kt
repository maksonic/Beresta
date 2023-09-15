package ru.maksonic.beresta.screen.settings.notifications.ui

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
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.screen.settings.notifications.core.Model
import ru.maksonic.beresta.screen.settings.notifications.core.Msg
import ru.maksonic.beresta.screen.settings.notifications.ui.widget.items.SoundsItem
import ru.maksonic.beresta.ui.theme.color.background
import ru.maksonic.beresta.ui.widget.bar.top.TopAppBarCollapsingLarge

/**
 * @Author maksonic on 07.07.2023
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun Content(
    model: Model,
    send: SendMessage,
    modifier: Modifier = Modifier,
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
                    title = text.settingsNotifications.topBarTitle,
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
                SoundsItem(model, send)
            }
        }
    }
}

