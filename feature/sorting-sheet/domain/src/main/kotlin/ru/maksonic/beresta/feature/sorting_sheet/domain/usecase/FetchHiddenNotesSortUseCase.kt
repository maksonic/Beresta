package ru.maksonic.beresta.feature.sorting_sheet.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.maksonic.beresta.common.domain.UseCase
import ru.maksonic.beresta.feature.sorting_sheet.domain.sort.NotesSortDomain

/**
 * @Author maksonic on 16.10.2023
 */
interface FetchHiddenNotesSortUseCase : UseCase.Default<Flow<NotesSortDomain>>