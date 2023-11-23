package ru.maksonic.beresta.feature.wallpaper_picker.ui.core.widget.pager.pages

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.GradientDrawable
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import coil.compose.AsyncImage
import coil.imageLoader
import coil.request.CachePolicy
import coil.request.ImageRequest
import ru.maksonic.beresta.common.ui_theme.colors.secondaryContainer
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
                WallpaperImageContent(image, isPicker = true)
            }
        }
    }
}

@Composable
internal fun WallpaperImageContent(
    image: WallpaperImage<Color>,
    isMainCard: Boolean = false,
    modifier: Modifier = Modifier,
    isPicker: Boolean = false,
) {
    val key = rememberUpdatedState("${image.id}-${image.resId}")

    val context = LocalContext.current
    val defColor = secondaryContainer
    val initialPlaceholder = GradientDrawable().apply {
        setSize(1, 1)
        setColor(defColor.toArgb())
    }

    var mutablePlaceholder by remember { mutableStateOf(initialPlaceholder) }

    val builder = ImageRequest.Builder(context)
        .data(image.resId)
        .crossfade(true)
        .memoryCachePolicy(CachePolicy.ENABLED)
        .diskCachePolicy(CachePolicy.ENABLED)
        .memoryCacheKey(key.value)
        .diskCacheKey(key.value)
        .placeholder(mutablePlaceholder)

    if (!isPicker && !isMainCard) {
        LaunchedEffect(image.resId) {
            val fetchedColor = getWallpaperColorFromImage(image.resId, context, defColor)

            if (mutablePlaceholder != initialPlaceholder) {
                mutablePlaceholder = initialPlaceholder.apply {
                    setColor(fetchedColor.toArgb())
                }
            }
        }
    }

     AsyncImage(
        model = builder.build(),
        contentDescription = "",
        contentScale = ContentScale.Crop,
        imageLoader = context.imageLoader,
        modifier = modifier.fillMaxSize()
    )
}

private fun getWallpaperColorFromImage(
    @DrawableRes resId: Int,
    context: Context,
    defColor: Color
): Color {
    val config = Bitmap.Config.ARGB_8888
    val bitmap = ContextCompat.getDrawable(context, resId)?.toBitmap(5, 5, config)

    return bitmap?.getPixel(0, 0)?.let { Color(it) } ?: defColor
}
