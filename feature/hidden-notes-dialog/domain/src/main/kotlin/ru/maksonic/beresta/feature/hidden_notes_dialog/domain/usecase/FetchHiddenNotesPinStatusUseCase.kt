package ru.maksonic.beresta.feature.hidden_notes_dialog.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.maksonic.beresta.common.domain.UseCase

/**
 * @Author maksonic on 25.10.2023
 */
interface FetchHiddenNotesPinStatusUseCase : UseCase.Default<Flow<Boolean>>