package ru.maksonic.beresta.feature.search_bar.ui.widget

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.movableContentOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ru.maksonic.beresta.feature.search_bar.api.SearchBarApi
import ru.maksonic.beresta.feature.search_bar.api.SearchBarState
import ru.maksonic.beresta.feature.search_bar.api.isCollapsed
import ru.maksonic.beresta.feature.search_bar.api.isExpanded
import ru.maksonic.beresta.feature.search_bar.api.isSelection
import ru.maksonic.beresta.feature.sorting_sheet.api.listUiSortState
import ru.maksonic.beresta.ui.theme.color.onBackground
import ru.maksonic.beresta.ui.theme.color.onSurface
import ru.maksonic.beresta.ui.theme.component.dp12
import ru.maksonic.beresta.ui.theme.component.dp4
import ru.maksonic.beresta.ui.theme.component.dp48
import ru.maksonic.beresta.ui.theme.icons.AppIcon
import ru.maksonic.beresta.ui.theme.icons.ArrowBack
import ru.maksonic.beresta.ui.theme.icons.Close
import ru.maksonic.beresta.ui.theme.icons.GridView
import ru.maksonic.beresta.ui.theme.icons.ListView
import ru.maksonic.beresta.ui.theme.icons.Search
import ru.maksonic.beresta.ui.theme.icons.SelectAll
import ru.maksonic.beresta.ui.theme.icons.ShareIos
import ru.maksonic.beresta.ui.widget.button.ClickableIcon
import ru.maksonic.beresta.ui.widget.functional.animation.AnimateFadeInOut

/**
 * @Author maksonic on 25.08.2023
 */
@Composable
internal fun IconsRow(
    isHiddenNotes: Boolean,
    searchBarState: SearchBarState,
    actions: Map<SearchBarApi.ActionKey, () -> Unit>,
    modifier: Modifier = Modifier
) {

    Row(
        modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically
    ) {
        val selectedIcons = movableContentOf {
            Row {
                Spacer(modifier.weight(1f))

                ClickableIcon(
                    icon = AppIcon.ShareIos,
                    tint = onBackground,
                    action = {}
                )
                ClickableIcon(
                    icon = AppIcon.SelectAll,
                    tint = onBackground,
                    action = { actions[SearchBarApi.ActionKey.OnSelectAllClicked]?.invoke() },
                    modifier = modifier.padding(end = dp4)
                )
            }
        }

        Crossfade(targetState = searchBarState, label = "") { current ->
            when {
                current.isCollapsed -> {
                    Icon(
                        imageVector = AppIcon.Search,
                        tint = onSurface,
                        contentDescription = "",
                        modifier = modifier
                            .size(dp48)
                            .padding(dp12)
                    )
                }

                current.isExpanded -> {
                    ClickableIcon(
                        icon = AppIcon.ArrowBack,
                        tint = onBackground,
                        action = { actions[SearchBarApi.ActionKey.OnCollapseBar]?.invoke() },
                        modifier = modifier.padding(end = dp4)
                    )
                }

                current.isSelection -> {
                    ClickableIcon(
                        icon = AppIcon.Close,
                        tint = onBackground,
                        action = { actions[SearchBarApi.ActionKey.OnCancelClicked]?.invoke() },
                        modifier = modifier.padding(end = dp4)
                    )
                }
            }
        }


        if (isHiddenNotes) {
            Crossfade(searchBarState.isSelection, label = "") { isSelection ->
                if (isSelection) {
                    selectedIcons()
                } else {
                    AnimateFadeInOut(searchBarState.isCollapsed) {
                        HiddenNotesGridCountButton {
                            actions[SearchBarApi.ActionKey.OnChangeGridClicked]?.invoke()
                        }
                    }
                }
            }
        } else {
            AnimateFadeInOut(searchBarState.isSelection) {
                selectedIcons()
            }
        }
    }
}

@Composable
private fun HiddenNotesGridCountButton(
    modifier: Modifier = Modifier,
    onChangeGridClicked: () -> Unit
) {
    val afterSearchBarIcon = remember { mutableStateOf(AppIcon.ListView) }
    val gridCount = rememberUpdatedState(listUiSortState.gridHiddenNotesCount)

    LaunchedEffect(listUiSortState.gridHiddenNotesCount) {
        afterSearchBarIcon.value = if (gridCount.value == 1) AppIcon.ListView else AppIcon.GridView
    }

    Box(
        modifier
            .fillMaxWidth(), contentAlignment = Alignment.CenterEnd
    ) {
        ClickableIcon(
            icon = afterSearchBarIcon.value,
            tint = onSurface,
            action = onChangeGridClicked
        )
    }
}