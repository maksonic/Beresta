package ru.maksonic.beresta.feature.note_wallpaper_selector.core.presentation.widget

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import ru.maksonic.beresta.feature.language_selector.api.provider.text
import ru.maksonic.beresta.feature.note_wallpaper_selector.core.presentation.core.Msg
import ru.maksonic.beresta.feature.note_wallpaper_selector.core.data.NoteWallpaper
import ru.maksonic.beresta.feature.note_wallpaper_selector.core.data.WallpaperCategory
import ru.maksonic.beresta.feature.note_wallpaper_selector.core.presentation.SendMessage
import ru.maksonic.beresta.ui.widget.functional.animation.OverscrollBehavior

/**
 * @Author maksonic on 10.03.2023
 */
@OptIn(ExperimentalPagerApi::class)
@Composable
fun WallpaperPager(
    send: SendMessage,
    selectedNoteWallpaper: NoteWallpaper,
    tabData: WallpaperCategory.Collection,
    pagerState: () -> PagerState,
    modifier: Modifier
) {
    val wallpapersCategoryTitle = arrayOf(
        text.editNote.noteWallpaperCategoryNew,
        text.editNote.noteWallpaperCategorySoftColors,
        text.editNote.noteWallpaperCategoryBrightColors,
        text.editNote.noteWallpaperCategoryGradients,
        text.editNote.noteWallpaperCategoryGrids,
        text.editNote.noteWallpaperCategoryBest,
    )

    LaunchedEffect(Unit) {
        send(Msg.Inner.FillWallpaperCategoryTitle(wallpapersCategoryTitle))
    }

    OverscrollBehavior {
        HorizontalPager(
            state = pagerState(),
            count = tabData.data.count(),
            modifier = modifier
        ) { index ->
            tabData.data.forEachIndexed { categoryIndex, category ->
                if (categoryIndex == index) {
                    Page(
                        send = send,
                        wallpapers = category.wallpapers,
                        selectedNoteWallpaper = selectedNoteWallpaper
                    )
                }
            }
        }
    }
}