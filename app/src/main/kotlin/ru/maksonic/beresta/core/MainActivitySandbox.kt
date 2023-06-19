package ru.maksonic.beresta.core

import ru.maksonic.beresta.elm.Sandbox
import ru.maksonic.beresta.elm.UpdatedModel

/**
 * @Author maksonic on 18.02.2023
 */
private typealias UpdateResult = UpdatedModel<Model, Set<Cmd>, Set<Eff>>

class MainActivitySandbox(mainActivityProgram: MainActivityProgram) : Sandbox<Model, Msg, Cmd, Eff>(
    initialModel = Model.Initial,
    initialCmd = setOf(
        Cmd.ReadLanguageFromDataStore,
        Cmd.ReadThemeFromDataStore,
        Cmd.FetchAppLanguage,
        Cmd.ReadThemePaletteFromDataStore,
    ),
    subscriptions = listOf(mainActivityProgram)
) {
    override fun update(msg: Msg, model: Model): UpdateResult = when (msg) {
        is Msg.Inner.SetAppLanguage -> afterApplyLanguage(model, msg)
        is Msg.Inner.SetAppTheme -> afterApplyTheme(model, msg)
        is Msg.Inner.FetchedLanguageForProvide -> afterFetchingLangProvider(model, msg)
        is Msg.Inner.SetAppThemePalette -> afterApplyThemePalette(model, msg)
    }

    private fun afterApplyTheme(model: Model, msg: Msg.Inner.SetAppTheme): UpdateResult =
        UpdatedModel(model.copy(theme = msg.theme))

    private fun afterApplyThemePalette(
        model: Model,
        msg: Msg.Inner.SetAppThemePalette
    ): UpdateResult =
        UpdatedModel(model.copy(themePalette = msg.palette))


    private fun afterApplyLanguage(model: Model, msg: Msg.Inner.SetAppLanguage): UpdateResult =
        UpdatedModel(model.copy(language = msg.language))

    private fun afterFetchingLangProvider(
        model: Model,
        msg: Msg.Inner.FetchedLanguageForProvide
    ): UpdateResult =
        UpdatedModel(model.copy(languageProvider = msg.lang))
}