package ru.maksonic.beresta.feature.note_wallpaper_selector.core.presentation.widget

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import ru.maksonic.beresta.feature.language_selector.api.provider.text
import ru.maksonic.beresta.feature.note_wallpaper_selector.core.data.WallpaperCategory
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.widget.SurfacePro
import ru.maksonic.beresta.ui.widget.bar.TopAppBarNormal

/**
 * @Author maksonic on 14.03.2023
 */
@OptIn(ExperimentalPagerApi::class, ExperimentalMaterial3Api::class)
@Composable
internal fun TopBarContainer(
    scrollBehavior: TopAppBarScrollBehavior,
    wallpapers: WallpaperCategory.Collection,
    pagerState: () -> PagerState,
    hideSheet: () -> Unit,
    modifier: Modifier = Modifier
) {
    val tonalElevation = Theme.tonal.Level2
    SurfacePro(tonalElevation = tonalElevation) { color ->

        Column(modifier.padding(bottom = dp16)) {

            TopAppBarNormal(
                scrollBehavior = scrollBehavior,
                title = text.editNote.topBarTitleSelectNoteWallpaper,
                tonal = Theme.tonal.Level0,
                onBackAction = { hideSheet() },
            )
            TabLayout(wallpapers, pagerState, layoutBackground = color)
        }
    }
}