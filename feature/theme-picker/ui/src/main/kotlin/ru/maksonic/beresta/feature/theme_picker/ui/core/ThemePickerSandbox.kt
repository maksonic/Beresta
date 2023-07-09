package ru.maksonic.beresta.feature.theme_picker.ui.core

import ru.maksonic.beresta.elm.core.ElmUpdate
import ru.maksonic.beresta.elm.core.Sandbox
import ru.maksonic.beresta.ui.theme.AppTheme

/**
 * @Author maksonic on 19.06.2023
 */
private typealias UpdateResult = ElmUpdate<Model, Set<Cmd>, Set<Eff>>

class ThemePickerSandbox(program: ThemePickerProgram) : Sandbox<Model, Msg, Cmd, Eff>(
    initialModel = Model.Initial,
    initialCmd = setOf(Cmd.FetchThemeWithPalette),
    subscriptions = listOf(program)
) {
    override fun update(msg: Msg, model: Model): UpdateResult =
        when (msg) {
            is Msg.Inner.FetchedThemeWithPalettes -> fetchedThemeWithPalettes(model, msg)
            is Msg.Ui.OnThemeClicked -> onThemeClicked(model, msg)
            is Msg.Ui.OnPaletteColorClicked -> onPaletteClicked(model, msg)
        }

    private fun fetchedThemeWithPalettes(
        model: Model,
        msg: Msg.Inner.FetchedThemeWithPalettes
    ): UpdateResult = ElmUpdate(
        model = model.copy(
            currentTheme = model.currentTheme.copy(msg.theme.first, msg.theme.second),
            currentPalette = msg.palette
        )
    )

    private fun onThemeClicked(model: Model, msg: Msg.Ui.OnThemeClicked): UpdateResult = ElmUpdate(
        model = model.copy(currentTheme = model.currentTheme.copy(msg.theme)),
        commands = setOf(Cmd.SaveSelectedThemeToDatastore(msg.theme))
    )

    private fun onPaletteClicked(
        model: Model,
        msg: Msg.Ui.OnPaletteColorClicked
    ): UpdateResult {

        val isDark = when (model.currentTheme.first) {
            AppTheme.LIGHT -> false
            AppTheme.SYSTEM -> model.currentTheme.second
            else -> true
        }

        return ElmUpdate(
            model = model.copy(currentPalette = msg.palette),
            commands = setOf(
                Cmd.SaveSelectedColorPaletteToDatastore(
                    currentTheme = model.currentTheme.first,
                    isDarkMode = isDark,
                    palette = msg.palette
                )
            )
        )
    }
}