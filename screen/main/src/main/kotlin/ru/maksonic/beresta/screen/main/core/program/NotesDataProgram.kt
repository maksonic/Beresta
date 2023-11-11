package ru.maksonic.beresta.screen.main.core.program

import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import ru.maksonic.beresta.common.ui_theme.colors.ColorContainer
import ru.maksonic.beresta.feature.hidden_notes_dialog.domain.usecase.FetchHiddenNotesPinStatusUseCase
import ru.maksonic.beresta.feature.marker_color_picker.domain.FindMarkerColorByIdUseCase
import ru.maksonic.beresta.feature.notes_list.domain.list.NotesInteractor
import ru.maksonic.beresta.feature.notes_list.domain.list.usecase.FetchNotesUseCase
import ru.maksonic.beresta.feature.notes_list.ui.api.NoteUi
import ru.maksonic.beresta.feature.notes_list.ui.api.NoteUiMapper
import ru.maksonic.beresta.feature.notes_list.ui.api.Style
import ru.maksonic.beresta.feature.tags_list.domain.FetchNoteTagsUseCase
import ru.maksonic.beresta.feature.tags_list.ui.api.NoteTagUi
import ru.maksonic.beresta.feature.tags_list.ui.api.TagUiMapper
import ru.maksonic.beresta.feature.wallpaper_picker.domain.WallpaperParams
import ru.maksonic.beresta.feature.wallpaper_picker.domain.WallpaperType
import ru.maksonic.beresta.feature.wallpaper_picker.domain.usecase.FindWallpaperByParamsUseCase
import ru.maksonic.beresta.feature.wallpaper_picker.domain.wallpaper.BaseWallpaper
import ru.maksonic.beresta.platform.elm.core.ElmProgram
import ru.maksonic.beresta.screen.main.core.Cmd
import ru.maksonic.beresta.screen.main.core.Msg
import java.time.LocalDateTime

/**
 * @Author maksonic on 15.10.2023
 */
class NotesDataProgram(
    private val fetchNotesUseCase: FetchNotesUseCase,
    private val fetchHiddenNotesPinStatusUseCase: FetchHiddenNotesPinStatusUseCase,
    private val fetchNoteTagsUseCase: FetchNoteTagsUseCase,
    private val findMarkerColorByIdUseCase: FindMarkerColorByIdUseCase<ColorContainer>,
    private val findWallpaperByParamsUseCase: FindWallpaperByParamsUseCase<Color>,
    private val notesInteractor: NotesInteractor,
    private val mapper: NoteUiMapper,
    private val tagUiMapper: TagUiMapper,
    private val ioDispatcher: CoroutineDispatcher
) : ElmProgram<Msg, Cmd> {

    override suspend fun executeProgram(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            is Cmd.FetchNotesList -> fetchNotesList(consumer)
            is Cmd.RemoveSelectedNotes -> moveSelectedNotesToTrash(cmd.notes)
            is Cmd.UpdatePinnedNotes -> updatePinnedNotes(cmd.pinned)
            is Cmd.UndoRemoveNotes -> undoRemovedFromTrash(cmd.notes)
            is Cmd.TryHideSelectedNotes -> tryMoveNotesToHiddenFolder(cmd.notes, consumer)
            is Cmd.HideSelectedNotes -> moveNotesToHiddenFolder(cmd.notes)
            else -> {}
        }
    }

    private suspend fun fetchNotesList(consumer: (Msg) -> Unit) = runCatching {
        combine(fetchNotesUseCase(), fetchNoteTagsUseCase()) { notesDomain, tagsDomain ->
            val notes = mapper.mapListTo(notesDomain).map { note ->
                val markerColor = findMarkerColorByIdUseCase(note.style.markerColorId)
                val tags = tagsDomain.filter { tag -> note.tagsIds.any { tag.id == it } }
                val wallpaper = fetchWallpaperByStyle(note.style)

                note.copy(
                    tags = NoteTagUi.Collection(tagUiMapper.mapListTo(tags)),
                    style = note.style.copy(markerColor = markerColor),
                    wallpaper = wallpaper
                )
            }
            consumer(Msg.Inner.FetchedNotesSuccess(NoteUi.Collection(notes)))
        }.collect()

    }.onFailure { throwable ->
        consumer(Msg.Inner.FetchedNotesFail(throwable.localizedMessage ?: "Failure"))
    }

    private suspend fun moveSelectedNotesToTrash(notes: List<NoteUi>) {
        val notesUi = notes.map { note ->
            note.copy(
                isMovedToTrash = true,
                folderId = 2L,
                dateMovedToTrashRaw = LocalDateTime.now()
            )
        }
        val notesDomain = mapper.mapListFrom(notesUi)
        notesInteractor.updateList(notesDomain)
    }

    private suspend fun undoRemovedFromTrash(notes: List<NoteUi>) {
        val restored = mapper.mapListFrom(notes.map { it.copy(dateMovedToTrashRaw = null) })
        notesInteractor.updateList(restored)
    }

    private suspend fun updatePinnedNotes(notes: List<NoteUi>) {
        val currentDate = LocalDateTime.now()
        val isSelectedContainsUnpinned = notes.map { !it.style.isPinned }.contains(true)
        val selected = notes.map { note ->
            note.copy(
                dateCreationRaw = note.dateCreationRaw,
                pinTime = if (isSelectedContainsUnpinned) currentDate else null,
                style = note.style.copy(isPinned = isSelectedContainsUnpinned)
            )
        }
        val notesDomain = mapper.mapListFrom(selected)
        notesInteractor.updateList(notesDomain)
    }

    private fun tryMoveNotesToHiddenFolder(notes: List<NoteUi>, consumer: (Msg) -> Unit) {
        CoroutineScope(ioDispatcher).launch {
            fetchHiddenNotesPinStatusUseCase().cancellable().collectLatest { isValid ->
                if (isValid) {
                    moveNotesToHiddenFolder(notes)
                } else {
                    consumer(Msg.Inner.UpdatedHiddenNotesDialogVisibility(true))
                }
                this.coroutineContext.cancel()
            }
        }
    }

    private suspend fun moveNotesToHiddenFolder(notes: List<NoteUi>) = runCatching {
        val notesDomain = mapper.mapListFrom(notes.toList()).map {
            it.copy(isHidden = true, isPinned = false, pinTime = null)
        }
        notesInteractor.updateList(notesDomain)
    }

    private fun fetchWallpaperByStyle(noteStyle: Style): BaseWallpaper<Color> = with(noteStyle) {
        findWallpaperByParamsUseCase(
            WallpaperParams(
                type = WallpaperType.idToType(wallpaperTypeId),
                id = wallpaperId,
                tintColorId = wallpaperTintId,
                backgroundColorId = wallpaperBackgroundColorId,
                isTextureStyle = isTextureStyle
            )
        )
    }
}
