package ru.maksonic.beresta.feature.notes_list.data.card

import ru.maksonic.beresta.feature.notes_list.domain.card.NoteCardElevationDomain
import ru.maksonic.beresta.feature.notes_list.domain.card.NoteCardInteractor
import ru.maksonic.beresta.feature.notes_list.domain.card.NoteCardShapeDomain
import ru.maksonic.beresta.feature.notes_list.domain.card.NoteCardStateRepository

/**
 * @Author maksonic on 21.10.2023
 */
class NoteCardInteractorImpl(private val repository: NoteCardStateRepository) : NoteCardInteractor {
    override suspend fun setCardShape(shape: NoteCardShapeDomain) = repository.setCardShape(shape)

    override suspend fun setCardElevation(elevation: NoteCardElevationDomain) =
        repository.setCardElevation(elevation)

    override suspend fun setCardWallpaperVisibility(isVisible: Boolean) =
        repository.setCardWallpaperVisibility(isVisible)

    override suspend fun setCardTitleMaxLines(value: Int) = repository.setCardTitleMaxLines(value)

    override suspend fun setCardMessageMaxLines(value: Int) =
        repository.setCardMessageMaxLines(value)

    override suspend fun setCardColorMarkerVisibility(isVisible: Boolean) =
        repository.setCardColorMarkerVisibility(isVisible)

    override suspend fun setByDefaultCardMaxLines() = repository.setByDefaultCardMaxLines()
}