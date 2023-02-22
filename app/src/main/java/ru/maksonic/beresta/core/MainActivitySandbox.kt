package ru.maksonic.beresta.core

import ru.maksonic.beresta.elm.Sandbox
import ru.maksonic.beresta.elm.UpdatedModel

/**
 * @Author maksonic on 18.02.2023
 */
private typealias UpdateResult = UpdatedModel<Model, Set<Cmd>, Set<Eff>>

class MainActivitySandbox(mainActivityProgram: MainActivityProgram) : Sandbox<Model, Msg, Cmd, Eff>(
    initialModel = Model(),
    initialCmd = setOf(
        Cmd.ReadLanguageFromDataStore, Cmd.ReadThemeFromDataStore, Cmd.FetchAppLanguage
    ),
    subscriptions = listOf(mainActivityProgram)
) {
    override fun update(msg: Msg, model: Model): UpdateResult = when (msg) {
        is Msg.Inner.SetAppLanguage -> afterApplyLanguage(model, msg)
        is Msg.Inner.SetAppTheme -> afterApplyTheme(model, msg)
        is Msg.Inner.FetchedLanguageForProvide -> afterFetchingLangProvider(model, msg)
    }

    private fun afterApplyLanguage(model: Model, msg: Msg.Inner.SetAppLanguage): UpdateResult =
        UpdatedModel(model.copy(language = msg.language))

    private fun afterApplyTheme(model: Model, msg: Msg.Inner.SetAppTheme): UpdateResult =
        UpdatedModel(model.copy(theme = msg.theme))

    private fun afterFetchingLangProvider(
        model: Model,
        msg: Msg.Inner.FetchedLanguageForProvide
    ): UpdateResult =
        UpdatedModel(model.copy(languageProvider = msg.lang))
}