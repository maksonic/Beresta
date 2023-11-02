package ru.maksonic.beresta.screen.trash.notes.core

import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.delay
import ru.maksonic.beresta.common.core.ext.RETRY_REQUEST_DELAY
import ru.maksonic.beresta.common.ui_theme.colors.ColorContainer
import ru.maksonic.beresta.feature.marker_color_picker.domain.FindMarkerColorByIdUseCase
import ru.maksonic.beresta.feature.notes_list.domain.list.NotesInteractor
import ru.maksonic.beresta.feature.notes_list.domain.list.usecase.FetchNotesWithoutFolderTrashListUseCase
import ru.maksonic.beresta.feature.notes_list.ui.api.NoteUi
import ru.maksonic.beresta.feature.notes_list.ui.api.NoteUiMapper
import ru.maksonic.beresta.feature.notes_list.ui.api.Style
import ru.maksonic.beresta.feature.wallpaper_picker.domain.WallpaperParams
import ru.maksonic.beresta.feature.wallpaper_picker.domain.WallpaperType
import ru.maksonic.beresta.feature.wallpaper_picker.domain.usecase.FindWallpaperByParamsUseCase
import ru.maksonic.beresta.feature.wallpaper_picker.domain.wallpaper.BaseWallpaper
import ru.maksonic.beresta.platform.elm.core.ElmProgram

/**
 * @Author maksonic on 30.05.2023
 */
class NotesTrashProgram(
    private val fetchRemovedNotesUseCase: FetchNotesWithoutFolderTrashListUseCase,
    private val findMarkerColorByIdUseCase: FindMarkerColorByIdUseCase<ColorContainer>,
    private val findWallpaperByParamsUseCase: FindWallpaperByParamsUseCase<Color>,
    private val notesInteractor: NotesInteractor,
    private val notesMapper: NoteUiMapper
) : ElmProgram<Msg, Cmd> {
    override suspend fun executeProgram(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            is Cmd.FetchData -> fetchData(consumer)
            is Cmd.RetryFetchData -> retryFetchData(consumer)
            is Cmd.RestoreNotes -> restoreNotes(cmd.notes)
            is Cmd.DeleteNotes -> deleteNotes(cmd.notes)
        }
    }

    private suspend fun fetchData(consumer: (Msg) -> Unit) = runCatching {
        fetchRemovedNotesUseCase().collect { notesDomain ->
            val notes = notesMapper.mapListTo(notesDomain).map { note ->
                val markerColor = findMarkerColorByIdUseCase(note.style.markerColorId)
                val wallpaper = fetchWallpaperByStyle(note.style)

                note.copy(style = note.style.copy(markerColor = markerColor), wallpaper = wallpaper)
            }

            consumer(Msg.Inner.FetchedDataResult(notes))
        }
    }.onFailure { consumer(Msg.Inner.FetchedDataResult(emptyList())) }

    private suspend fun retryFetchData(consumer: (Msg) -> Unit) {
        delay(RETRY_REQUEST_DELAY)
        fetchData(consumer)
    }

    private suspend fun restoreNotes(notes: List<NoteUi>) {
        val restored = notesMapper.mapListFrom(notes).map { note ->
            note.copy(isMovedToTrash = false, dateMovedToTrashRaw = null)
        }
        notesInteractor.updateList(restored)
    }

    private suspend fun deleteNotes(notes: List<NoteUi>) =
        notesInteractor.deleteList(notesMapper.mapListFrom(notes))

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
