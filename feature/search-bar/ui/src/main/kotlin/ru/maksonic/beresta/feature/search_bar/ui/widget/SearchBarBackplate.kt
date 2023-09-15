package ru.maksonic.beresta.feature.search_bar.ui.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ru.maksonic.beresta.feature.search_bar.api.SearchBackplateState
import ru.maksonic.beresta.feature.search_bar.api.SearchBarApi
import ru.maksonic.beresta.feature.search_bar.api.SearchBarUiState
import ru.maksonic.beresta.feature.sorting_sheet.api.listUiSortState
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.onBackground
import ru.maksonic.beresta.ui.theme.component.dp4
import ru.maksonic.beresta.ui.theme.icons.AppIcon
import ru.maksonic.beresta.ui.theme.icons.ArrowBack
import ru.maksonic.beresta.ui.theme.icons.GridView
import ru.maksonic.beresta.ui.theme.icons.ListView
import ru.maksonic.beresta.ui.theme.icons.SortBy
import ru.maksonic.beresta.ui.widget.button.ClickableIcon
import ru.maksonic.beresta.ui.widget.functional.animation.animateDp
import ru.maksonic.beresta.ui.widget.surface.SurfacePro

/**
 * @Author maksonic on 26.04.2023
 */
@Composable
internal fun SearchBarBackplate(
    modifier: Modifier = Modifier,
    state: State<SearchBarUiState>,
    isColoredBackplate: State<Boolean>,
    actions: Map<SearchBarApi.ActionKey, () -> Unit>
) {
    val tonal = animateDp(if (isColoredBackplate.value) Theme.tonal.Level2 else Theme.tonal.Level0)

    SurfacePro(
        tonalElevation = tonal.value,
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier
                .statusBarsPadding()
                .fillMaxWidth()
                .height(Theme.widgetSize.topBarSmallHeight)
                .padding(start = dp4, end = dp4),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            when (state.value.backplateState) {
                SearchBackplateState.MainNotes -> {
                    MainStateComponent(
                        onUserAvatarClicked = {
                            actions[SearchBarApi.ActionKey.OnUserAvatarClicked]?.invoke()
                        },
                        onChangeGridClicked = {
                            actions[SearchBarApi.ActionKey.OnChangeGridClicked]?.invoke()
                        }
                    )
                }

                SearchBackplateState.HiddenNotes -> {
                    HiddenNotesStateComponent(
                        onBackClicked = {
                            actions[SearchBarApi.ActionKey.OnBackClicked]?.invoke()
                        },
                        onSortByClicked = {
                            actions[SearchBarApi.ActionKey.OnSortByClicked]?.invoke()
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun MainStateComponent(onUserAvatarClicked: () -> Unit, onChangeGridClicked: () -> Unit) {
    val afterSearchBarIcon = remember { mutableStateOf(AppIcon.ListView) }
    val gridCount = rememberUpdatedState(listUiSortState.gridNotesCount)

    LaunchedEffect(listUiSortState.gridNotesCount) {
        afterSearchBarIcon.value = if (gridCount.value == 1) AppIcon.ListView else AppIcon.GridView
    }

    UserClickableAvatar(onUserAvatarClicked)

    ClickableIcon(icon = afterSearchBarIcon.value, tint = onBackground, onClick = onChangeGridClicked)
}

@Composable
private fun HiddenNotesStateComponent(onBackClicked: () -> Unit, onSortByClicked: () -> Unit) {
    ClickableIcon(icon = AppIcon.ArrowBack, tint = onBackground, onClick = onBackClicked)
    ClickableIcon(icon = AppIcon.SortBy, tint = onBackground, onClick = onSortByClicked)
}