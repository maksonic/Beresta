package ru.maksonic.beresta.feature.notes_list.domain.list.usecase

import ru.maksonic.beresta.common.domain.UseCase
import ru.maksonic.beresta.feature.notes_list.domain.NoteDomainItem

/**
 * @Author maksonic on 15.10.2023
 */
interface FetchNoteByIdUseCase : UseCase.WithArgs<NoteDomainItem, Long>