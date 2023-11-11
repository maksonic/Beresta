package ru.maksonic.beresta.feature.ui.edit_note.core

import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import ru.maksonic.beresta.common.ui_theme.colors.ColorContainer
import ru.maksonic.beresta.feature.folders_list.domain.usecase.FetchFoldersUseCase
import ru.maksonic.beresta.feature.folders_list.ui.api.FolderUiMapper
import ru.maksonic.beresta.feature.folders_list.ui.api.FoldersChipsRowUiApi
import ru.maksonic.beresta.feature.marker_color_picker.domain.FetchMarkerColorsUseCase
import ru.maksonic.beresta.feature.marker_color_picker.ui.api.MarkerPickerUiState
import ru.maksonic.beresta.feature.notes_list.domain.list.NotesInteractor
import ru.maksonic.beresta.feature.notes_list.domain.list.usecase.FetchNoteByIdUseCase
import ru.maksonic.beresta.feature.notes_list.ui.api.NoteUi
import ru.maksonic.beresta.feature.notes_list.ui.api.NoteUiMapper
import ru.maksonic.beresta.feature.notes_list.ui.api.Style
import ru.maksonic.beresta.feature.notes_list.ui.api.isDefaultId
import ru.maksonic.beresta.feature.tags_list.domain.FetchNoteTagsUseCase
import ru.maksonic.beresta.feature.tags_list.ui.api.NoteTagUi
import ru.maksonic.beresta.feature.tags_list.ui.api.TagUiMapper
import ru.maksonic.beresta.feature.wallpaper_picker.domain.WallpaperParams
import ru.maksonic.beresta.feature.wallpaper_picker.domain.WallpaperRepository
import ru.maksonic.beresta.feature.wallpaper_picker.domain.WallpaperType
import ru.maksonic.beresta.feature.wallpaper_picker.domain.wallpaper.BaseWallpaper
import ru.maksonic.beresta.navigation.router.core.AbstractNavigator
import ru.maksonic.beresta.navigation.router.core.Destination
import ru.maksonic.beresta.platform.elm.core.ElmProgram
import java.time.LocalDateTime

/**
 * @Author maksonic on 26.04.2023
 */
class EditNoteProgram(
    private val fetchNoteByIdUseCase: FetchNoteByIdUseCase,
    private val fetchNoteTagsUseCase: FetchNoteTagsUseCase,
    private val fetchFoldersUseCase: FetchFoldersUseCase,
    private val fetchMarkerColorsUseCase: FetchMarkerColorsUseCase<ColorContainer>,
    private val wallpaperRepository: WallpaperRepository<Color>,
    private val foldersChipsRowUiApi: FoldersChipsRowUiApi.CurrentSelectedFolderStore,
    private val notesInteractor: NotesInteractor,
    private val mapper: NoteUiMapper,
    private val tagUiMapper: TagUiMapper,
    private val foldersMapper: FolderUiMapper,
    private val navigator: AbstractNavigator,
) : ElmProgram<Msg, Cmd> {

    override suspend fun executeProgram(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            is Cmd.FetchNote -> fetchNote(consumer)
            is Cmd.FetchFolders -> fetchFolders(consumer)
            is Cmd.SaveNote -> saveOrUpdateNote(cmd.note, cmd.wallpaper)
            is Cmd.UpdateNoteMarkerColor -> updateMarkerColorId(cmd.note)
            is Cmd.FetchWallpaper -> fetchWallpaper(cmd.wallpaper, consumer)
        }
    }

    private suspend fun fetchNote(consumer: (Msg) -> Unit) {
        val args = navigator.getNoteEditorArgs(Destination.EditNote.passedKeysList)
        val markerColorsList = fetchMarkerColorsUseCase()

        runCatching {
            combine(
                fetchNoteByIdUseCase(args.second),
                fetchNoteTagsUseCase()
            ) { noteDomain, tagsDomain ->
                val tags = tagsDomain.filter { tag -> noteDomain.tagsIds.any { tag.id == it } }
                val tagsUi = NoteTagUi.Collection(tagUiMapper.mapListTo(tags))
                val note = mapper.mapTo(noteDomain).copy(tags = tagsUi)
                val markerState = MarkerPickerUiState(
                    currentSelectedColorId = note.style.markerColorId,
                    isVisibleDialog = false,
                    colors = markerColorsList
                )

                consumer(
                    Msg.Inner.FetchedPassedNoteResult(
                        isHidden = args.first,
                        note = note,
                        markerState = markerState,
                        wallpaper = fetchWallpaperByStyle(note.style)
                    )
                )
            }.collect()

        }.onFailure {
            consumer(
                Msg.Inner.FetchedPassedNoteResult(
                    isHidden = args.first,
                    note = NoteUi.Default,
                    markerState = MarkerPickerUiState.Initial.copy(colors = markerColorsList),
                    wallpaper = BaseWallpaper.empty()
                )
            )
        }
    }

    private suspend fun fetchFolders(consumer: (Msg) -> Unit) = runCatching {
        fetchFoldersUseCase().collect { foldersDomain ->
            val folders = foldersMapper.mapListTo(foldersDomain).filter { !it.isStickyToStart }

            consumer(Msg.Inner.FetchedFoldersSuccess(folders))
        }
    }.onFailure {}

    private suspend fun saveOrUpdateNote(note: NoteUi, wallpaper: BaseWallpaper<Color>) {
        val currentRawTime = LocalDateTime.now()
        val noteDomain = mapper.mapFrom(note.addWallpaperParams(wallpaper))
        val pinTime = if (note.style.isPinned) currentRawTime else null

        with(notesInteractor) {
            if (note.isDefaultId()) {
                foldersChipsRowUiApi.updateId(note.folderId)
                add(noteDomain.copy(dateCreationRaw = currentRawTime, pinTime = pinTime))
            } else {
                update(
                    noteDomain.copy(
                        dateCreationRaw = note.dateCreationRaw,
                        dateLastUpdateRaw = currentRawTime,
                        pinTime = pinTime
                    )
                )
            }
        }
    }

    private suspend fun updateMarkerColorId(note: NoteUi) =
        notesInteractor.update(mapper.mapFrom(note))

    private fun fetchWallpaperByStyle(noteStyle: Style): BaseWallpaper<Color> = with(noteStyle) {
        wallpaperRepository.findWallpaper(
            WallpaperParams(
                type = WallpaperType.idToType(wallpaperTypeId),
                id = wallpaperId,
                tintColorId = wallpaperTintId,
                backgroundColorId = wallpaperBackgroundColorId,
                isTextureStyle = isTextureStyle
            )
        )
    }

    private fun fetchWallpaper(wallpaper: BaseWallpaper<Color>, consumer: (Msg) -> Unit) {
        val data = wallpaperRepository.findWallpaper(wallpaper.getParams())
        consumer(Msg.Inner.FetchedNoteWallpaperResult(data))
    }
}