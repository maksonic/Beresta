package ru.maksonic.beresta.feature.wallpaper_picker.ui.core.widget.pager.pages

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.imageLoader
import coil.request.CachePolicy
import coil.request.ImageRequest
import kotlinx.coroutines.delay
import ru.maksonic.beresta.common.ui_theme.Theme
import ru.maksonic.beresta.common.ui_theme.colors.outline
import ru.maksonic.beresta.feature.wallpaper_picker.domain.wallpaper.BaseWallpaper
import ru.maksonic.beresta.feature.wallpaper_picker.domain.wallpaper.WallpaperTexture
import ru.maksonic.beresta.feature.wallpaper_picker.ui.api.WallpaperDefault
import ru.maksonic.beresta.feature.wallpaper_picker.ui.core.widget.pager.base.BaseWallpaperItem
import ru.maksonic.beresta.feature.wallpaper_picker.ui.core.widget.pager.base.BaseWallpaperPage

/**
 * @Author maksonic on 30.10.2023
 */
@Composable
fun WallpaperTexturePage(
    texturesData: List<WallpaperTexture<Color>>,
    currentSelectedId: Long,
    onClick: (BaseWallpaper<Color>) -> Unit
) {
    BaseWallpaperPage {
        items(texturesData) { texture ->
            BaseWallpaperItem(
                isSelected = texture.id == currentSelectedId,
                onClick = { texture.onClick(currentSelectedId, onClick, WallpaperDefault) }
            ) {
                WallpaperTextureContent(texture, isPicker = true)
            }
        }
    }
}

@Composable
internal fun WallpaperTextureContent(
    texture: WallpaperTexture<Color>,
    modifier: Modifier = Modifier,
    isPicker: Boolean = false
) {
    val animDelay = Theme.animVelocity.common
    var isAnimated by remember { mutableStateOf(false) }
    val animVelocity = if (isAnimated) animDelay else 0
    val animatedTextureBackground = animateColorAsState(
        if (texture.backgroundColor.id == 0L)
            Color.Transparent
        else
            texture.backgroundColor.value.copy(texture.backgroundColorAlpha),
        tween(animVelocity), label = ""
    )

    LaunchedEffect(Unit) {
        if (!isAnimated) {
            delay(animDelay.toLong())
            isAnimated = true
        }
    }

    Box(modifier.drawBehind { drawRect(animatedTextureBackground.value) }) {
        val context = LocalContext.current
        val animatedTint = animateColorAsState(
            if (texture.tintColor.id == 0L)
                outline.copy(0.2f)
            else
                texture.tintColor.value.copy(texture.tintColorAlpha),
            tween(animVelocity), label = ""
        )

        val tint = if (isPicker) rememberUpdatedState(outline) else animatedTint
        val key = rememberUpdatedState("${texture.id}-${texture.resId}")
        val builder = ImageRequest.Builder(context)
            .data(texture.resId)
            .crossfade(true)
            .memoryCachePolicy(CachePolicy.ENABLED)
            .diskCachePolicy(CachePolicy.ENABLED)
            .memoryCacheKey(key.value)
            .diskCacheKey(key.value)

        AsyncImage(
            model = builder.build(),
            imageLoader = context.imageLoader,
            contentDescription = "",
            contentScale = ContentScale.Crop,
            colorFilter = ColorFilter.tint(tint.value),
            filterQuality = if (isPicker) FilterQuality.Low else FilterQuality.None,
            modifier = modifier.fillMaxSize()
        )
    }
}

