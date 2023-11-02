package ru.maksonic.beresta.feature.wallpaper_picker.data.store

import androidx.compose.ui.graphics.Color
import ru.maksonic.beresta.feature.wallpaper_picker.domain.WallpaperParams
import ru.maksonic.beresta.feature.wallpaper_picker.domain.wallpaper.WallpaperColor

/**
 * @Author maksonic on 23.09.2023
 */
class WallpaperColorsStore : BaseWallpaperStore<WallpaperColor<Color>> {
    /**
     *       COLOR MAP
     *   - 100000 - main
     *   - 101000 - deep
     *   - 102000 - toxic
     *   - 103000 - normal
     *   - 104000 - light
     *   - 105000 - pastel
     *   - 106000 - neutral
     * */
    private val color100000 = Color(0x00000000)
    private val color100001 = Color(0xFFFFFFFF)
    private val color100002 = Color(0xFF000000)
    private val color100003 = Color(0xFFFE0000)

    private val color101001 = Color(0xFF5E080B)
    private val color101002 = Color(0xFF602A06)
    private val color101003 = Color(0xFF663F00)
    private val color101004 = Color(0xFF664E00)
    private val color101005 = Color(0xFF665F00)
    private val color101006 = Color(0xFF506600)
    private val color101007 = Color(0xFF00660F)
    private val color101008 = Color(0xFF065960)
    private val color101009 = Color(0xFF002766)
    private val color101010 = Color(0xFF270052)
    private val color101011 = Color(0xFF3D0A4D)
    private val color101012 = Color(0xFF660041)

    private val color102001 = Color(0xFFFF383F)
    private val color102002 = Color(0xFFFF7214)
    private val color102003 = Color(0xFFFF9F05)
    private val color102004 = Color(0xFFFFD138)
    private val color102005 = Color(0xFFFFF129)
    private val color102006 = Color(0xFFCDFF1A)
    private val color102007 = Color(0xFF0FFF33)
    private val color102008 = Color(0xFF35E3F3)
    private val color102009 = Color(0xFF297BFF)
    private val color102010 = Color(0xFF881AFF)
    private val color102011 = Color(0xFFCB1FFF)
    private val color102012 = Color(0xFFFF3DB8)

    private val color103001 = Color(0xFFED1C24)
    private val color103002 = Color(0xFFF17524)
    private val color103003 = Color(0xFFF79700)
    private val color103004 = Color(0xFFFCC100)
    private val color103005 = Color(0xFFFCEC00)
    private val color103006 = Color(0xFFAEE000)
    private val color103007 = Color(0xFF00CC1D)
    private val color103008 = Color(0xFF0BB4C3)
    private val color103009 = Color(0xFF0051D4)
    private val color103010 = Color(0xFF460091)
    private val color103011 = Color(0xFF8414A6)
    private val color103012 = Color(0xFFE3008E)

    private val color104001 = Color(0xFFF47176)
    private val color104002 = Color(0xFFF6A56F)
    private val color104003 = Color(0xFFFFC466)
    private val color104004 = Color(0xFFFFDB66)
    private val color104005 = Color(0xFFFFF566)
    private val color104006 = Color(0xFFDEFF66)
    private val color104007 = Color(0xFF66FF7D)
    private val color104008 = Color(0xFF73ECF7)
    private val color104009 = Color(0xFF66A1FF)
    private val color104010 = Color(0xFFB066FF)
    private val color104011 = Color(0xFFD277EE)
    private val color104012 = Color(0xFFFF66C7)

    private val color105001 = Color(0xFFFBD0D1)
    private val color105002 = Color(0xFFFCE1CF)
    private val color105003 = Color(0xFFFFEBCC)
    private val color105004 = Color(0xFFFFF3CC)
    private val color105005 = Color(0xFFFFFCCC)
    private val color105006 = Color(0xFFF4FFCC)
    private val color105007 = Color(0xFFCCFFD4)
    private val color105008 = Color(0xFFCFF8FC)
    private val color105009 = Color(0xFFCCE0FF)
    private val color105010 = Color(0xFFE5CCFF)
    private val color105011 = Color(0xFFF0D2F9)
    private val color105012 = Color(0xFFFFCCEC)

    private val color106001 = Color(0xFFFEF1F1)
    private val color106002 = Color(0xFFFEF6F1)
    private val color106003 = Color(0xFFFFF9F0)
    private val color106004 = Color(0xFFFFFBF0)
    private val color106005 = Color(0xFFFFFEF0)
    private val color106006 = Color(0xFFFCFFF0)
    private val color106007 = Color(0xFFF0FFF2)
    private val color106008 = Color(0xFFF1FDFE)
    private val color106009 = Color(0xFFF0F6FF)
    private val color106010 = Color(0xFFF7F0FF)
    private val color106011 = Color(0xFFFBF1FD)
    private val color106012 = Color(0xFFFFF0F9)

