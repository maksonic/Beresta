package ru.maksonic.beresta.feature.wallpaper_picker.data.store.texture

import androidx.compose.ui.graphics.Color
import ru.maksonic.beresta.feature.wallpaper_picker.data.R
import ru.maksonic.beresta.feature.wallpaper_picker.data.helpers.findById
import ru.maksonic.beresta.feature.wallpaper_picker.data.helpers.findByParams
import ru.maksonic.beresta.feature.wallpaper_picker.data.helpers.textureDefault
import ru.maksonic.beresta.feature.wallpaper_picker.data.store.BaseWallpaperStore
import ru.maksonic.beresta.feature.wallpaper_picker.data.store.texture.color.TextureColorsStore
import ru.maksonic.beresta.feature.wallpaper_picker.domain.WallpaperParams
import ru.maksonic.beresta.feature.wallpaper_picker.domain.wallpaper.WallpaperTexture

/**
 * @Author maksonic on 23.09.2023
 */
class WallpaperTexturesStore(
    private val textureStyleStore: TextureStyleStore,
    private val textureColorsStore: TextureColorsStore
) : BaseWallpaperStore<WallpaperTexture<Color>> {

    private val _textures = listOf(
        textureDefault.copy(id = 300000L, resId = R.drawable.cell_suared_small),
        textureDefault.copy(id = 300001L, resId = R.drawable.cell_suared_medium),
        textureDefault.copy(id = 300002L, resId = R.drawable.line_column_small),
        textureDefault.copy(id = 300003L, resId = R.drawable.line_column_medium),
        textureDefault.copy(id = 300004L, resId = R.drawable.line_row_small),
        textureDefault.copy(id = 300005L, resId = R.drawable.line_row_medium),
        textureDefault.copy(id = 300101L, resId = R.drawable.pattern_001),
        textureDefault.copy(id = 300102L, resId = R.drawable.pattern_002),
        textureDefault.copy(id = 300104L, resId = R.drawable.pattern_004),
        textureDefault.copy(id = 300105L, resId = R.drawable.pattern_005),
        textureDefault.copy(id = 300106L, resId = R.drawable.pattern_006),
        textureDefault.copy(id = 300107L, resId = R.drawable.pattern_007),
        textureDefault.copy(id = 300108L, resId = R.drawable.pattern_008),
        textureDefault.copy(id = 300110L, resId = R.drawable.pattern_010),
        textureDefault.copy(id = 300111L, resId = R.drawable.pattern_011),
        textureDefault.copy(id = 300112L, resId = R.drawable.pattern_012),
        textureDefault.copy(id = 300113L, resId = R.drawable.pattern_013),
        textureDefault.copy(id = 300114L, resId = R.drawable.pattern_014),
        textureDefault.copy(id = 300115L, resId = R.drawable.pattern_015),
        textureDefault.copy(id = 300116L, resId = R.drawable.pattern_016),
        textureDefault.copy(id = 300117L, resId = R.drawable.pattern_017),
        textureDefault.copy(id = 300119L, resId = R.drawable.pattern_019),
        textureDefault.copy(id = 300120L, resId = R.drawable.pattern_020),
        textureDefault.copy(id = 300121L, resId = R.drawable.pattern_021),
        textureDefault.copy(id = 300122L, resId = R.drawable.pattern_022),
        textureDefault.copy(id = 300123L, resId = R.drawable.pattern_023),
        textureDefault.copy(id = 300124L, resId = R.drawable.pattern_024),
        textureDefault.copy(id = 300125L, resId = R.drawable.pattern_025),
        textureDefault.copy(id = 300126L, resId = R.drawable.pattern_026),
        textureDefault.copy(id = 300127L, resId = R.drawable.pattern_027),
        textureDefault.copy(id = 300128L, resId = R.drawable.pattern_028),
        textureDefault.copy(id = 300129L, resId = R.drawable.pattern_029),
        textureDefault.copy(id = 300130L, resId = R.drawable.pattern_030),
        textureDefault.copy(id = 300131L, resId = R.drawable.pattern_031),
        textureDefault.copy(id = 300132L, resId = R.drawable.pattern_032),
        textureDefault.copy(id = 300133L, resId = R.drawable.pattern_033),
    )

    override val data: List<WallpaperTexture<Color>> = _textures

    override fun findByParams(params: WallpaperParams): WallpaperTexture<Color> {
        val texture = _textures.findById(params.id)

        return if (!params.isTextureStyle) {
            with(textureColorsStore.backgrounds.findById(params.backgroundColorId)) {
                texture.copy(
                    tintColor = textureColorsStore.tints.findById(params.tintColorId),
                    tintColorAlpha = params.tintColorAlpha,
                    backgroundColor = this,
                    backgroundColorAlpha = params.backgroundColorAlpha,
                    isTextureStyle = false,
                    isDark = this.isDark
                )
            }
        } else {
            with(textureStyleStore.dayStyleList.findByParams(params)) {
                texture.copy(
                    tintColor = first,
                    tintColorAlpha = params.tintColorAlpha,
                    backgroundColor = second,
                    backgroundColorAlpha = params.backgroundColorAlpha,
                    isTextureStyle = true,
                    isDark = this.second.isDark
                )
            }
        }
    }

    val styles = textureStyleStore.dayStyleList
    val backgroundColors = textureColorsStore.backgrounds
    val tintColors = textureColorsStore.tints
}
