package ru.maksonic.beresta.screen.settings.tags.ui.widget

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberUpdatedState
import ru.maksonic.beresta.common.ui_kit.bar.top.TopAppCollapsingCounterBar
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.screen.settings.tags.core.Model
import ru.maksonic.beresta.screen.settings.tags.core.Msg
import ru.maksonic.beresta.screen.settings.tags.ui.Send

/**
 * @Author maksonic on 11.11.2023
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun TopBar(model: Model, send: Send, scrollBehavior: TopAppBarScrollBehavior?) {
    TopAppCollapsingCounterBar(
        scrollBehavior = scrollBehavior,
        count = rememberUpdatedState(model.tags.data.count { it.isSelected }),
        idleTitle = text.tags.topBarTitleTagsManagement,
        onBackClicked = { send(Msg.Ui.OnTopBarBackPressed) },
    )
}
