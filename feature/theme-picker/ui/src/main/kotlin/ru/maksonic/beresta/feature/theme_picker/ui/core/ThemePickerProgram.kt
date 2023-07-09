package ru.maksonic.beresta.feature.theme_picker.ui.core

import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import ru.maksonic.beresta.elm.core.ElmProgram
import ru.maksonic.beresta.feature.theme_picker.api.ThemePickerApi
import ru.maksonic.beresta.ui.theme.AppTheme
import ru.maksonic.beresta.ui.theme.color.AppThemePalette

/**
 * @Author maksonic on 20.06.2023
 */
class ThemePickerProgram(
    private val themeFeatureApi: ThemePickerApi.Feature.Theme,
    private val paletteFeatureApi: ThemePickerApi.Feature.Palette,
) : ElmProgram<Msg, Cmd> {
    override suspend fun executeProgram(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            is Cmd.FetchThemeWithPalette -> fetchThemeWithPalette(consumer)
            is Cmd.SaveSelectedThemeToDatastore -> saveCurrentTheme(cmd.theme)
            is Cmd.SaveSelectedColorPaletteToDatastore -> {
                saveThemePalette(cmd.currentTheme, cmd.isDarkMode, cmd.palette)
            }
        }
    }

    private suspend fun fetchThemeWithPalette(consumer: (Msg) -> Unit) {
        combine(themeFeatureApi.current, paletteFeatureApi.current) { theme, palettes ->
            val currentPalette = when (theme.first) {
                AppTheme.SYSTEM -> if (theme.second) palettes.dark else palettes.light
                AppTheme.LIGHT -> palettes.light
                AppTheme.DARK -> palettes.dark
                AppTheme.HIGH_CONTRAST -> palettes.highContrast
            }
            consumer(Msg.Inner.FetchedThemeWithPalettes(theme, currentPalette))
        }.collect()
    }

    private suspend fun saveCurrentTheme(theme: AppTheme) = themeFeatureApi.setTheme(theme)

    private suspend fun saveThemePalette(
        currentTheme: AppTheme,
        isDarkMode: Boolean,
        palette: AppThemePalette
    ) = paletteFeatureApi.setPalette(currentTheme, isDarkMode, palette)
}