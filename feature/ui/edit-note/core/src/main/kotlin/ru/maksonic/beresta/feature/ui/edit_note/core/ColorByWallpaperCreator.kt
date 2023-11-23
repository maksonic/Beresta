package ru.maksonic.beresta.feature.ui.edit_note.core

import android.content.Context
import android.graphics.Bitmap
import androidx.annotation.DrawableRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import ru.maksonic.beresta.common.ui_theme.Theme
import ru.maksonic.beresta.common.ui_theme.colors.primary
import ru.maksonic.beresta.common.ui_theme.colors.secondaryContainer
import ru.maksonic.beresta.common.ui_theme.colors.surface
import ru.maksonic.beresta.common.ui_theme.colors.surfaceVariant
import ru.maksonic.beresta.common.ui_theme.colors.tertiaryContainer
import ru.maksonic.beresta.feature.wallpaper_picker.domain.WallpaperType
import ru.maksonic.beresta.feature.wallpaper_picker.domain.wallpaper.BaseWallpaper
import ru.maksonic.beresta.feature.wallpaper_picker.domain.wallpaper.WallpaperColor
import ru.maksonic.beresta.feature.wallpaper_picker.domain.wallpaper.WallpaperGradient
import ru.maksonic.beresta.feature.wallpaper_picker.domain.wallpaper.WallpaperImage
import ru.maksonic.beresta.feature.wallpaper_picker.domain.wallpaper.WallpaperTexture


/**
 * @Author maksonic on 19.11.2023
 */
private const val DEF_WALLPAPER_COLOR_ID = 100000L

@Composable
internal fun rememberColorCreator(
    wallpaper: BaseWallpaper<Color>,
    canNoteScrollBackward: State<Boolean>,
) = rememberUpdatedState(ColorByWallpaperCreator(wallpaper, canNoteScrollBackward))

class ColorByWallpaperCreator(
    private val wallpaper: BaseWallpaper<Color>,
    private val canNoteScrollBackward: State<Boolean>,
) {
    private fun getWallpaperColorFromImage(
        @DrawableRes resId: Int,
        context: Context,
        defColor: Color
    ): Color {
        val config = Bitmap.Config.ARGB_8888
        val bitmap = ContextCompat.getDrawable(context, resId)?.toBitmap(5, 5, config)

        return bitmap?.getPixel(0, 0)?.let { Color(it) } ?: defColor
    }

    private val fetchedColor
        @Composable get() = with(wallpaper) {
            val defColor = surface

            when (this) {
                is WallpaperColor -> if (id == DEF_WALLPAPER_COLOR_ID) defColor else value
                is WallpaperGradient -> value.first()
                is WallpaperTexture -> {
                    with(backgroundColor) {
                        val colorByAlpha = value.copy(backgroundColorAlpha).compositeOver(defColor)
                        if (id == 0L) defColor else colorByAlpha
                    }
                }

                is WallpaperImage -> {
                    val context = LocalContext.current
                    var mutableColor by remember { mutableStateOf(Color.Transparent) }

                    LaunchedEffect(id) {
                        val color = getWallpaperColorFromImage(resId, context, defColor)
                        if (mutableColor != color) {
                            mutableColor = color
                        }
                    }
                    mutableColor
                }

                else -> surface
            }
        }

    private val isInitialColor @Composable get() = fetchedColor == surface

    @Composable
    fun topBarColor(animVelocity: Int): State<Color> {
        val colorResult = if (canNoteScrollBackward.value)
            if (isInitialColor) {
                surfaceVariant.copy(0.5f).compositeOver(fetchedColor)
            } else {
                Color.Black.copy(0.1f).compositeOver(fetchedColor)
            }
        else fetchedColor

        return if (wallpaper.getType() == WallpaperType.Value.IMAGE) {
            rememberUpdatedState(colorResult)
        } else {
            animateColorAsState(colorResult, tween(animVelocity), label = "")
        }
    }

    @Composable
    fun bottomBarColor(): State<Color> {
        val color =
            if (wallpaper is WallpaperGradient<Color>) wallpaper.value.last() else fetchedColor

        return rememberUpdatedState(
            if (isInitialColor) {
                surfaceVariant.copy(0.5f).compositeOver(color)
            } else {
                Color.Black.copy(0.1f).compositeOver(color)
            }
        )
    }

    @Composable
    fun controlBarColor(animVelocity: Int): State<Color> {
        val colorResult = if (isInitialColor) surface else fetchedColor

        return if (wallpaper.getType() == WallpaperType.Value.IMAGE) {
            rememberUpdatedState(colorResult)
        } else {
            animateColorAsState(colorResult, tween(animVelocity), label = "")
        }
    }

    @Composable
    fun tagChipBarColor(animVelocity: Int): State<Color> {
        val colorResult = if (isInitialColor) secondaryContainer else {
            if (wallpaper.isDark) {
                fetchedColor.copy(0.75f).compositeOver(Color.White)
            } else {
                fetchedColor.copy(0.8f).compositeOver(primary)
            }
        }

        return if (wallpaper.getType() == WallpaperType.Value.IMAGE) {
            rememberUpdatedState(colorResult)
        } else {
            animateColorAsState(colorResult, tween(animVelocity), label = "")
        }
    }

    @Composable
    fun fabSaveNoteColor(isBlankNote: Boolean) = if (isInitialColor) {
        animateColorAsState(
            if (isBlankNote) {
                tertiaryContainer.copy(alpha = 0.4f).compositeOver(surface)
            } else {
                tertiaryContainer
            }, tween(Theme.animVelocity.common), label = ""
        )
    } else {
        animateColorAsState(
            if (isBlankNote) {
                Color.Black.copy(0.3f).compositeOver(fetchedColor)
            } else {
                Color.Black.copy(0.65f).compositeOver(fetchedColor)
            }, tween(Theme.animVelocity.common), ""
        )
    }
}