package ru.maksonic.beresta.feature.ui.search_bar.core.widget

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import org.koin.compose.koinInject
import ru.maksonic.beresta.common.ui_kit.bar.system.SystemNavigationBarHeight
import ru.maksonic.beresta.common.ui_theme.Theme
import ru.maksonic.beresta.common.ui_theme.provide.dp10
import ru.maksonic.beresta.common.ui_theme.provide.dp16
import ru.maksonic.beresta.feature.notes_list.ui.api.NoteUi
import ru.maksonic.beresta.feature.notes_list.ui.api.card.NotesCardUiApi
import ru.maksonic.beresta.feature.wallpaper_picker.ui.api.WallpaperPickerUiApi

/**
 * @Author maksonic on 02.10.2023
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun SearchListResult(
    notes: NoteUi.Collection,
    isEmptySearch: Boolean,
    onNoteClicked: (Long) -> Unit,
    updateStatusBarColor: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    noteCardApi: NotesCardUiApi = koinInject(),
    wallpaperUiApi: WallpaperPickerUiApi.Wallpaper = koinInject()
) {
    val scrollState = rememberLazyStaggeredGridState()

    LaunchedEffect(scrollState.canScrollBackward) {
        updateStatusBarColor(scrollState.canScrollBackward)
    }

    Box {
        LazyVerticalStaggeredGrid(
            state = scrollState,
            columns = StaggeredGridCells.Fixed(1),
            contentPadding = PaddingValues(
                top = dp16,
                bottom = SystemNavigationBarHeight.plus(dp16),
                start = dp10,
                end = dp10
            ),
        ) {
            items(items = notes.data, key = { note -> note.id }) { note ->
                noteCardApi.Card(
                    note = note,
                    onNoteClicked = onNoteClicked,
                    onNoteLongClicked = onNoteClicked,
                    modifier = Modifier.animateItemPlacement(tween(Theme.animVelocity.common)),
                    cardBackground = { wallpaperUiApi.Widget(it, Modifier.matchParentSize()) }
                )
            }
        }

        if (isEmptySearch) {
            EmptySearchResult(modifier.imePadding())
        }
    }
}