package ru.maksonic.beresta.feature.notes_list.ui.core.data

import ru.maksonic.beresta.feature.notes_list.domain.card.NoteCardDomainState
import ru.maksonic.beresta.feature.notes_list.domain.card.NoteCardElevationDomain
import ru.maksonic.beresta.feature.notes_list.domain.card.NoteCardShapeDomain
import ru.maksonic.beresta.feature.notes_list.ui.api.card.NoteCardElevationUi
import ru.maksonic.beresta.feature.notes_list.ui.api.card.NoteCardShapeUi
import ru.maksonic.beresta.feature.notes_list.ui.api.card.NoteCardStateMapper
import ru.maksonic.beresta.feature.notes_list.ui.api.card.NoteCardUiState

/**
 * @Author maksonic on 13.10.2023
 */
class NoteCardStateMapperImpl : NoteCardStateMapper {
    override fun mapTo(i: NoteCardDomainState) = NoteCardUiState(
        shape = NoteCardShapeUi.valueOf(i.shape.name),
        elevation = NoteCardElevationUi.valueOf(i.elevation.name),
        maxTitleLines = i.maxTitleLines,
        maxMessageLines = i.maxMessageLines,
        isVisibleColorMarker = i.isVisibleColorMarker,
        isVisibleWallpaper = i.isVisibleWallpaper
    )

    override fun mapFrom(o: NoteCardUiState) = NoteCardDomainState(
        shape = NoteCardShapeDomain.valueOf(o.shape.name),
        elevation = NoteCardElevationDomain.valueOf(o.elevation.name),
        maxTitleLines = o.maxTitleLines,
        maxMessageLines = o.maxMessageLines,
        isVisibleColorMarker = o.isVisibleColorMarker,
        isVisibleWallpaper = o.isVisibleWallpaper
    )

    override fun mapDomainShapeToUi(domain: NoteCardShapeDomain) =
        NoteCardShapeUi.valueOf(domain.name)

    override fun mapUiShapeToDomain(ui: NoteCardShapeUi) = NoteCardShapeDomain.valueOf(ui.name)

    override fun mapDomainElevationToUi(domain: NoteCardElevationDomain) =
        NoteCardElevationUi.valueOf(domain.name)

    override fun mapUiElevationToDomain(ui: NoteCardElevationUi) =
        NoteCardElevationDomain.valueOf(ui.name)
}