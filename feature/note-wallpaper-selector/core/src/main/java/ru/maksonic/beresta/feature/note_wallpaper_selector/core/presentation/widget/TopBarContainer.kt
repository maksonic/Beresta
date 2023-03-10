package ru.maksonic.beresta.feature.note_wallpaper_selector.core.presentation.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import ru.maksonic.beresta.feature.language_selector.api.provider.text
import ru.maksonic.beresta.feature.note_wallpaper_selector.core.data.WallpaperCategory
import ru.maksonic.beresta.ui.theme.color.tertiaryContainer
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.widget.bar.TopAppBarNormal

/**
 * @Author maksonic on 14.03.2023
 */
@OptIn(ExperimentalPagerApi::class)
@Composable
internal fun TopBarContainer(
    wallpapers: WallpaperCategory.Collection,
    pagerState: () -> PagerState,
    hideSheet: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier
            .background(tertiaryContainer)
            .padding(bottom = dp16)
    ) {

        TopAppBarNormal(
            title = text.editNote.topBarTitleSelectNoteWallpaper,
            backAction = { hideSheet() },
        )
        TabLayout(wallpapers, pagerState)
    }
}