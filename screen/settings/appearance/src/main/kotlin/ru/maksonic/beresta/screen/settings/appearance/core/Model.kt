package ru.maksonic.beresta.screen.settings.appearance.core

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import ru.maksonic.beresta.elm.core.ElmBaseModel
import ru.maksonic.beresta.elm.core.ElmCommand
import ru.maksonic.beresta.elm.core.ElmEffect
import ru.maksonic.beresta.elm.core.ElmMessage
import ru.maksonic.beresta.elm.core.ElmModel
import ru.maksonic.beresta.feature.notes.api.ui.NoteCardShape
import ru.maksonic.beresta.language_engine.shell.provider.AppLanguage
import ru.maksonic.beresta.ui.theme.component.AppAnimationVelocity

/**
 * @Author maksonic on 07.07.2023
 */
enum class ModalSheetContent {
    NOTHING, NOTE_CARD_LINES_PICKER, ANIMATIONS_VELOCITY_PICKER
}

@OptIn(ExperimentalMaterial3Api::class)
@Stable
data class ModalSheet(
    val isVisible: Boolean,
    val state: SheetState,
    val content: ModalSheetContent,
) {
    companion object {
        val Initial = ModalSheet(
            isVisible = false,
            state = SheetState(
                initialValue = SheetValue.Hidden,
                skipPartiallyExpanded = true
            ),
            content = ModalSheetContent.NOTHING,
        )
    }
}

@Stable
@Immutable
data class Model(
    val base: ElmBaseModel,
    val modalSheet: ModalSheet,
    val currentLang: AppLanguage


) : ElmModel {
    companion object {
        val Initial = Model(
            base = ElmBaseModel.Initial,
            modalSheet = ModalSheet.Initial,
            currentLang = AppLanguage.RUSSIAN
        )
    }
}

sealed class Msg : ElmMessage {
    sealed class Ui : Msg() {
        object OnTopBarBackPressed : Ui()
        data class OnNoteCardShapeClicked(val shape: NoteCardShape) : Ui()
        data class OnNoteCardElevationClicked(val isEnabled: Boolean) : Ui()
        object OnModalSheetAcceptClicked : Ui()
        object OnNoteLinesCountClicked : Ui()
        object OnModalSheetLinesPickerDefaultClicked : Ui()
        object OnAnimationsVelocityClicked : Ui()
        object OnModalSheetAnimationsVelocityDefaultClicked : Ui()

    }

    sealed class Inner : Msg() {
        object HiddenModalBottomSheet : Inner()
        data class UpdatedNoteTitleMaxLines(val value: Int) : Inner()
        data class UpdatedNoteMessageMaxLines(val value: Int) : Inner()
        data class UpdatedAnimationsVelocity(val key: AppAnimationVelocity.Key) : Inner()
        data class FetchedAppLang(val lang: AppLanguage) : Inner()
    }
}

sealed class Cmd : ElmCommand {
    data class UpdateNoteCardShape(val shape: NoteCardShape) : Cmd()
    data class UpdateNoteCardElevation(val isEnabled: Boolean) : Cmd()
    data class UpdatedNoteCardTitleMaxLines(val value: Int) : Cmd()
    data class UpdatedNoteCardMessageMaxLines(val value: Int) : Cmd()
    data class UpdateAnimationsVelocity(val key: AppAnimationVelocity.Key) : Cmd()
    object ResetNoteCardLinesByDefault : Cmd()
    object FetchCurrentAppLang : Cmd()

}

sealed class Eff : ElmEffect {
    object NavigateBack : Eff()
    object HideModalSheet : Eff()
}