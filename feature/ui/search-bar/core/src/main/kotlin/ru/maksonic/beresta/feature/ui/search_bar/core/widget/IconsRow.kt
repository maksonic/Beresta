package ru.maksonic.beresta.feature.ui.search_bar.core.widget

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
import ru.maksonic.beresta.common.ui_kit.animation.AnimateFadeInOut
import ru.maksonic.beresta.common.ui_kit.button.ButtonIcon
import ru.maksonic.beresta.common.ui_kit.icons.AppIcon
import ru.maksonic.beresta.common.ui_kit.icons.ArrowBack
import ru.maksonic.beresta.common.ui_kit.icons.Close
import ru.maksonic.beresta.common.ui_kit.icons.GridView
import ru.maksonic.beresta.common.ui_kit.icons.ListView
import ru.maksonic.beresta.common.ui_kit.icons.Search
import ru.maksonic.beresta.common.ui_kit.icons.SelectAll
import ru.maksonic.beresta.common.ui_kit.icons.ShareIos
import ru.maksonic.beresta.common.ui_theme.colors.onBackground
import ru.maksonic.beresta.common.ui_theme.colors.onSurface
import ru.maksonic.beresta.common.ui_theme.provide.dp12
import ru.maksonic.beresta.common.ui_theme.provide.dp4
import ru.maksonic.beresta.common.ui_theme.provide.dp48
import ru.maksonic.beresta.feature.sorting_sheet.ui.api.listHiddenNotesSortState
import ru.maksonic.beresta.feature.ui.search_bar.api.SearchBarState
import ru.maksonic.beresta.feature.ui.search_bar.api.SearchBarUiApi
import ru.maksonic.beresta.feature.ui.search_bar.api.isCollapsed
import ru.maksonic.beresta.feature.ui.search_bar.api.isExpanded
import ru.maksonic.beresta.feature.ui.search_bar.api.isSelection

/**
 * @Author maksonic on 25.08.2023
 */
@Composable
internal fun IconsRow(
    isHiddenNotes: Boolean,
    searchBarState: SearchBarState,
    actions: Map<SearchBarUiApi.ActionKey, () -> Unit>,
    modifier: Modifier = Modifier
) {

    Row(
        modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically
    ) {
        val selectedIcons = movableContentOf {
            Row {
                Spacer(modifier.weight(1f))

                ButtonIcon(
                    icon = AppIcon.ShareIos,
                    tint = onBackground,
                    onClick = {}
                )
                ButtonIcon(
                    icon = AppIcon.SelectAll,
                    tint = onBackground,
                    onClick = { actions[SearchBarUiApi.ActionKey.OnSelectAllClicked]?.invoke() },
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
                    ButtonIcon(
                        icon = AppIcon.ArrowBack,
                        tint = onBackground,
                        onClick = { actions[SearchBarUiApi.ActionKey.OnCollapseBar]?.invoke() },
                        modifier = modifier.padding(end = dp4)
                    )
                }

                current.isSelection -> {
                    ButtonIcon(
                        icon = AppIcon.Close,
                        tint = onBackground,
                        onClick = { actions[SearchBarUiApi.ActionKey.OnCancelClicked]?.invoke() },
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
                            actions[SearchBarUiApi.ActionKey.OnChangeGridClicked]?.invoke()
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
    val gridCount = rememberUpdatedState(listHiddenNotesSortState.gridCount)

    LaunchedEffect(listHiddenNotesSortState.gridCount) {
        afterSearchBarIcon.value = if (gridCount.value == 1) AppIcon.ListView else AppIcon.GridView
    }

    Box(
        modifier
            .fillMaxWidth(), contentAlignment = Alignment.CenterEnd
    ) {
        ButtonIcon(
            icon = afterSearchBarIcon.value,
            tint = onSurface,
            onClick = onChangeGridClicked
        )
    }
}
