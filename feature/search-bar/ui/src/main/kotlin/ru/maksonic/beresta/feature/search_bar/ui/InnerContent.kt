package ru.maksonic.beresta.feature.search_bar.ui

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ru.maksonic.beresta.feature.search_bar.api.SearchBarApi
import ru.maksonic.beresta.feature.search_bar.api.SearchBarUiState
import ru.maksonic.beresta.feature.search_bar.api.isCollapsed
import ru.maksonic.beresta.feature.search_bar.api.isSelection
import ru.maksonic.beresta.feature.search_bar.ui.widget.IconsRow
import ru.maksonic.beresta.feature.search_bar.ui.widget.SelectedItemsCounter
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.onSurface
import ru.maksonic.beresta.ui.theme.component.TextDesign
import ru.maksonic.beresta.ui.theme.component.dp4
import ru.maksonic.beresta.ui.theme.component.dp48
import ru.maksonic.beresta.ui.theme.component.dp56
import ru.maksonic.beresta.ui.theme.component.dp8
import ru.maksonic.beresta.ui.widget.functional.animation.animateDp

/**
 * @Author maksonic on 25.08.2023
 */
@Composable
internal fun InnerContent(
    uiState: State<SearchBarUiState>,
    actions: Map<SearchBarApi.ActionKey, () -> Unit>,
    modifier: Modifier = Modifier,
) {
    val edge = animateDp(state = if (uiState.value.barState.isCollapsed) dp56 else dp4)

    Box(
        modifier
            .statusBarsPadding()
            .fillMaxWidth()
            .padding(top = dp8)
            .height(Theme.widgetSize.searchBarCollapsedHeight)
            .padding(start = edge.value, end = edge.value),
    ) {
        Box(
            modifier.height(Theme.widgetSize.searchBarCollapsedHeight),
            contentAlignment = Alignment.CenterStart
        ) {
            IconsRow(uiState.value.isVisibleGridIcon, uiState.value.barState, actions)

            Crossfade(uiState.value.barState, label = "") { state ->
                if (state.isCollapsed) {
                    Text(
                        text = text.shared.hintFindNote,
                        style = TextDesign.bodyPrimary.copy(color = onSurface),
                        modifier = modifier.padding(start = dp48)
                    )
                }
                if (state.isSelection) {
                    SelectedItemsCounter(rememberUpdatedState(uiState.value.counterValue))
                }
            }
        }
    }
}