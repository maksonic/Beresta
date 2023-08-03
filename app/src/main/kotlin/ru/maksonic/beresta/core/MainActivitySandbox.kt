package ru.maksonic.beresta.core

import ru.maksonic.beresta.elm.core.ElmUpdate
import ru.maksonic.beresta.elm.core.Sandbox
import ru.maksonic.beresta.ui.theme.AppTheme

/**
 * @Author maksonic on 18.02.2023
 */
private typealias UpdateResult = ElmUpdate<Model, Set<Cmd>, Set<Eff>>

class MainActivitySandbox(mainActivityProgram: MainActivityProgram) : Sandbox<Model, Msg, Cmd, Eff>(
    initialModel = Model.Initial,
    initialCmd = setOf(
        Cmd.FetchAppLangProvider,
        Cmd.FetchThemeFromDataStore,
        Cmd.FetchThemePaletteFromDataStore,
        Cmd.FetchAnimationVelocity
    ),
    subscriptions = listOf(mainActivityProgram)
) {
    override fun update(msg: Msg, model: Model): UpdateResult = when (msg) {
        is Msg.Inner.FetchedTheme -> fetchedTheme(model, msg)
        is Msg.Inner.FetchedThemePalette -> fetchedThemePalette(model, msg)
        is Msg.Inner.UpdatedThemeDarkModeValue -> updatedThemeDarkMode(model, msg)
        is Msg.Inner.FetchedLanguageProvider -> fetchedLangProvider(model, msg)
        is Msg.Inner.FetchedAnimationsVelocity -> fetchedAnimationsVelocity(model, msg)
    }

    private fun fetchedTheme(model: Model, msg: Msg.Inner.FetchedTheme): UpdateResult =
        ElmUpdate(
            model = model.copy(
                currentTheme = msg.theme.first,
                darkMode = model.darkMode.copy(msg.theme.second)
            )
        )

    private fun fetchedThemePalette(
        model: Model,
        msg: Msg.Inner.FetchedThemePalette
    ): UpdateResult {
        val light = msg.palette.light
        val dark = msg.palette.dark
        val highContrast = msg.palette.highContrast

        val palette = when (model.currentTheme) {
            AppTheme.SYSTEM -> if (model.darkMode.value) dark else light
            AppTheme.LIGHT -> light
            AppTheme.DARK -> dark
            AppTheme.HIGH_CONTRAST -> highContrast
        }

        return ElmUpdate(model.copy(themePalette = msg.palette, currentPalette = palette))
    }

    private fun updatedThemeDarkMode(
        model: Model,
        msg: Msg.Inner.UpdatedThemeDarkModeValue
    ): UpdateResult {
        val light = model.themePalette.light
        val dark = model.themePalette.dark
        val highContrast = model.themePalette.highContrast

        val palette = when (model.currentTheme) {
            AppTheme.SYSTEM -> if (msg.isDark) dark else light
            AppTheme.LIGHT -> light
            AppTheme.DARK -> dark
            AppTheme.HIGH_CONTRAST -> highContrast
        }
        return ElmUpdate(
            model.copy(currentPalette = palette, darkMode = model.darkMode.copy(msg.isDark)),
            commands = setOf(Cmd.UpdateDarkModeInDatastore(msg.isDark))
        )
    }

    private fun fetchedLangProvider(
        model: Model,
        msg: Msg.Inner.FetchedLanguageProvider
    ): UpdateResult = ElmUpdate(model.copy(languageProvider = msg.provider))

    private fun fetchedAnimationsVelocity(
        model: Model,
        msg: Msg.Inner.FetchedAnimationsVelocity
    ): UpdateResult = ElmUpdate(model.copy(animationVelocity = msg.key))
}