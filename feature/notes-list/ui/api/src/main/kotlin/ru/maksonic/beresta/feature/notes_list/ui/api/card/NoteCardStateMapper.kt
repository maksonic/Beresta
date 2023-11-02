package ru.maksonic.beresta.feature.notes_list.ui.api.card

import ru.maksonic.beresta.common.core.Mapper
import ru.maksonic.beresta.feature.notes_list.domain.card.NoteCardDomainState
import ru.maksonic.beresta.feature.notes_list.domain.card.NoteCardElevationDomain
import ru.maksonic.beresta.feature.notes_list.domain.card.NoteCardShapeDomain

/**
 * @Author maksonic on 13.10.2023
 */
interface NoteCardStateMapper : Mapper<NoteCardDomainState, NoteCardUiState> {
    fun mapDomainShapeToUi(domain: NoteCardShapeDomain): NoteCardShapeUi
    fun mapUiShapeToDomain(ui: NoteCardShapeUi): NoteCardShapeDomain
    fun mapDomainElevationToUi(domain: NoteCardElevationDomain): NoteCardElevationUi
    fun mapUiElevationToDomain(ui: NoteCardElevationUi): NoteCardElevationDomain
}