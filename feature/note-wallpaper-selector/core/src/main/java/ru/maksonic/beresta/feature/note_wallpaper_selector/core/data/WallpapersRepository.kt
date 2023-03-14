package ru.maksonic.beresta.feature.note_wallpaper_selector.core.data

import ru.maksonic.beresta.feature.note_wallpaper_selector.core.R

/**
 * @Author maksonic on 10.03.2023
 */
object WallpapersRepository {

    private val gentleColorsIds = arrayOf(
        R.drawable.color_soft_misty_rose
    )

    private val newWallpapersData = arrayOf(
        R.drawable.wallpaper_new_0001,
        R.drawable.wallpaper_new_0002,
        R.drawable.wallpaper_new_0003,
        R.drawable.wallpaper_new_0004,
        R.drawable.wallpaper_new_0005,
        R.drawable.wallpaper_new_0006,
        R.drawable.wallpaper_new_0007,
        R.drawable.wallpaper_new_0008,
        R.drawable.wallpaper_new_0009,
        R.drawable.wallpaper_new_0010,
        R.drawable.wallpaper_new_0011,
        R.drawable.wallpaper_new_0012,
        R.drawable.wallpaper_new_0013,
        R.drawable.wallpaper_new_0014,
        R.drawable.wallpaper_new_0015,
        R.drawable.wallpaper_new_0016,
        R.drawable.wallpaper_new_0017,
        R.drawable.wallpaper_new_0018,
        R.drawable.wallpaper_new_0019,
        R.drawable.wallpaper_new_0020,
        R.drawable.wallpaper_new_0021,
        R.drawable.wallpaper_new_0022,
        R.drawable.wallpaper_new_0023,
        R.drawable.wallpaper_new_0024,
    )

    private val softColorsData = arrayOf(
        R.drawable.color_soft_misty_rose,
        R.drawable.color_soft_cornsilk,
        R.drawable.color_soft_light_yellow,
        R.drawable.color_soft_nyanza,
        R.drawable.color_soft_ultra_light_mint,
        R.drawable.color_soft_light_cyan,
        R.drawable.color_soft_lavender,
        R.drawable.color_soft_lavender_light_blue,
        R.drawable.color_soft_blue_chalk,
        R.drawable.color_soft_pink_lace,
        R.drawable.color_soft_pale_rose,
        R.drawable.color_soft_classic_rose,
        R.drawable.color_soft_shampoo,
        R.drawable.color_soft_brilliant_lavender,
        R.drawable.color_soft_lavender_blue,
        R.drawable.color_soft_water,
        R.drawable.color_soft_aero_blue,
        R.drawable.color_soft_conditioner,
    )

    private val brightColorsData = arrayOf(
        R.drawable.color_bright_congo_pink,
        R.drawable.color_bright_macaroni_and_cheese,
        R.drawable.color_bright_jasmine,
        R.drawable.color_bright_sunny,
        R.drawable.color_bright_mindaro,
        R.drawable.color_bright_light_green,
        R.drawable.color_bright_mint_green,
        R.drawable.color_bright_electric_blue,
        R.drawable.color_bright_pale_cyan,
        R.drawable.color_bright_french_sky_bule,
        R.drawable.color_bright_violets_are_blue,
        R.drawable.color_bright_pale_violet,
        R.drawable.color_bright_pink_flamingo,
        R.drawable.color_bright_princess_perfume,
        R.drawable.color_bright_tickle_me_pink,
    )

    private val gradientColorsData = arrayOf(
        R.drawable.color_gradient_roseanna,
        R.drawable.color_gradient_sexy_blue,
        R.drawable.color_gradient_purple_love,
        R.drawable.color_gradient_piglet,
        R.drawable.color_gradient_mauve,
        R.drawable.color_gradient_50_shades_of_grey,
        R.drawable.color_gradient_a_lost_memory,
        R.drawable.color_gradient_socialive,
        R.drawable.color_gradient_cherry,
        R.drawable.color_gradient_lush,
        R.drawable.color_gradient_kashimir,
        R.drawable.color_gradient_frost,
        R.drawable.color_gradient_love_tonight,
        R.drawable.color_gradient_dusk,
        R.drawable.color_gradient_relay,
        R.drawable.color_gradient_scooter,
        R.drawable.color_gradient_wedding_day_blues,
        R.drawable.color_gradient_cosmic_fusion,
    )

    val wallpapers = Array(gentleColorsIds.size) {
        NoteWallpaper(
            id = it.plus(777).toLong(),
            resourceId = gentleColorsIds[it]
        )
    }
    private val newWallpapers = Array(newWallpapersData.size) { index ->
        NoteWallpaper(id = index.plus(111).toLong(), resourceId = newWallpapersData[index])
    }

    private val softColorWallpapers = Array(softColorsData.size) { index ->
        NoteWallpaper(id = index.plus(222).toLong(), resourceId = softColorsData[index])
    }

    private val brightColorWallpapers = Array(brightColorsData.size) { index ->
        NoteWallpaper(id = index.plus(333).toLong(), resourceId = brightColorsData[index])
    }

    private val gradientColorWallpapers = Array(gradientColorsData.size) { index ->
        NoteWallpaper(id = index.plus(444).toLong(), resourceId = gradientColorsData[index])
    }

    private val data = arrayOf(
        WallpaperCategory(id = 0, title = "Новинки", wallpapers = newWallpapers),
        WallpaperCategory(id = 1, title = "Цвета", wallpapers = softColorWallpapers),
        WallpaperCategory(id = 2, title = "Яркие цвета", wallpapers = brightColorWallpapers),
        WallpaperCategory(id = 3, title = "Градиент", wallpapers = gradientColorWallpapers),
        WallpaperCategory(id = 4, title = "Сетка", wallpapers = wallpapers),
        WallpaperCategory(id = 5, title = "Лучшее", wallpapers = wallpapers),
    )

    val wallpapersData = data
}