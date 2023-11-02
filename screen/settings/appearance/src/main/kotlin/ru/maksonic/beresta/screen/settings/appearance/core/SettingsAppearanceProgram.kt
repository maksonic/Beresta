package ru.maksonic.beresta.screen.settings.appearance.core

import ru.maksonic.beresta.common.ui_theme.AppAnimationVelocity
import ru.maksonic.beresta.feature.app_lang.domain.formatter.DateFormatter
import ru.maksonic.beresta.feature.app_lang.domain.usecase.FetchAppLangUseCase
import ru.maksonic.beresta.feature.notes_list.domain.card.NoteCardInteractor
import ru.maksonic.beresta.feature.notes_list.ui.api.card.NoteCardElevationUi
import ru.maksonic.beresta.feature.notes_list.ui.api.card.NoteCardShapeUi
import ru.maksonic.beresta.feature.notes_list.ui.api.card.NoteCardStateMapper
import ru.maksonic.beresta.feature.notes_list.ui.api.card.NotesCardUiApi
import ru.maksonic.beresta.platform.core.ui.AnimationVelocity
import ru.maksonic.beresta.platform.elm.core.ElmProgram
import java.time.LocalDateTime

/**
 * @Author maksonic on 07.07.2023
 */
class SettingsAppearanceProgram(
    private val notesCardStateStoreUiApi: NotesCardUiApi.CardState,
    private val notesCardInteractor: NoteCardInteractor,
    private val cardStateMapper: NoteCardStateMapper,
    private val fetchAppLangUseCase: FetchAppLangUseCase,
    private val dateFormatter: DateFormatter,
    private val animationVelocity: AnimationVelocity,
) : ElmProgram<Msg, Cmd> {

    override suspend fun executeProgram(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            is Cmd.FetchCardDate -> fetchCardDate(consumer)
            is Cmd.UpdateNoteCardShape -> updateNoteCardShape(cmd.shape)
            is Cmd.UpdateNoteCardElevation -> updateNoteCardElevation(cmd.isEnabled)
            is Cmd.UpdateNoteCardWallpaper -> updateWallpaperVisibility(cmd.isEnabled)
            is Cmd.UpdatedNoteCardTitleMaxLines -> updateNoteCardTitleMaxLines(cmd.value)
            is Cmd.UpdatedNoteCardMessageMaxLines -> updateNoteCardMessageMaxLines(cmd.value)
            is Cmd.ResetNoteCardLinesByDefault -> resetNoteCardLinesByDefault()
            is Cmd.UpdateAnimationsVelocity -> updateAnimationVelocity(cmd.key)
            is Cmd.UpdatedNoteCardColorMarkerVisibility -> {
                updateColorMarkerVisibility(cmd.isVisible)
            }

        }
    }

    private suspend fun fetchCardDate(consumer: (Msg) -> Unit) {
        fetchAppLangUseCase().collect {
            val date = dateFormatter.fetchDateByLang(LocalDateTime.now(), it)
            consumer(Msg.Inner.FetchedCardDate(date))
        }
    }

    private suspend fun updateNoteCardShape(cardShape: NoteCardShapeUi) = notesCardStateStoreUiApi
        .updateShape(cardShape)
        .let { notesCardInteractor.setCardShape(cardStateMapper.mapUiShapeToDomain(cardShape)) }

    private suspend fun updateNoteCardElevation(isEnabled: Boolean) {
        val elevation = if (isEnabled) NoteCardElevationUi.DISABLED else NoteCardElevationUi.ENABLED

        notesCardStateStoreUiApi.updateElevation(elevation).let {
            notesCardInteractor.setCardElevation(cardStateMapper.mapUiElevationToDomain(elevation))
        }
    }

    private suspend fun updateWallpaperVisibility(isVisible: Boolean) = notesCardStateStoreUiApi
        .updateWallpaperVisibility(isVisible)
        .let { notesCardInteractor.setCardWallpaperVisibility(!isVisible) }

    private suspend fun updateNoteCardTitleMaxLines(count: Int) = notesCardStateStoreUiApi
        .updateMaxTitleLines(count)
        .let { notesCardInteractor.setCardTitleMaxLines(count) }

    private suspend fun updateNoteCardMessageMaxLines(count: Int) = notesCardStateStoreUiApi
        .updateMaxMessageLines(count)
        .let { notesCardInteractor.setCardMessageMaxLines(count) }

    private suspend fun resetNoteCardLinesByDefault() = notesCardStateStoreUiApi
        .resetNoteCardLinesByDefault()
        .let { notesCardInteractor.setByDefaultCardMaxLines() }

    private suspend fun updateAnimationVelocity(key: AppAnimationVelocity.Key) =
        animationVelocity.update(key)

    private suspend fun updateColorMarkerVisibility(isVisible: Boolean) = notesCardStateStoreUiApi
        .updateColorMarkerVisibility(isVisible)
        .let { notesCardInteractor.setCardColorMarkerVisibility(!isVisible) }
}