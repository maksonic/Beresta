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
import ru.maksonic.beresta.feature.notes_list.ui.api.NoteImageUi
import ru.maksonic.beresta.feature.notes_list.ui.api.NoteUi
import ru.maksonic.beresta.feature.notes_list.ui.api.NoteUiMapper
import ru.maksonic.beresta.feature.notes_list.ui.api.Style
import ru.maksonic.beresta.feature.notes_list.ui.api.isDefaultId
import ru.maksonic.beresta.feature.tags_list.domain.FetchNoteTagsUseCase
import ru.maksonic.beresta.feature.tags_list.ui.api.NoteTagUi
import ru.maksonic.beresta.feature.tags_list.ui.api.TagUiMapper
import ru.maksonic.beresta.feature.wallpaper_picker.domain.WallpaperParams
import ru.maksonic.beresta.feature.wallpaper_picker.domain.WallpaperType
import ru.maksonic.beresta.feature.wallpaper_picker.domain.usecase.FindWallpaperByParamsUseCase
import ru.maksonic.beresta.feature.wallpaper_picker.domain.wallpaper.BaseWallpaper
import ru.maksonic.beresta.navigation.router.core.AbstractNavigator
import ru.maksonic.beresta.navigation.router.core.Destination
import ru.maksonic.beresta.platform.elm.core.ElmProgram
import java.time.LocalDateTime

/**
 * @Author maksonic on 26.04.2023
 */
data class ProgramProxy(
    val fetchNoteByIdUseCase: FetchNoteByIdUseCase,
    val fetchNoteTagsUseCase: FetchNoteTagsUseCase,
    val fetchFoldersUseCase: FetchFoldersUseCase,
    val fetchMarkerColorsUseCase: FetchMarkerColorsUseCase<ColorContainer>,
    val findWallpaperByParamsUseCase: FindWallpaperByParamsUseCase<Color>,
    val foldersChipsRowUiApi: FoldersChipsRowUiApi.CurrentSelectedFolderStore,
    val notesInteractor: NotesInteractor,
)

data class MapperStore(
    val note: NoteUiMapper,
    val tag: TagUiMapper,
    val folder: FolderUiMapper,
)

val dataImages = listOf(
    R.drawable.wp_001,
    R.drawable.wp_002,
    R.drawable.wp_003,
    R.drawable.wp_004,
    R.drawable.wp_005,
    R.drawable.wp_006,
    R.drawable.wp_007,
    R.drawable.wp_008,
    R.drawable.wp_009,
    R.drawable.wp_010,
    R.drawable.wp_011,
    R.drawable.wp_012,
    R.drawable.wp_013,
).map { NoteImageUi(it.toLong(), it) }

class EditNoteProgram(
    private val proxy: ProgramProxy,
    private val mapper: MapperStore,
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
        val markerColorsList = proxy.fetchMarkerColorsUseCase()

        runCatching {
            combine(
                proxy.fetchNoteByIdUseCase(args.second),
                proxy.fetchNoteTagsUseCase()
            ) { noteDomain, tagsDomain ->
                val tags = tagsDomain.filter { tag -> noteDomain.tagsIds.any { tag.id == it } }
                val tagsUi = NoteTagUi.Collection(mapper.tag.mapListTo(tags))

                val note = mapper.note.mapTo(noteDomain).copy(tags = tagsUi, images = dataImages)
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
        proxy.fetchFoldersUseCase().collect { foldersDomain ->
            val folders = mapper.folder.mapListTo(foldersDomain).filter { !it.isStickyToStart }

            consumer(Msg.Inner.FetchedFoldersSuccess(folders))
        }
    }.onFailure {}

    private suspend fun saveOrUpdateNote(note: NoteUi, wallpaper: BaseWallpaper<Color>) {
        val currentRawTime = LocalDateTime.now()
        val noteDomain = mapper.note.mapFrom(note.addWallpaperParams(wallpaper))
        val pinTime = if (note.style.isPinned) currentRawTime else null

        with(proxy.notesInteractor) {
            if (note.isDefaultId()) {
                proxy.foldersChipsRowUiApi.updateId(note.folderId)
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
        proxy.notesInteractor.update(mapper.note.mapFrom(note))

    private fun fetchWallpaperByStyle(noteStyle: Style): BaseWallpaper<Color> = with(noteStyle) {
        proxy.findWallpaperByParamsUseCase(
            WallpaperParams(
                type = WallpaperType.idToType(wallpaperTypeId),
                id = wallpaperId,
                tintColorId = wallpaperTintId,
                tintColorAlpha = wallpaperTintAlpha,
                backgroundColorId = wallpaperBackgroundColorId,
                backgroundColorAlpha = wallpaperBackgroundColorAlpha,
                isTextureStyle = isTextureStyle
            )
        )
    }

    private fun fetchWallpaper(wallpaper: BaseWallpaper<Color>, consumer: (Msg) -> Unit) {
        val data = proxy.findWallpaperByParamsUseCase(wallpaper.getParams())
        consumer(Msg.Inner.FetchedNoteWallpaperResult(data))
    }
}