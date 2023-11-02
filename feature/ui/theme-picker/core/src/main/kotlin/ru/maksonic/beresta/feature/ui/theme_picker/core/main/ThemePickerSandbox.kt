package ru.maksonic.beresta.feature.ui.theme_picker.core.main

import ru.maksonic.beresta.platform.elm.core.ElmUpdate
import ru.maksonic.beresta.platform.elm.core.Sandbox

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
            is Msg.Inner.FetchedAppTheme -> fetchedAppTheme(model, msg)
            is Msg.Ui.OnThemeClicked -> onThemeClicked(model, msg)
            is Msg.Ui.OnPaletteColorClicked -> onPaletteClicked(model, msg)
        }

    private fun fetchedAppTheme(
        model: Model, msg: Msg.Inner.FetchedAppTheme
    ): UpdateResult =
        ElmUpdate(model = model.copy(currentTheme = msg.theme, currentPalette = msg.palette))

    private fun onThemeClicked(model: Model, msg: Msg.Ui.OnThemeClicked): UpdateResult = ElmUpdate(
        model = model.copy(currentTheme = msg.theme),
        commands = setOf(Cmd.SaveSelectedTheme(msg.theme))
    )

    private fun onPaletteClicked(
        model: Model,
        msg: Msg.Ui.OnPaletteColorClicked
    ): UpdateResult = ElmUpdate(
        model = model.copy(currentPalette = msg.palette),
        commands = if (model.currentPalette != msg.palette)
            setOf(Cmd.SaveSelectedColorPalette(msg.palette))
        else emptySet()
    )
}