package ru.maksonic.beresta.screen.settings.appearance.core

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import ru.maksonic.beresta.common.ui_theme.AppAnimationVelocity
import ru.maksonic.beresta.feature.notes_list.ui.api.card.NoteCardShapeUi
import ru.maksonic.beresta.platform.elm.core.ElmBaseModel
import ru.maksonic.beresta.platform.elm.core.ElmCommand
import ru.maksonic.beresta.platform.elm.core.ElmEffect
import ru.maksonic.beresta.platform.elm.core.ElmMessage
import ru.maksonic.beresta.platform.elm.core.ElmModel

/**
 * @Author maksonic on 07.07.2023
 */
enum class ModalSheetContent {
    NOTHING, NOTE_CARD_LINES_PICKER, ANIMATIONS_VELOCITY_PICKER
}

@Stable
@Immutable
data class ModalSheet(
    val isVisible: Boolean,
    val skipPartiallyExpanded: Boolean,
    val content: ModalSheetContent
) {
    companion object {
        val Initial = ModalSheet(
            isVisible = false,
            skipPartiallyExpanded = true,
            content = ModalSheetContent.NOTHING
        )
    }
}

@Stable
@Immutable
data class Model(
    val base: ElmBaseModel,
    val modalSheet: ModalSheet,
    val cardDate: String,
) : ElmModel {
    companion object {
        val Initial = Model(
            base = ElmBaseModel.Initial,
            modalSheet = ModalSheet.Initial,
            cardDate = ""
        )
    }
}

sealed class Msg : ElmMessage {
    sealed class Ui : Msg() {
        data object OnTopBarBackPressed : Ui()
        data class OnCardShapeClicked(val shape: NoteCardShapeUi) : Ui()
        data class OnCardElevationClicked(val isEnabled: Boolean) : Ui()
        data class OnCardWallpaperClicked(val isEnabled: Boolean) : Ui()
        data object OnModalSheetAcceptClicked : Ui()
        data object OnNoteLinesCountClicked : Ui()
        data class OnCardColorMarkerVisibilityClicked(val isVisible: Boolean) : Ui()
        data object OnModalSheetLinesPickerDefaultClicked : Ui()
        data object OnAnimationsVelocityClicked : Ui()
        data object OnModalSheetAnimationsVelocityDefaultClicked : Ui()

    }

    sealed class Inner : Msg() {
        data class FetchedCardDate(val date: String) : Inner()
        data object HiddenModalBottomSheet : Inner()
        data class UpdatedNoteTitleMaxLines(val value: Int) : Inner()
        data class UpdatedNoteMessageMaxLines(val value: Int) : Inner()
        data class UpdatedAnimationsVelocity(val key: AppAnimationVelocity.Key) : Inner()
    }
}

sealed class Cmd : ElmCommand {
    data object FetchCardDate : Cmd()
    data class UpdateNoteCardShape(val shape: NoteCardShapeUi) : Cmd()
    data class UpdateNoteCardElevation(val isEnabled: Boolean) : Cmd()
    data class UpdateNoteCardWallpaper(val isEnabled: Boolean) : Cmd()
    data class UpdatedNoteCardTitleMaxLines(val value: Int) : Cmd()
    data class UpdatedNoteCardMessageMaxLines(val value: Int) : Cmd()
    data class UpdatedNoteCardColorMarkerVisibility(val isVisible: Boolean) : Cmd()
    data class UpdateAnimationsVelocity(val key: AppAnimationVelocity.Key) : Cmd()
    data object ResetNoteCardLinesByDefault : Cmd()

}

sealed class Eff : ElmEffect {
    data object NavigateBack : Eff()
    data object HideModalSheet : Eff()
}