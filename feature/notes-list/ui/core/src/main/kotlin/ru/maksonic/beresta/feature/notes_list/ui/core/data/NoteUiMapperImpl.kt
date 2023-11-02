package ru.maksonic.beresta.feature.notes_list.ui.core.data

import ru.maksonic.beresta.feature.notes_list.domain.NoteDomain
import ru.maksonic.beresta.feature.notes_list.ui.api.NoteUi
import ru.maksonic.beresta.feature.notes_list.ui.api.NoteUiMapper
import ru.maksonic.beresta.feature.notes_list.ui.api.Style

/**
 * @Author maksonic on 13.10.2023
 */
class NoteUiMapperImpl : NoteUiMapper {
    override fun mapTo(i: NoteDomain) = NoteUi(
        id = i.id,
        folderId = i.folderId,
        title = i.title,
        message = i.message,
        dateCreationRaw = i.dateCreationRaw,
        dateCreation = i.dateCreation,
        dateLastUpdateRaw = i.dateLastUpdateRaw,
        pinTime = i.pinTime,
        dateMovedToTrashRaw = i.dateMovedToTrashRaw,
        dateMovedToTrash = i.dateMovedToTrash,
        isHidden = i.isHidden,
        isMovedToTrash = i.isMovedToTrash,
        style = Style(
            isPinned = i.isPinned,
            markerColorId = i.markerColorId,
            wallpaperTypeId = i.wallpaperTypeId,
            wallpaperId = i.wallpaperId,
            wallpaperTintId = i.wallpaperTintId,
            wallpaperBackgroundColorId = i.wallpaperBackgroundColorId,
            isTextureStyle = i.isTextureStyle,
            isDarkWallpaper = i.isDarkWallpaper
        )
    )

    override fun mapFrom(o: NoteUi) = NoteDomain(
        id = o.id,
        folderId = o.folderId,
        title = o.title,
        message = o.message,
        dateCreationRaw = o.dateCreationRaw,
        dateLastUpdateRaw = o.dateLastUpdateRaw,
        pinTime = o.pinTime,
        dateMovedToTrashRaw = o.dateMovedToTrashRaw,
        isPinned = o.style.isPinned,
        isHidden = o.isHidden,
        isMovedToTrash = o.isMovedToTrash,
        markerColorId = o.style.markerColorId,
        wallpaperTypeId = o.style.wallpaperTypeId,
        wallpaperId = o.style.wallpaperId,
        wallpaperTintId = o.style.wallpaperTintId,
        wallpaperBackgroundColorId = o.style.wallpaperBackgroundColorId,
        isTextureStyle = o.style.isTextureStyle,
        isDarkWallpaper = o.style.isDarkWallpaper
    )
}