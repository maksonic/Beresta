package ru.maksonic.beresta.feature.wallpaper_picker.data.store

import androidx.compose.ui.graphics.Color
import ru.maksonic.beresta.feature.wallpaper_picker.data.R
import ru.maksonic.beresta.feature.wallpaper_picker.domain.WallpaperParams
import ru.maksonic.beresta.feature.wallpaper_picker.domain.wallpaper.WallpaperImage

/**
 * @Author maksonic on 23.09.2023
 */
class WallpaperImagesStore : BaseWallpaperStore<WallpaperImage<Color>> {
    private val wallpaperDefault = WallpaperImage<Color>(id = 0, resId = 0, isDark = false)

    private val _images: List<WallpaperImage<Color>> = listOf(
        WallpaperImage(id = 400001L, resId = R.drawable.wp_001, isDark = true),
        WallpaperImage(id = 400002L, resId = R.drawable.wp_002, isDark = true),
        WallpaperImage(id = 400003L, resId = R.drawable.wp_003, isDark = false),
        WallpaperImage(id = 400004L, resId = R.drawable.wp_004, isDark = true),
        WallpaperImage(id = 400005L, resId = R.drawable.wp_005, isDark = true),
        WallpaperImage(id = 400006L, resId = R.drawable.wp_006, isDark = true),
        WallpaperImage(id = 400007L, resId = R.drawable.wp_007, isDark = true),
        WallpaperImage(id = 400008L, resId = R.drawable.wp_008, isDark = true),
        WallpaperImage(id = 400009L, resId = R.drawable.wp_009, isDark = true),
        WallpaperImage(id = 400010L, resId = R.drawable.wp_010, isDark = true),
        WallpaperImage(id = 400011L, resId = R.drawable.wp_011, isDark = false),
        WallpaperImage(id = 400012L, resId = R.drawable.wp_012, isDark = true),
        WallpaperImage(id = 400013L, resId = R.drawable.wp_013, isDark = true),
        WallpaperImage(id = 400014L, resId = R.drawable.wp_014, isDark = true),
        WallpaperImage(id = 400015L, resId = R.drawable.wp_015, isDark = true),
        WallpaperImage(id = 400016L, resId = R.drawable.wp_016, isDark = true),
        WallpaperImage(id = 400017L, resId = R.drawable.wp_017, isDark = false),
        WallpaperImage(id = 400018L, resId = R.drawable.wp_018, isDark = true),
        WallpaperImage(id = 400019L, resId = R.drawable.wp_019, isDark = true),
        WallpaperImage(id = 400020L, resId = R.drawable.wp_020, isDark = true),
        WallpaperImage(id = 400021L, resId = R.drawable.wp_021, isDark = true),
        WallpaperImage(id = 400022L, resId = R.drawable.wp_022, isDark = true),
        WallpaperImage(id = 400023L, resId = R.drawable.wp_023, isDark = true),
        WallpaperImage(id = 400024L, resId = R.drawable.wp_024, isDark = true),
    )

    override val data: List<WallpaperImage<Color>> = _images

    override fun findByParams(params: WallpaperParams): WallpaperImage<Color> =
        _images.find { it.id == params.id } ?: wallpaperDefault
}