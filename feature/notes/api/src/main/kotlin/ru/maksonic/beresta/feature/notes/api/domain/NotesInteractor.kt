package ru.maksonic.beresta.feature.notes.api.domain

import ru.maksonic.beresta.core.domain.BaseInteractor

/**
 * @Author maksonic on 25.03.2023
 */
class NotesInteractor(repository: NotesRepository) : BaseInteractor<NoteDomain>(repository)