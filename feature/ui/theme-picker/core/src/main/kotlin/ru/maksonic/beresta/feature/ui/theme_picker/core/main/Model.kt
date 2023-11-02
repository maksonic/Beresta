package ru.maksonic.beresta.feature.ui.theme_picker.core.main

import ru.maksonic.beresta.common.ui_theme.AppThemePaletteUi
import ru.maksonic.beresta.common.ui_theme.AppThemeUi
import ru.maksonic.beresta.platform.elm.core.ElmCommand
import ru.maksonic.beresta.platform.elm.core.ElmEffect
import ru.maksonic.beresta.platform.elm.core.ElmMessage
import ru.maksonic.beresta.platform.elm.core.ElmModel

/**
 * @Author maksonic on 19.06.2023
 */
data class Model(
    val currentTheme: AppThemeUi,
    val currentPalette: AppThemePaletteUi,
) : ElmModel {
    companion object {
        val Initial = Model(
            currentTheme = AppThemeUi.SYSTEM, currentPalette = AppThemePaletteUi.BLUE
        )
    }
}

sealed class Msg : ElmMessage {
    sealed class Ui : Msg() {
        data class OnThemeClicked(val theme: AppThemeUi) : Ui()
        data class OnPaletteColorClicked(val palette: AppThemePaletteUi) : Ui()
    }

    sealed class Inner : Msg() {
        data class FetchedAppTheme(val theme: AppThemeUi, val palette: AppThemePaletteUi) : Inner()
    }
}

sealed class Cmd : ElmCommand {
    data object FetchThemeWithPalette : Cmd()
    data class SaveSelectedTheme(val theme: AppThemeUi) : Cmd()
    data class SaveSelectedColorPalette(val palette: AppThemePaletteUi) : Cmd()
}

sealed class Eff : ElmEffect