    private val main = listOf(
        WallpaperColor(100000L, color100000, false),
        WallpaperColor(100001L, color100001, false),
        WallpaperColor(100002L, color100002, true),
        WallpaperColor(100003L, color100003, true),
    )
    private val deep = listOf(
        WallpaperColor(101001L, color101001, true),
        WallpaperColor(101002L, color101002, true),
        WallpaperColor(101003L, color101003, true),
        WallpaperColor(101004L, color101004, true),
        WallpaperColor(101005L, color101005, true),
        WallpaperColor(101006L, color101006, true),
        WallpaperColor(101007L, color101007, true),
        WallpaperColor(101008L, color101008, true),
        WallpaperColor(101009L, color101009, true),
        WallpaperColor(101010L, color101010, true),
        WallpaperColor(101011L, color101011, true),
        WallpaperColor(101012L, color101012, true),
    )
    private val toxic = listOf(
        WallpaperColor(102001L, color102001, true),
        WallpaperColor(102002L, color102002, true),
        WallpaperColor(102003L, color102003, false),
        WallpaperColor(102004L, color102004, false),
        WallpaperColor(102005L, color102005, false),
        WallpaperColor(102006L, color102006, false),
        WallpaperColor(102007L, color102007, false),
        WallpaperColor(102008L, color102008, false),
        WallpaperColor(102009L, color102009, true),
        WallpaperColor(102010L, color102010, true),
        WallpaperColor(102011L, color102011, true),
        WallpaperColor(102012L, color102012, true),
    )
    private val normal = listOf(
        WallpaperColor(103001L, color103001, true),
        WallpaperColor(103002L, color103002, true),
        WallpaperColor(103003L, color103003, true),
        WallpaperColor(103004L, color103004, false),
        WallpaperColor(103005L, color103005, false),
        WallpaperColor(103006L, color103006, false),
        WallpaperColor(103007L, color103007, false),
        WallpaperColor(103008L, color103008, false),
        WallpaperColor(103009L, color103009, true),
        WallpaperColor(103010L, color103010, true),
        WallpaperColor(103011L, color103011, true),
        WallpaperColor(103012L, color103012, true),
    )
    private val light = listOf(
        WallpaperColor(104001L, color104001, false),
        WallpaperColor(104002L, color104002, false),
        WallpaperColor(104003L, color104003, false),
        WallpaperColor(104004L, color104004, false),
        WallpaperColor(104005L, color104005, false),
        WallpaperColor(104006L, color104006, false),
        WallpaperColor(104007L, color104007, false),
        WallpaperColor(104008L, color104008, false),
        WallpaperColor(104009L, color104009, false),
        WallpaperColor(104010L, color104010, false),
        WallpaperColor(104011L, color104011, false),
        WallpaperColor(104012L, color104012, false),
    )
    private val pastel = listOf(
        WallpaperColor(105001L, color105001, false),
        WallpaperColor(105002L, color105002, false),
        WallpaperColor(105003L, color105003, false),
        WallpaperColor(105004L, color105004, false),
        WallpaperColor(105005L, color105005, false),
        WallpaperColor(105006L, color105006, false),
        WallpaperColor(105007L, color105007, false),
        WallpaperColor(105008L, color105008, false),
        WallpaperColor(105009L, color105009, false),
        WallpaperColor(105010L, color105010, false),
        WallpaperColor(105011L, color105011, false),
        WallpaperColor(105012L, color105012, false),
    )
    private val neutral = listOf(
        WallpaperColor(106001L, color106001, false),
        WallpaperColor(106002L, color106002, false),
        WallpaperColor(106003L, color106003, false),
        WallpaperColor(106004L, color106004, false),
        WallpaperColor(106005L, color106005, false),
        WallpaperColor(106006L, color106006, false),
        WallpaperColor(106007L, color106007, false),
        WallpaperColor(106008L, color106008, false),
        WallpaperColor(106009L, color106009, false),
        WallpaperColor(106010L, color106010, false),
        WallpaperColor(106011L, color106011, false),
        WallpaperColor(106012L, color106012, false),
    )
    private val colors = main + deep + toxic + normal + light + pastel + neutral

    override val data: List<WallpaperColor<Color>> = colors

    override fun findByParams(params: WallpaperParams): WallpaperColor<Color> =
        colors.find { it.id == params.id } ?: main.first()
}