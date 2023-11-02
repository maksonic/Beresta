package ru.maksonic.beresta.feature.wallpaper_picker.data.store.texture

import androidx.compose.ui.graphics.Color
import ru.maksonic.beresta.feature.wallpaper_picker.domain.BackgroundColor

/**
 * @Author maksonic on 31.10.2023
 */
abstract class BaseWallpaperColors {
    private object Bright {
        val color101 = Color(0xFFFF0000)
        val color102 = Color(0xFFFF0056)
        val color103 = Color(0xFFDB00FF)
        val color104 = Color(0xFF5600F1)
        val color105 = Color(0xFF002BFF)
        val color106 = Color(0xFF0090FF)
        val color107 = Color(0xFF38C3FF)
        val color108 = Color(0xFF00E4FF)
        val color109 = Color(0xFF00D1BC)
        val color110 = Color(0xFF27CA2D)
        val color111 = Color(0xFF93E833)
        val color112 = Color(0xFFE3F44A)
        val color113 = Color(0xFFFFE700)
        val color114 = Color(0xFFFFC000)
        val color115 = Color(0xFF686868)
        val color116 = Color(0xFF244F5F)

        val colors = listOf(
            BackgroundColor(101L, color101, true),
            BackgroundColor(102L, color102, true),
            BackgroundColor(103L, color103, true),
            BackgroundColor(104L, color104, true),
            BackgroundColor(105L, color105, true),
            BackgroundColor(106L, color106, true),
            BackgroundColor(107L, color107, false),
            BackgroundColor(108L, color108, false),
            BackgroundColor(109L, color109, false),
            BackgroundColor(110L, color110, false),
            BackgroundColor(111L, color111, false),
            BackgroundColor(112L, color112, false),
            BackgroundColor(113L, color113, false),
            BackgroundColor(114L, color114, false),
            BackgroundColor(115L, color115, false),
            BackgroundColor(116L, color116, false),
        )
    }

    private object Normal {
        val color201 = Color(0xFFF34334)
        val color202 = Color(0xFFE71E62)
        val color203 = Color(0xFF9B27AE)
        val color204 = Color(0xFF663AB5)
        val color205 = Color(0xFF3D51B4)
        val color206 = Color(0xFF1F96F2)
        val color207 = Color(0xFF01A9F2)
        val color208 = Color(0xFF00BCD2)
        val color209 = Color(0xFF009687)
        val color210 = Color(0xFF4BAF4F)
        val color211 = Color(0xFF89C348)
        val color212 = Color(0xFFCCDD39)
        val color213 = Color(0xFFFFEC3A)
        val color214 = Color(0xFFFEC106)
        val color215 = Color(0xFF9E9E9E)
        val color216 = Color(0xFF5F7D88)

        val colors = listOf(
            BackgroundColor(201L, color201, true),
            BackgroundColor(202L, color202, true),
            BackgroundColor(203L, color203, true),
            BackgroundColor(204L, color204, true),
            BackgroundColor(205L, color205, true),
            BackgroundColor(206L, color206, true),
            BackgroundColor(207L, color207, false),
            BackgroundColor(208L, color208, false),
            BackgroundColor(209L, color209, false),
            BackgroundColor(210L, color210, false),
            BackgroundColor(211L, color211, false),
            BackgroundColor(212L, color212, false),
            BackgroundColor(213L, color213, false),
            BackgroundColor(214L, color214, false),
            BackgroundColor(215L, color215, false),
            BackgroundColor(216L, color216, false)
        )
    }

    private object Neutral {
        val color301 = Color(0xFFF9A69F)
        val color302 = Color(0xFFF5A3BE)
        val color303 = Color(0xFFE4ACEC)
        val color304 = Color(0xFFC4B2E6)
        val color305 = Color(0xFFB3BBE5)
        val color306 = Color(0xFF9FD2F9)
        val color307 = Color(0xFF9AE0FE)
        val color308 = Color(0xFFB3F0F6)
        val color309 = Color(0xFFA9F2EB)
        val color310 = Color(0xFFB8E0B9)
        val color311 = Color(0xFFCEE6B2)
        val color312 = Color(0xFFE9F0A8)
        val color313 = Color(0xFFFFF599)
        val color314 = Color(0xFFFEE59A)
        val color315 = Color(0xFFE6E6E6)
        val color316 = Color(0xFFC3D0D5)

        val colors = listOf(
            BackgroundColor(301L, color301, false),
            BackgroundColor(302L, color302, false),
            BackgroundColor(303L, color303, false),
            BackgroundColor(304L, color304, false),
            BackgroundColor(305L, color305, false),
            BackgroundColor(306L, color306, false),
            BackgroundColor(307L, color307, false),
            BackgroundColor(308L, color308, false),
            BackgroundColor(309L, color309, false),
            BackgroundColor(310L, color310, false),
            BackgroundColor(311L, color311, false),
            BackgroundColor(312L, color312, false),
            BackgroundColor(313L, color313, false),
            BackgroundColor(314L, color314, false),
            BackgroundColor(315L, color315, false),
            BackgroundColor(316L, color316, false),
        )
    }

    private object Additional {
        val color401 = Color(0xFFFFF5F5)
        val color402 = Color(0xFFFFFCF5)
        val color403 = Color(0xFFFFFFF5)
        val color404 = Color(0xFFF5FFF5)
        val color405 = Color(0xFFF5FBFF)
        val color406 = Color(0xFFF5F5FF)
        val color407 = Color(0xFFF9F5FF)
        val color408 = Color(0xFFFFF5FC)
        val color409 = Color(0xFFFFD700)
        val color410 = Color(0xFFC0C0C0)
        val color411 = Color(0xFFCD7F32)
        val color412 = Color(0xFFE5E4E2)
        val color413 = Color(0xFF878681)
        val color414 = Color(0xFF9F003D)

        val colors = listOf(
            BackgroundColor(401L, color401, false),
            BackgroundColor(402L, color402, false),
            BackgroundColor(403L, color403, false),
            BackgroundColor(404L, color404, false),
            BackgroundColor(405L, color405, false),
            BackgroundColor(406L, color406, false),
            BackgroundColor(407L, color407, false),
            BackgroundColor(408L, color408, false),
            BackgroundColor(409L, color409, false),
            BackgroundColor(410L, color410, false),
            BackgroundColor(411L, color411, false),
            BackgroundColor(412L, color412, false),
            BackgroundColor(413L, color413, false),
            BackgroundColor(414L, color414, false),
        )
    }

    private val brightColors = Bright.colors
    private val normalColors = Normal.colors
    private val neutralColors = Neutral.colors
    private val additionalColors = Additional.colors

    protected val baseColors = brightColors + normalColors + neutralColors + additionalColors
}