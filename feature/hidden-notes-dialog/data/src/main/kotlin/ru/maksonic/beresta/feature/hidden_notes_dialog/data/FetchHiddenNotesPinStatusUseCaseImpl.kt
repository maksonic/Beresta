package ru.maksonic.beresta.feature.hidden_notes_dialog.data

import kotlinx.coroutines.flow.Flow
import ru.maksonic.beresta.feature.hidden_notes_dialog.domain.HiddenNotesPinCreator
import ru.maksonic.beresta.feature.hidden_notes_dialog.domain.usecase.FetchHiddenNotesPinStatusUseCase

/**
 * @Author maksonic on 25.10.2023
 */
class FetchHiddenNotesPinStatusUseCaseImpl(
    private val hiddenNotesPinCreator: HiddenNotesPinCreator
) : FetchHiddenNotesPinStatusUseCase {
    override fun invoke(): Flow<Boolean> = hiddenNotesPinCreator.state
}