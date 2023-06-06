package ru.maksonic.beresta.feature.notes.folders.core

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject
import ru.maksonic.beresta.feature.notes.folders.api.ui.FoldersListApi
import ru.maksonic.beresta.feature.notes.folders.api.ui.FoldersSharedUiState
import ru.maksonic.beresta.feature.notes.folders.api.ui.NoteFolderUi
import ru.maksonic.beresta.feature.notes.folders.api.ui.StickyItemsTitleFormatter
import ru.maksonic.beresta.feature.notes.folders.core.add_folder_dialog.ui.FolderDialogContent
import ru.maksonic.beresta.feature.notes.folders.core.chips_row_widget.ChipsWidgetContainer
import ru.maksonic.beresta.feature.notes.folders.core.screen.core.FoldersScreenSandbox
import ru.maksonic.beresta.feature.notes.folders.core.screen.ui.FolderItemContent
import ru.maksonic.beresta.feature.notes.folders.core.screen.ui.HandleUiEffects
import ru.maksonic.beresta.feature.notes.folders.core.screen.ui.NotesFoldersScreenContent
import ru.maksonic.beresta.feature.notes.folders.core.screen.ui.widget.FoldersLoaderWidgetContent
import ru.maksonic.beresta.feature.notes.list.api.domain.DateFormatter
import ru.maksonic.beresta.feature.notes.list.api.ui.NotesListApi
import ru.maksonic.beresta.feature.top_bar_counter.api.TopBarCounterApi
import ru.maksonic.beresta.language_engine.shell.provider.AppLanguage
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
        isShowPlaceholder: Boolean,
        currentLanguage: AppLanguage
    ) {
        ChipsWidgetContainer(
            chips = chips,
            onChipClicked = onChipClicked,
            currentSelectedChipId = currentSelectedChipId,
            isShowPlaceholder = isShowPlaceholder,
            currentLanguage = currentLanguage,
        )
    }

    @Composable
    override fun FolderListItem(
        folder: NoteFolderUi,
        isSelected: Boolean,
        isCurrent: Boolean,
        onFolderClicked: (id: Long) -> Unit,
        onFolderLongPressed: (id: Long) -> Unit,
        isTrashPlacement: Boolean,
        currentAppLang: AppLanguage,
        formatter: DateFormatter,
        modifier: Modifier
    ) {
        FolderItemContent(
            folder = folder,
            isSelected = isSelected,
            isCurrent = isCurrent,
            onFolderClicked = onFolderClicked,
            onFolderLongPressed = onFolderLongPressed,
            isTrashPlacement = isTrashPlacement,
            currentAppLanguage = currentAppLang,
            formatter = formatter,
            modifier = modifier
        )
    }

    @Composable
    override fun FoldersLoaderWidget(modifier: Modifier) {
        FoldersLoaderWidgetContent(modifier)
    }

    @Composable
    override fun FolderCreationDialog() {
        FolderDialogContent(sharedUiState = sharedUiState)
    }

    @Composable
    private fun ScreenContainer(
        api: FoldersListApi.Ui = koinInject(),
        notesListApi: NotesListApi.Ui = koinInject(),
        topBarCounterFeatureApi: TopBarCounterApi.Ui = koinInject(),
        stickyItemsTitleFormatter: StickyItemsTitleFormatter = koinInject(),
        sandbox: FoldersScreenSandbox = koinViewModel(),
        router: NotesFoldersScreenRouter
    ) {
        val model = sandbox.model.collectAsStateWithLifecycle()

        HandleUiEffects(
            effects = sandbox.effects,
            router = router,
            sharedUiState = api.sharedUiState,
            notesListSharedState = notesListApi.sharedUiState,
        )

        NotesFoldersScreenContent(
            model = model.value,
            send = sandbox::send,
            notesFoldersFeatureApi = api,
            notesListApi = notesListApi,
            topBarCounterApi = topBarCounterFeatureApi,
            stickyItemsTitleFormatter = stickyItemsTitleFormatter,
        )
    }
}