package ru.maksonic.beresta.core

import ru.maksonic.beresta.platform.elm.core.ElmUpdate
import ru.maksonic.beresta.platform.elm.core.Sandbox

/**
 * @Author maksonic on 18.02.2023
 */
private typealias UpdateResult = ElmUpdate<Model, Set<Cmd>, Set<Eff>>

class MainActivitySandbox(mainActivityProgram: MainActivityProgram) : Sandbox<Model, Msg, Cmd, Eff>(
    initialModel = Model.Initial,
    initialCmd = setOf(Cmd.FetchAppLangProvider, Cmd.FetchAppTheme, Cmd.FetchAnimationVelocity),
    subscriptions = listOf(mainActivityProgram)
) {
    override fun update(msg: Msg, model: Model): UpdateResult = when (msg) {
        is Msg.Inner.FetchedThemeContainer -> fetchedTheme(model, msg)
        is Msg.Inner.UpdatedThemeDarkModeValue -> updatedThemeDarkMode(model, msg)
        is Msg.Inner.FetchedLanguageProvider -> fetchedLangProvider(model, msg)
        is Msg.Inner.FetchedAnimationsVelocity -> fetchedAnimationsVelocity(model, msg)
    }

    private fun fetchedTheme(model: Model, msg: Msg.Inner.FetchedThemeContainer): UpdateResult =
        ElmUpdate(
            model = model.copy(
                currentTheme = msg.data.currentTheme,
                currentPalette = msg.data.currentPalette,
                paletteContainer = msg.data.paletteContainer,
                darkMode = model.darkMode.copy(msg.data.isDarkMode)
            )
        )

    private fun updatedThemeDarkMode(
        model: Model,
        msg: Msg.Inner.UpdatedThemeDarkModeValue
    ): UpdateResult = ElmUpdate(
        model = model.copy(darkMode = model.darkMode.copy(msg.isDark)),
        commands = setOf(Cmd.UpdateThemeDarkMode(msg.isDark))
    )

    private fun fetchedLangProvider(
        model: Model,
        msg: Msg.Inner.FetchedLanguageProvider
    ): UpdateResult = ElmUpdate(model.copy(languageProvider = msg.provider))

    private fun fetchedAnimationsVelocity(
        model: Model,
        msg: Msg.Inner.FetchedAnimationsVelocity
    ): UpdateResult = ElmUpdate(model.copy(animationVelocity = msg.key))
}