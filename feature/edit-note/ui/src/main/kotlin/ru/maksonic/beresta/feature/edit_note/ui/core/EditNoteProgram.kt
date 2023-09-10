package ru.maksonic.beresta.feature.edit_note.ui.core

import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import ru.maksonic.beresta.elm.core.ElmProgram
import ru.maksonic.beresta.feature.folders_chips.api.domain.FoldersInteractor
import ru.maksonic.beresta.feature.folders_chips.api.ui.FolderUi
import ru.maksonic.beresta.feature.folders_chips.api.ui.FolderUiMapper
import ru.maksonic.beresta.feature.folders_chips.api.ui.StickyFoldersTitleFormatter
import ru.maksonic.beresta.feature.notes.api.domain.NotesInteractor
import ru.maksonic.beresta.feature.notes.api.ui.NoteUi
import ru.maksonic.beresta.feature.notes.api.ui.NoteUiMapper
import ru.maksonic.beresta.feature.notes.api.ui.isDefaultId
import ru.maksonic.beresta.language_engine.shell.LanguageEngineApi
import ru.maksonic.beresta.navigation.router.AbstractNavigator
import ru.maksonic.beresta.navigation.router.Destination
import java.time.LocalDateTime

/**
 * @Author maksonic on 26.04.2023
 */
class EditNoteProgram(
    private val interactor: NotesInteractor,
    private val foldersInteractor: FoldersInteractor,
    private val mapper: NoteUiMapper,
    private val foldersMapper: FolderUiMapper,
    private val navigator: AbstractNavigator,
    private val appLanguageEngineApi: LanguageEngineApi,
    private val stickyFoldersTitleFormatter: StickyFoldersTitleFormatter,
) : ElmProgram<Msg, Cmd> {

    override suspend fun executeProgram(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            is Cmd.FetchNote -> fetchNote(consumer)
            is Cmd.FetchFolders -> fetchFolders(consumer)
            is Cmd.SaveNote -> saveOrUpdateNote(cmd.note)
            is Cmd.UpdatePinnedNoteInCache -> updateNotePinState(cmd.note)
        }
    }

    private suspend fun fetchNote(consumer: (Msg) -> Unit) {
        val args = navigator.getNoteEditorArgs(Destination.EditNote.passedKeysList)

        runCatching {
            interactor.fetchById(args.second).collect { noteDomain ->
                combine(
                    foldersInteractor.fetchById(noteDomain.folderId),
                    appLanguageEngineApi.current
                ) { folderDomain, lang ->
                    val note = mapper.mapTo(noteDomain)
                    val folderRaw = foldersMapper.mapTo(folderDomain)
                    val updatedTitle = stickyFoldersTitleFormatter.format(folderRaw, lang)
                    val folder = folderRaw.copy(title = updatedTitle)
                    consumer(Msg.Inner.FetchedPassedNoteResult(args.first, note, folder))
                }.collect()
            }

        }.onFailure {
            consumer(Msg.Inner.FetchedPassedNoteResult(args.first, NoteUi.Default, FolderUi.Empty))
        }
    }

    private suspend fun fetchFolders(consumer: (Msg) -> Unit) = runCatching {
        combine(
            foldersInteractor.fetchList(),
            appLanguageEngineApi.current
        ) { foldersDomain, lang ->
            val folders = foldersMapper.mapListTo(foldersDomain)
                .filter { !it.isStickyToStart }
                .map { it.copy(title = stickyFoldersTitleFormatter.format(it, lang)) }

            consumer(Msg.Inner.FetchedFoldersResult(folders))
        }.collect()

    }.onFailure {}

    private suspend fun saveOrUpdateNote(note: NoteUi) {
        val currentRawTime = LocalDateTime.now()
        val noteDomain = mapper.mapFrom(note)

        interactor.let {
            if (note.isDefaultId())
                it.add(noteDomain.copy(dateCreationRaw = currentRawTime))
            else
                it.update(
                    noteDomain.copy(
                        dateCreationRaw = note.dateCreationRaw,
                        dateLastUpdateRaw = currentRawTime
                    )
                )
        }
    }

    private suspend fun updateNotePinState(note: NoteUi) = interactor.update(mapper.mapFrom(note))
}