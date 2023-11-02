package ru.maksonic.beresta.feature.ui.search_bar.core.widget

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
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
import ru.maksonic.beresta.common.ui_kit.button.ButtonIcon
import ru.maksonic.beresta.common.ui_kit.icons.AppIcon
import ru.maksonic.beresta.common.ui_kit.icons.ArrowBack
import ru.maksonic.beresta.common.ui_kit.icons.GridView
import ru.maksonic.beresta.common.ui_kit.icons.ListView
import ru.maksonic.beresta.common.ui_kit.icons.SortBy
import ru.maksonic.beresta.common.ui_kit.surface.SurfacePro
import ru.maksonic.beresta.common.ui_theme.Theme
import ru.maksonic.beresta.common.ui_theme.colors.onBackground
import ru.maksonic.beresta.common.ui_theme.provide.dp4
import ru.maksonic.beresta.feature.search_bar.api.SearchBackplateState
import ru.maksonic.beresta.feature.sorting_sheet.ui.api.listNotesSortState
import ru.maksonic.beresta.feature.ui.search_bar.api.SearchBarUiApi
import ru.maksonic.beresta.feature.ui.search_bar.api.SearchBarUiState

/**
 * @Author maksonic on 26.04.2023
 */
@Composable
internal fun SearchBarBackplate(
    modifier: Modifier = Modifier,
    state: SearchBarUiState,
    isColoredBackplate: State<Boolean>,
    actions: Map<SearchBarUiApi.ActionKey, () -> Unit>
) {
    val tonal = animateDpAsState(
        if (isColoredBackplate.value) Theme.tonal.level2 else Theme.tonal.level0,
        tween(Theme.animVelocity.common), label = ""
    )

    SurfacePro(
        tonalElevation = tonal.value,
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier
                .statusBarsPadding()
                .fillMaxWidth()
                .height(Theme.size.topBarSmallHeight)
                .padding(start = dp4, end = dp4),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            when (state.backplateState) {
                SearchBackplateState.MainNotes -> {
                    MainScreenSearchBarBackPlate(
                        onUserAvatarClicked = {
                            actions[SearchBarUiApi.ActionKey.OnUserAvatarClicked]?.invoke()
                        },
                        onChangeGridClicked = {
                            actions[SearchBarUiApi.ActionKey.OnChangeGridClicked]?.invoke()
                        }
                    )
                }

                SearchBackplateState.HiddenNotes -> {
                    HiddenNotesSearchBarBackPlate(
                        onBackClicked = { actions[SearchBarUiApi.ActionKey.OnBackClicked]?.invoke() },
                        onSortClicked = { actions[SearchBarUiApi.ActionKey.OnSortByClicked]?.invoke() }
                    )
                }
            }
        }
    }
}

@Composable
private fun MainScreenSearchBarBackPlate(
    onUserAvatarClicked: () -> Unit,
    onChangeGridClicked: () -> Unit
) {
    val gridCount = rememberUpdatedState(listNotesSortState.gridCount)
    val afterSearchBarIcon = remember { mutableStateOf(AppIcon.ListView) }

    LaunchedEffect(listNotesSortState.gridCount) {
        afterSearchBarIcon.value =
            if (gridCount.value == 1) AppIcon.ListView else AppIcon.GridView
    }

    UserClickableAvatar(onUserAvatarClicked)

    ButtonIcon(
        icon = afterSearchBarIcon.value,
        tint = onBackground,
        onClick = onChangeGridClicked
    )
}

@Composable
private fun HiddenNotesSearchBarBackPlate(onBackClicked: () -> Unit, onSortClicked: () -> Unit) {
    ButtonIcon(icon = AppIcon.ArrowBack, tint = onBackground, onClick = onBackClicked)
    ButtonIcon(icon = AppIcon.SortBy, tint = onBackground, onClick = onSortClicked)
}