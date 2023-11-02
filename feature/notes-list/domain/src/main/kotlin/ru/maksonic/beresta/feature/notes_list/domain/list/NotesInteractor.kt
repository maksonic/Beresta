package ru.maksonic.beresta.feature.notes_list.domain.list

import ru.maksonic.beresta.common.domain.BaseInteractor
import ru.maksonic.beresta.feature.notes_list.domain.NoteDomain

/**
 * @Author maksonic on 13.10.2023
 */
class NotesInteractor(repository: NotesRepository) : BaseInteractor<NoteDomain>(repository)