package ru.maksonic.beresta.screen.hidden_notes.core.programs

import androidx.compose.ui.graphics.Color
import ru.maksonic.beresta.common.ui_theme.colors.ColorContainer
import ru.maksonic.beresta.feature.marker_color_picker.domain.FindMarkerColorByIdUseCase
import ru.maksonic.beresta.feature.notes_list.domain.list.NotesInteractor
import ru.maksonic.beresta.feature.notes_list.domain.list.usecase.FetchHiddenNotesUseCase
import ru.maksonic.beresta.feature.notes_list.ui.api.NoteUi
import ru.maksonic.beresta.feature.notes_list.ui.api.NoteUiMapper
import ru.maksonic.beresta.feature.notes_list.ui.api.Style
import ru.maksonic.beresta.feature.wallpaper_picker.domain.WallpaperParams
import ru.maksonic.beresta.feature.wallpaper_picker.domain.WallpaperType
import ru.maksonic.beresta.feature.wallpaper_picker.domain.usecase.FindWallpaperByParamsUseCase
import ru.maksonic.beresta.feature.wallpaper_picker.domain.wallpaper.BaseWallpaper
import ru.maksonic.beresta.platform.elm.core.ElmProgram
import ru.maksonic.beresta.screen.hidden_notes.core.Cmd
import ru.maksonic.beresta.screen.hidden_notes.core.Msg
import java.time.LocalDateTime

/**
 * @Author maksonic on 21.07.2023
 */
class HiddenNotesDataProgram(
    private val fetchHiddenNotesUseCase: FetchHiddenNotesUseCase,
    private val notesInteractor: NotesInteractor,
    private val findMarkerColorByIdUseCase: FindMarkerColorByIdUseCase<ColorContainer>,
    private val findWallpaperByParamsUseCase: FindWallpaperByParamsUseCase<Color>,
    private val mapper: NoteUiMapper
) : ElmProgram<Msg, Cmd> {

    override suspend fun executeProgram(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            is Cmd.FetchNotesData -> fetchNotesList(consumer)
            is Cmd.RemoveSelectedNotes -> moveSelectedNotesToTrash(cmd.notes)
            is Cmd.UpdatePinnedNotes -> updatePinnedNotes(cmd.pinned)
            is Cmd.UndoRemoveNotes -> undoRemovedFromTrash(cmd.notes)
            is Cmd.UnhideSelectedNotes -> unhideNotes(cmd.notes, consumer)
            else -> {}
        }
    }

    private suspend fun fetchNotesList(consumer: (Msg) -> Unit) = runCatching {
        fetchHiddenNotesUseCase().collect { notesDomain ->
            val notes = mapper.mapListTo(notesDomain).map { note ->
                val markerColor = findMarkerColorByIdUseCase(note.style.markerColorId)
                val wallpaper = fetchWallpaperByStyle(note.style)

                note.copy(style = note.style.copy(markerColor = markerColor), wallpaper = wallpaper)
            }
            consumer(Msg.Inner.FetchedNotesSuccess(NoteUi.Collection(notes)))
        }

    }.onFailure { throwable ->
        consumer(Msg.Inner.FetchedNotesFail(throwable.localizedMessage ?: "Fail"))
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
        val restored = mapper.mapListFrom(notes.map {
            it.copy(
                isHidden = true,
                isMovedToTrash = false,
                dateMovedToTrashRaw = null
            )
        })
        notesInteractor.updateList(restored)
    }

    private suspend fun updatePinnedNotes(notes: List<NoteUi>) {
        val currentDate = LocalDateTime.now()
        val isSelectedContainsUnpinned = notes.map { !it.style.isPinned }.contains(true)
        val selected = notes.map { note ->
            note.copy(
                pinTime = if (isSelectedContainsUnpinned) currentDate else null,
                dateCreationRaw = note.dateCreationRaw,
                style = note.style.copy(isPinned = isSelectedContainsUnpinned)
            )
        }
        val notesDomain = mapper.mapListFrom(selected)
        notesInteractor.updateList(notesDomain)
    }

    private suspend fun unhideNotes(notes: List<NoteUi>, consumer: (Msg) -> Unit) {
        val unhidden = mapper.mapListFrom(notes.toList())
            .map { it.copy(isPinned = false, pinTime = null, isHidden = false) }

        notesInteractor.updateList(unhidden)
        consumer(Msg.Inner.HiddenLoadingPlaceholder)
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