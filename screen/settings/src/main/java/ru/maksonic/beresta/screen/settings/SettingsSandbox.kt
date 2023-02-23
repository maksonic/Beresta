package ru.maksonic.beresta.screen.settings

import androidx.compose.material.ExperimentalMaterialApi
import ru.maksonic.beresta.elm.Sandbox
import ru.maksonic.beresta.elm.UpdatedModel
import ru.maksonic.beresta.screen.settings.presentation.widget.ModalSheetContent

/**
 * @Author maksonic on 23.01.2023
 */
private typealias UpdateResult = UpdatedModel<Model, Set<Cmd>, Set<Eff>>

@OptIn(ExperimentalMaterialApi::class)
class SettingsSandbox(
    program: SettingsProgram
) : Sandbox<Model, Msg, Cmd, Eff>(
    initialModel = Model(),
    initialCmd = setOf(Cmd.FetchCurrentTheme),
    subscriptions = listOf(program)
) {

    override fun update(msg: Msg, model: Model): UpdateResult = when (msg) {
        is Msg.Ui.OnTopBarBackPressed -> topBarBackPressed(model)
        is Msg.Ui.OnShowSelectLanguageSheetClicked -> onShowLanguageSheetClicked(model)
        is Msg.Ui.OnShowSelectThemeSheetClicked -> onShowThemeSheetClicked(model)
        is Msg.Ui.OnHideModalSheetClicked -> onHideLanguageSheetClicked(model)
        is Msg.Inner.FetchedTheme -> fetchedTheme(model, msg)
    }

    private fun topBarBackPressed(model: Model): UpdateResult =
        UpdatedModel(model, effects = setOf(Eff.NavigateBack))

    private fun onShowLanguageSheetClicked(model: Model): UpdateResult =
        UpdatedModel(
            model = model.copy(currentSheetContent = ModalSheetContent.LANGUAGE_SELECTOR),
            effects = setOf(Eff.ShowModalSheet)
        )

    private fun onShowThemeSheetClicked(model: Model): UpdateResult =
        UpdatedModel(
            model = model.copy(currentSheetContent = ModalSheetContent.THEME_SELECTOR),
            effects = setOf(Eff.ShowModalSheet)
        )

    private fun onHideLanguageSheetClicked(model: Model): UpdateResult =
        UpdatedModel(model, effects = setOf(Eff.HideModalSheet))

    private fun fetchedTheme(model: Model, msg: Msg.Inner.FetchedTheme): UpdateResult =
        UpdatedModel(model.copy(currentTheme = msg.theme))
}