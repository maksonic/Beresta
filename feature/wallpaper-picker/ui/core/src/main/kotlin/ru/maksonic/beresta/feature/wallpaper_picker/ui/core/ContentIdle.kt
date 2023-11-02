package ru.maksonic.beresta.feature.wallpaper_picker.ui.core

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.launch
import ru.maksonic.beresta.feature.wallpaper_picker.domain.WallpapersDataContainer
import ru.maksonic.beresta.feature.wallpaper_picker.domain.wallpaper.BaseWallpaper
import ru.maksonic.beresta.feature.wallpaper_picker.ui.core.widget.pager.TabRow
import ru.maksonic.beresta.feature.wallpaper_picker.ui.core.widget.pager.WallpaperPickerPagerPage
import ru.maksonic.beresta.feature.wallpaper_picker.ui.core.widget.pager.pages.WallpaperColorPage
import ru.maksonic.beresta.feature.wallpaper_picker.ui.core.widget.pager.pages.WallpaperGradientPage
import ru.maksonic.beresta.feature.wallpaper_picker.ui.core.widget.pager.pages.WallpaperImagePage
import ru.maksonic.beresta.feature.wallpaper_picker.ui.core.widget.pager.pages.WallpaperTexturePage

/**
 * @Author maksonic on 23.09.2023
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun ContentIdle(
    currentSelected: BaseWallpaper<Color>,
    pagerState: PagerState,
    wallpapers: WallpapersDataContainer<Color>,
    updateWallpaper: (BaseWallpaper<Color>) -> Unit,
    modifier: Modifier
) {
    val scope = rememberCoroutineScope()

    Column {

        TabRow(
            selectedTabIndex = pagerState.currentPage,
            onTabClicked = { scope.launch { pagerState.animateScrollToPage(it) } }
        )

        HorizontalPager(
            state = pagerState,
            modifier = modifier.navigationBarsPadding()
        ) { page ->
            when (page) {
                WallpaperPickerPagerPage.COLORS -> {
                    WallpaperColorPage(
                        colorsData = wallpapers.colors,
                        currentSelectedId = currentSelected.id,
                        onClick = updateWallpaper
                    )
                }

                WallpaperPickerPagerPage.GRADIENTS -> {
                    WallpaperGradientPage(
                        gradientsData = wallpapers.gradients,
                        currentSelectedId = currentSelected.id,
                        onClick = updateWallpaper
                    )
                }

                WallpaperPickerPagerPage.TEXTURES -> {
                    WallpaperTexturePage(
                        texturesData = wallpapers.textures,
                        currentSelectedId = currentSelected.id,
                        onClick = updateWallpaper
                    )
                }

                WallpaperPickerPagerPage.IMAGES -> {
                    WallpaperImagePage(
                        imagesData = wallpapers.images,
                        currentSelectedId = currentSelected.id,
                        onClick = updateWallpaper
                    )
                }
            }
        }
    }
}