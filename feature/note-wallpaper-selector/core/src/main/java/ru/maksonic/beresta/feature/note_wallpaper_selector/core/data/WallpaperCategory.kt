package ru.maksonic.beresta.feature.note_wallpaper_selector.core.data

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable

/**
 * @Author maksonic on 10.03.2023
 */
data class NoteWallpaper(
    val id: Long = 0,
    val resourceId: Int = 0,
    val isSelected: Boolean = false,
)

data class WallpaperCategory(
    val id: Int = 0,
    val title: String = "",
    val wallpapers: Array<NoteWallpaper> = emptyArray(),
) {

    @Stable
    @Immutable
    data class Collection(val data: Array<WallpaperCategory>) {
        companion object {
            val Empty = Collection(emptyArray())
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Collection

            if (!data.contentEquals(other.data)) return false

            return true
        }

        override fun hashCode(): Int {
            return data.contentHashCode()
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as WallpaperCategory

        if (id != other.id) return false
        if (title != other.title) return false
        if (!wallpapers.contentEquals(other.wallpapers)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + title.hashCode()
        result = 31 * result + wallpapers.contentHashCode()
        return result
    }
}