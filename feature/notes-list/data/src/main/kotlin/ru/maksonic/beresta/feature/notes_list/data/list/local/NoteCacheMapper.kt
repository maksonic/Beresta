package ru.maksonic.beresta.feature.notes_list.data.list.local

import ru.maksonic.beresta.common.core.Mapper
import ru.maksonic.beresta.database.notes.NoteCache
import ru.maksonic.beresta.feature.notes_list.domain.NoteDomain

/**
 * @Author maksonic on 21.02.2023
 */
class NoteCacheMapper : Mapper<NoteCache, NoteDomain> {
    override fun mapTo(i: NoteCache) = NoteDomain(
        id = i.id,
        folderId = i.folderId,
        title = i.title,
        message = i.message,
        dateCreationRaw = i.dateCreation,
        dateLastUpdateRaw = i.dateLastUpdateRaw,
        pinTime = i.pinTime,
        dateMovedToTrashRaw = i.dateMovedToTrash,
        isPinned = i.isPinned,
        isHidden = i.isHidden,
        isMovedToTrash = i.isMovedToTrash,
        markerColorId = i.markerColorId,
        wallpaperTypeId = i.wallpaperTypeId,
        wallpaperId = i.wallpaperId,
        wallpaperTintId = i.wallpaperTintId,
        wallpaperBackgroundColorId = i.wallpaperBackgroundColorId,
        isTextureStyle = i.isTextureStyle,
        isDarkWallpaper = i.isDarkWallpaper,
        tagsIds = i.tagsIds
    )

    override fun mapFrom(o: NoteDomain) = NoteCache(
        id = o.id,
        folderId = o.folderId,
        title = o.title,
        message = o.message,
        dateCreation = o.dateCreationRaw,
        dateLastUpdateRaw = o.dateLastUpdateRaw,
        pinTime = o.pinTime,
        dateMovedToTrash = o.dateMovedToTrashRaw,
        isPinned = o.isPinned,
        isHidden = o.isHidden,
        isMovedToTrash = o.isMovedToTrash,
        markerColorId = o.markerColorId,
        wallpaperTypeId = o.wallpaperTypeId,
        wallpaperId = o.wallpaperId,
        wallpaperTintId = o.wallpaperTintId,
        wallpaperBackgroundColorId = o.wallpaperBackgroundColorId,
        isTextureStyle = o.isTextureStyle,
        isDarkWallpaper = o.isDarkWallpaper,
        tagsIds = o.tagsIds
    )
}