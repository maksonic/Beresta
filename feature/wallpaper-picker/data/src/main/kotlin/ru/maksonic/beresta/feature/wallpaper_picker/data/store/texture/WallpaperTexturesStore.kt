package ru.maksonic.beresta.feature.wallpaper_picker.data.store.texture

import androidx.compose.ui.graphics.Color
import ru.maksonic.beresta.feature.wallpaper_picker.data.R
import ru.maksonic.beresta.feature.wallpaper_picker.data.helpers.findById
import ru.maksonic.beresta.feature.wallpaper_picker.data.helpers.findByParams
import ru.maksonic.beresta.feature.wallpaper_picker.data.helpers.textureDefault
import ru.maksonic.beresta.feature.wallpaper_picker.data.store.BaseWallpaperStore
import ru.maksonic.beresta.feature.wallpaper_picker.domain.WallpaperParams
import ru.maksonic.beresta.feature.wallpaper_picker.domain.wallpaper.WallpaperTexture

/**
 * @Author maksonic on 23.09.2023
 */
class WallpaperTexturesStore(
    private val textureStyleStore: TextureStyleStore,
    private val tintColorsStore: TextureTintColorsStore,
    private val backgroundColorsStore: TextureBackgroundColorsStore
) : BaseWallpaperStore<WallpaperTexture<Color>> {

    private val _textures = listOf(
        textureDefault.copy(id = 30000L, resId = R.drawable.cell_suared_small),
        textureDefault.copy(id = 30001L, resId = R.drawable.cell_suared_medium),
        textureDefault.copy(id = 30002L, resId = R.drawable.line_column_small),
        textureDefault.copy(id = 30003L, resId = R.drawable.line_column_medium),
        textureDefault.copy(id = 30004L, resId = R.drawable.line_row_small),
        textureDefault.copy(id = 30005L, resId = R.drawable.line_row_medium),
        textureDefault.copy(id = 30006L, resId = R.drawable.pattern_6_1),
    )

    override val data: List<WallpaperTexture<Color>> = _textures

    override fun findByParams(params: WallpaperParams): WallpaperTexture<Color> {
        val texture = _textures.findById(params.id)

        return if (!params.isTextureStyle) {
            with(backgroundColorsStore.colors.findById(params.backgroundColorId)) {
                texture.copy(
                    tintColor = tintColorsStore.colors.findById(params.tintColorId),
                    backgroundColor = this,
                    isTextureStyle = false,
                    isDark = this.isDark
                )
            }
        } else {
            with(textureStyleStore.dayStyleList.findByParams(params)) {
                texture.copy(
                    tintColor = first,
                    backgroundColor = second,
                    isTextureStyle = true,
                    isDark = this.second.isDark
                )
            }
        }
    }

    val styles = textureStyleStore.dayStyleList
    val tintColors = tintColorsStore.colors
    val backgroundColors = backgroundColorsStore.colors
}
