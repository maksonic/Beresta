package ru.maksonic.beresta.screen.main

import androidx.compose.material3.ExperimentalMaterial3Api
import ru.maksonic.beresta.elm.Sandbox
import ru.maksonic.beresta.elm.UpdatedModel
import ru.maksonic.beresta.feature.notes.list.api.ui.MainBottomBarState

/**
 * @Author maksonic on 16.01.2023
 */
private typealias UpdateResult = UpdatedModel<Model, Set<Cmd>, Set<Eff>>

@OptIn(ExperimentalMaterial3Api::class)
class MainSandbox(
    mainProgram: MainProgram
) : Sandbox<Model, Msg, Cmd, Eff>(
    initialModel = Model.Initial,
    initialCmd = setOf(Cmd.FetchFoldersChips, Cmd.FetchCurrentAppLang),
    subscriptions = listOf(mainProgram)
) {
    override fun update(msg: Msg, model: Model): UpdateResult = when (msg) {
        is Msg.Ui.OnCreateNewNoteClicked -> onAddNoteClicked(model)
        is Msg.Ui.OnShareSelectedNotes -> onShareSelectedNotesClicked(model)
        is Msg.Ui.OnBottomBarSettingsClicked -> onSettingsClicked(model)
        is Msg.Ui.OnBottomBarTrashClicked -> onTrashClicked(model)
        is Msg.Ui.OnBottomBarFoldersClicked -> onFoldersListClicked(model)
        is Msg.Ui.OnBottomBarSortNotesByClicked -> onSortByClicked(model)
        is Msg.Ui.OnSwitchViewClicked -> onSwitchGridCountClicked(model)
        is Msg.Inner.UpdateBottomPanelState -> updatedBottomBarState(model, msg)
        is Msg.Inner.FetchedChipsResult -> fetchedChipsResult(model, msg)
        is Msg.Ui.OnChipFilterClicked -> onFilterChipClicked(model, msg)
        is Msg.Inner.UpdateCurrentSelectedFolder -> updatedCurrentSelectedFolder(model, msg)
        is Msg.Inner.UpdatedModalSheetState -> updatedModalSheetState(model, msg)
        is Msg.Ui.OnHideModalBottomSheet -> onHideModalBottomSheet(model)
        is Msg.Inner.FetchedCurrentAppLang -> fetchedAppLang(model, msg)
    }

    private fun onAddNoteClicked(model: Model): UpdateResult =
        UpdatedModel(model, effects = setOf(Eff.NavigateToAddNewNote))

    private fun onSettingsClicked(model: Model): UpdateResult =
        UpdatedModel(model, effects = setOf(Eff.NavigateToSettings))

    private fun onShareSelectedNotesClicked(model: Model): UpdateResult = UpdatedModel(model)

    private fun onTrashClicked(model: Model): UpdateResult =
        UpdatedModel(model, effects = setOf(Eff.NavigateToTrash))

    private fun onFoldersListClicked(model: Model): UpdateResult =
        UpdatedModel(
            model,
            effects = setOf(Eff.NavigateToFoldersList(model.currentSelectedFolderId))
        )

    private fun onSortByClicked(model: Model): UpdateResult =
        UpdatedModel(model.copy(isVisibleModalSheet = true))

    private fun onSwitchGridCountClicked(model: Model): UpdateResult {
        return UpdatedModel(model)
    }

    private fun updatedBottomBarState(
        model: Model,
        msg: Msg.Inner.UpdateBottomPanelState
    ): UpdateResult {
        val state =
            if (msg.isSelectionState) MainBottomBarState.SELECTION else MainBottomBarState.IDLE

        return UpdatedModel(model.copy(bottomBarState = state))
    }

    private fun fetchedChipsResult(model: Model, msg: Msg.Inner.FetchedChipsResult): UpdateResult {
        val initialSelectedChipId = msg.chips.find { it.isStickyToStart }?.id ?: 0

        return UpdatedModel(
            model.copy(
                base = model.base.copy(isLoading = false, isSuccessLoading = true),
                filters = model.filters.copy(msg.chips),
                currentSelectedFolderId = initialSelectedChipId
            ),
        )
    }

    private fun onFilterChipClicked(model: Model, msg: Msg.Ui.OnChipFilterClicked): UpdateResult =
        UpdatedModel(
            model = model.copy(currentSelectedFolderId = msg.id),
            effects = setOf(Eff.UpdateCurrentSelectedFolderInSharedState(msg.id))
        )

    private fun updatedCurrentSelectedFolder(
        model: Model,
        msg: Msg.Inner.UpdateCurrentSelectedFolder
    ): UpdateResult = UpdatedModel(model.copy(currentSelectedFolderId = msg.id))

    private fun updatedModalSheetState(
        model: Model,
        msg: Msg.Inner.UpdatedModalSheetState
    ): UpdateResult =
        UpdatedModel(model.copy(isVisibleModalSheet = msg.isVisible))

    private fun onHideModalBottomSheet(model: Model): UpdateResult =
        UpdatedModel(model, effects = setOf(Eff.HideModalSheet))

    private fun fetchedAppLang(model: Model, msg: Msg.Inner.FetchedCurrentAppLang): UpdateResult =
        UpdatedModel(model.copy(currentLang = msg.language))
}