package ru.maksonic.beresta.feature.sorting_sheet.ui.core

import ru.maksonic.beresta.platform.elm.core.ElmUpdate
import ru.maksonic.beresta.platform.elm.core.Sandbox

/**
 * @Author maksonic on 09.07.2023
 */
private typealias UpdateResult = ElmUpdate<Model, Set<Cmd>, Set<Eff>>

class SortingSheetSandbox(
    program: SortingSheetProgram
) : Sandbox<Model, Msg, Cmd, Eff>(
    initialModel = Model.Initial,
    initialCmd = setOf(),
    subscriptions = listOf(program)
) {

    override fun update(msg: Msg, model: Model): UpdateResult = when (msg) {
        is Msg.Ui.OnOrderClicked -> onOrderClicked(model, msg)
        is Msg.Ui.OnSortSelected -> onSortSelected(model, msg)
        is Msg.Ui.OnCheckboxClicked -> onCheckboxClicked(model, msg)
        is Msg.Ui.OnDefaultBtnClicked -> onDefaultBtnClicked(model, msg)
    }

    private fun onOrderClicked(model: Model, msg: Msg.Ui.OnOrderClicked): UpdateResult =
        ElmUpdate(model, commands = setOf(Cmd.UpdateOrderState(msg.value)))

    private fun onSortSelected(model: Model, msg: Msg.Ui.OnSortSelected): UpdateResult =
        ElmUpdate(model, commands = setOf(Cmd.UpdateSortState(msg.value)))

    private fun onCheckboxClicked(model: Model, msg: Msg.Ui.OnCheckboxClicked): UpdateResult =
        ElmUpdate(model, commands = setOf(Cmd.UpdateCheckboxState(msg.value)))

    private fun onDefaultBtnClicked(model: Model, msg: Msg.Ui.OnDefaultBtnClicked): UpdateResult =
        ElmUpdate(model, commands = setOf(Cmd.SetDefaultSortState(msg.key)))
}