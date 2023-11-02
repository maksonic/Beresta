package ru.maksonic.beresta.feature.ui.search_bar.core

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ru.maksonic.beresta.common.ui_theme.Theme
import ru.maksonic.beresta.common.ui_theme.colors.onSurface
import ru.maksonic.beresta.common.ui_theme.provide.dp4
import ru.maksonic.beresta.common.ui_theme.provide.dp48
import ru.maksonic.beresta.common.ui_theme.provide.dp56
import ru.maksonic.beresta.common.ui_theme.provide.dp8
import ru.maksonic.beresta.common.ui_theme.typography.TextDesign
import ru.maksonic.beresta.feature.ui.search_bar.core.widget.IconsRow
import ru.maksonic.beresta.feature.ui.search_bar.core.widget.SelectedItemsCounter
import ru.maksonic.beresta.feature.ui.search_bar.api.SearchBarUiApi
import ru.maksonic.beresta.feature.ui.search_bar.api.SearchBarUiState
import ru.maksonic.beresta.feature.ui.search_bar.api.isCollapsed
import ru.maksonic.beresta.feature.ui.search_bar.api.isSelection
import ru.maksonic.beresta.language_engine.shell.provider.text

/**
 * @Author maksonic on 25.08.2023
 */
@Composable
internal fun InnerContent(
    state: SearchBarUiState,
    actions: Map<SearchBarUiApi.ActionKey, () -> Unit>,
    modifier: Modifier = Modifier,
) {
    val edge = animateDpAsState(
        if (state.barState.isCollapsed) dp56 else dp4, tween(Theme.animVelocity.common), label = ""
    )

    Box(
        modifier
            .statusBarsPadding()
            .fillMaxWidth()
            .padding(top = dp8)
            .height(Theme.size.searchBarCollapsedHeight)
            .padding(start = edge.value, end = edge.value),
    ) {
        Box(
            modifier.height(Theme.size.searchBarCollapsedHeight),
            contentAlignment = Alignment.CenterStart
        ) {
            IconsRow(state.isVisibleGridIcon, state.barState, actions)

            Crossfade(state.barState, label = "") { barState ->
                if (barState.isCollapsed) {
                    Text(
                        text = text.shared.hintFindNote,
                        style = TextDesign.bodyLarge.copy(color = onSurface),
                        modifier = modifier.padding(start = dp48)
                    )
                }
                if (barState.isSelection) {
                    SelectedItemsCounter(rememberUpdatedState(state.counterValue))
                }
            }
        }
    }
}