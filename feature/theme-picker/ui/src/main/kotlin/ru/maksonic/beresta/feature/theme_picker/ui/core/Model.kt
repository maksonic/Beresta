package ru.maksonic.beresta.feature.theme_picker.ui.core

import ru.maksonic.beresta.elm.core.ElmCommand
import ru.maksonic.beresta.elm.core.ElmEffect
import ru.maksonic.beresta.elm.core.ElmMessage
import ru.maksonic.beresta.elm.core.ElmModel
import ru.maksonic.beresta.ui.theme.AppTheme
import ru.maksonic.beresta.ui.theme.color.AppThemePalette
import ru.maksonic.beresta.ui.theme.color.PaletteStore

/**
 * @Author maksonic on 19.06.2023
 */
data class Model(
    val currentTheme: Pair<AppTheme, Boolean>,
    val currentPalette: AppThemePalette,
) : ElmModel {
    companion object {
        val Initial = Model(
            currentTheme = Pair(AppTheme.SYSTEM, false), currentPalette = AppThemePalette.BLUE
        )
    }
}

sealed class Msg : ElmMessage {
    sealed class Ui : Msg() {
        data class OnThemeClicked(val theme: AppTheme) : Ui()
        data class OnPaletteColorClicked(val palette: AppThemePalette) : Ui()
    }

    sealed class Inner : Msg() {
        data class FetchedThemeWithPalettes(
            val theme: Pair<AppTheme, Boolean>, val palette: AppThemePalette
        ) : Inner()
    }
}

sealed class Cmd : ElmCommand {
    object FetchThemeWithPalette : Cmd()
    data class SaveSelectedThemeToDatastore(val theme: AppTheme) : Cmd()
    data class SaveSelectedColorPaletteToDatastore(
        val currentTheme: AppTheme,
        val isDarkMode: Boolean,
        val palette: AppThemePalette
    ) : Cmd()
}

sealed class Eff : ElmEffect {

}