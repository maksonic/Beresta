package ru.maksonic.beresta.feature.search_bar.core.ui.content

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import ru.maksonic.beresta.feature.top_bar_counter.api.TopBarCounterApi
import ru.maksonic.beresta.ui.widget.SystemStatusBar

/**
 * @Author maksonic on 30.04.2023
 */
@Composable
internal fun SearchBarSelectedContent(
    counterApi: TopBarCounterApi.Ui
) {
    Column {
        SystemStatusBar()
        counterApi.Widget(true)
    }
}