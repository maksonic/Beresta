package ru.maksonic.beresta.feature.search_bar.ui.widget.state

import androidx.compose.runtime.Composable
import org.koin.compose.koinInject
import ru.maksonic.beresta.feature.search_bar.api.SearchBarApi
import ru.maksonic.beresta.feature.top_bar_counter.api.TopBarCounterApi

/**
 * @Author maksonic on 30.04.2023
 */
@Composable
internal fun SelectedContent(
    actions: Map<SearchBarApi.ActionKey, () -> Unit>,
    counterApi: TopBarCounterApi.Ui = koinInject()
) {
    counterApi.Widget(
        onCancelClicked = { actions[SearchBarApi.ActionKey.OnCancelClicked]?.invoke() },
        onShareSelectedClicked = { actions[SearchBarApi.ActionKey.OnShareClicked]?.invoke() },
        onSelectAllClicked = { actions[SearchBarApi.ActionKey.OnSelectAllClicked]?.invoke() }
    )
}