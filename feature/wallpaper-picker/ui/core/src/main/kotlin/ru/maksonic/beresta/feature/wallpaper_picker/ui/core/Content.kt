package ru.maksonic.beresta.feature.wallpaper_picker.ui.core

import android.content.res.Configuration
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import kotlinx.coroutines.launch
import ru.maksonic.beresta.common.ui_kit.animation.AnimateContent
import ru.maksonic.beresta.common.ui_kit.helpers.modifier.noRippleClick
import ru.maksonic.beresta.common.ui_theme.colors.secondaryContainer
import ru.maksonic.beresta.common.ui_theme.provide.dp24
import ru.maksonic.beresta.feature.wallpaper_picker.domain.wallpaper.BaseWallpaper
import ru.maksonic.beresta.feature.wallpaper_picker.domain.wallpaper.WallpaperTexture
import ru.maksonic.beresta.feature.wallpaper_picker.ui.core.widget.TopBar
import ru.maksonic.beresta.feature.wallpaper_picker.ui.core.widget.pager.WallpaperPickerPagerPage

/**
 * @Author maksonic on 30.10.2023
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun Content(
    wallpapersUiState: WallpapersUiState,
    currentSelected: BaseWallpaper<Color>,
    onCloseClicked: () -> Unit,
    updateWallpaper: (BaseWallpaper<Color>) -> Unit,
    modifier: Modifier = Modifier,
) {
    val scope = rememberCoroutineScope()
    val launchCounter = rememberSaveable { mutableIntStateOf(0) }
    val sheetHeightFraction = with(LocalConfiguration.current) {
        if (this.orientation == Configuration.ORIENTATION_LANDSCAPE) 0.9f else 0.55f
    }
    val currentContent = rememberSaveable { mutableStateOf(WallpaperPickerContent.IDLE) }
    var isVisibleAlphaSlider by rememberSaveable { mutableStateOf(false) }
    var textureAlphaKey by rememberSaveable { mutableStateOf(TextureAlphaKey.NOTHING) }
    val pagerState = rememberPagerState(
        initialPage = WallpaperPickerPagerPage.COLORS,
        initialPageOffsetFraction = 0f,
        pageCount = { WallpaperPickerPagerPage.COUNT }
    )

    BackHandler(currentContent.value != WallpaperPickerContent.IDLE) {
        currentContent.value = WallpaperPickerContent.IDLE
    }

    LaunchedEffect(Unit) {
        launchCounter.intValue = launchCounter.intValue + 1
        if (launchCounter.intValue <= 1) {
            scope.launch {
                val page = WallpaperPickerPagerPage.getPageByWallpaper(currentSelected)
                pagerState.animateScrollToPage(page)
            }
        }
    }

    Column(
        modifier
            .fillMaxWidth()
            .fillMaxHeight(sheetHeightFraction)
            .clip(RoundedCornerShape(topStart = dp24, topEnd = dp24))
            .background(secondaryContainer)
            .noRippleClick { },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TopBar(
            currentContent = currentContent,
            isTexturePage = pagerState.currentPage == WallpaperPickerPagerPage.TEXTURES,
            textureAlphaKey = textureAlphaKey,
            onCloseClicked = onCloseClicked,
            onSetTextureBackgroundClicked = {
                currentContent.value = WallpaperPickerContent.TEXTURE_SETTINGS
            },
            onInitialContentClicked = { currentContent.value = WallpaperPickerContent.IDLE },
            onCloseAlphaPickerDialog = {
                isVisibleAlphaSlider = false
                textureAlphaKey = TextureAlphaKey.NOTHING
            }
        )

        AnimateContent(currentContent.value) {
            when (it) {
                WallpaperPickerContent.IDLE -> {
                    ContentIdle(
                        currentSelected = currentSelected,
                        pagerState = pagerState,
                        wallpapers = wallpapersUiState.wallpapers,
                        updateWallpaper = updateWallpaper,
                        modifier = modifier
                    )
                }

                WallpaperPickerContent.TEXTURE_SETTINGS -> {
                    val texture = currentSelected as? WallpaperTexture

                    ContentTextureColorPicker(
                        state = wallpapersUiState,
                        texture = texture,
                        isVisibleAlphaSlider = isVisibleAlphaSlider,
                        textureAlphaKey = textureAlphaKey,
                        updateWallpaper = updateWallpaper,
                        updateAlphaPickerVisibility = { visible -> isVisibleAlphaSlider = visible },
                        updateAlphaPickerKey = { key -> textureAlphaKey = key },
                        onBack = { currentContent.value = WallpaperPickerContent.IDLE },
                        onAcceptClicked = onCloseClicked
                    )
                }
            }
        }
    }
}