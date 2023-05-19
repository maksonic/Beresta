package ru.maksonic.beresta.feature.notes.folders.core

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.get
import org.koin.androidx.compose.koinViewModel
import ru.maksonic.beresta.feature.notes.folders.api.ui.FoldersListApi
import ru.maksonic.beresta.feature.notes.folders.api.ui.FoldersSharedUiState
import ru.maksonic.beresta.feature.notes.folders.api.ui.NoteFolderUi
import ru.maksonic.beresta.feature.notes.folders.core.add_folder_dialog.ui.FolderDialogContent
import ru.maksonic.beresta.feature.notes.folders.core.chips_row_widget.ChipsWidgetContent
import ru.maksonic.beresta.feature.notes.folders.core.screen.core.FoldersScreenSandbox
import ru.maksonic.beresta.feature.notes.folders.core.screen.ui.HandleUiEffects
import ru.maksonic.beresta.feature.notes.folders.core.screen.ui.NotesFoldersScreenContent
import ru.maksonic.beresta.feature.top_bar_counter.api.TopBarCounterApi
import ru.maksonic.beresta.navigation.router.router.NotesFoldersScreenRouter

/**
 * @Author maksonic on 01.05.2023
 */
class FoldersListCore : FoldersListApi.Ui {

    override val sharedUiState = FoldersSharedUiState.Initial

    @Composable
    override fun Screen(router: NotesFoldersScreenRouter) {
        ScreenContainer(router = router)
    }

    @Composable
    override fun ChipsWidget(
        chips: NoteFolderUi.Collection,
        onChipClicked: (id: Long) -> Unit,
        currentSelectedChipId: Long,
        modifier: Modifier
    ) {
        ChipsWidgetContent(
            chips = chips,
            onChipClicked = onChipClicked,
            currentSelectedChipId = currentSelectedChipId,
            modifier = modifier
        )
    }

    @Composable
    override fun FolderCreationDialog() {
        FolderDialogContent(sharedUiState = sharedUiState)
    }

    @Composable
    private fun ScreenContainer(
        api: FoldersListApi.Ui = get(),
        topBarCounterFeatureApi: TopBarCounterApi.Ui = get(),
        sandbox: FoldersScreenSandbox = koinViewModel(),
        router: NotesFoldersScreenRouter
    ) {
        val model = sandbox.model.collectAsStateWithLifecycle()

        HandleUiEffects(
            effects = sandbox.effects,
            router = router,
            sharedUiState = api.sharedUiState
        )

        NotesFoldersScreenContent(
            model = model.value,
            send = sandbox::send,
            notesFoldersFeatureApi = api,
            topBarCounterApi = topBarCounterFeatureApi,
        )
    }
}