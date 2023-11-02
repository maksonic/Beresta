package ru.maksonic.beresta.feature.wallpaper_picker.ui.core.widget.pager.pages

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import ru.maksonic.beresta.feature.wallpaper_picker.domain.wallpaper.BaseWallpaper
import ru.maksonic.beresta.feature.wallpaper_picker.domain.wallpaper.WallpaperImage
import ru.maksonic.beresta.feature.wallpaper_picker.ui.api.WallpaperDefault
import ru.maksonic.beresta.feature.wallpaper_picker.ui.core.widget.pager.base.BaseWallpaperItem
import ru.maksonic.beresta.feature.wallpaper_picker.ui.core.widget.pager.base.BaseWallpaperPage

/**
 * @Author maksonic on 30.10.2023
 */
@Composable
fun WallpaperImagePage(
    imagesData: List<WallpaperImage<Color>>,
    currentSelectedId: Long,
    onClick: (BaseWallpaper<Color>) -> Unit
) {
    BaseWallpaperPage {
        items(imagesData) { image ->
            BaseWallpaperItem(
                isSelected = image.id == currentSelectedId,
                onClick = { image.onClick(currentSelectedId, onClick, WallpaperDefault) }
            ) {
                WallpaperImageContent(image)
            }
        }
    }
}

@Composable
internal fun WallpaperImageContent(image: WallpaperImage<Color>, modifier: Modifier = Modifier) {
    AsyncImage(
        model = image.resId,
        contentDescription = "",
        contentScale = ContentScale.Crop,
        modifier = modifier.fillMaxSize()
    )
}

