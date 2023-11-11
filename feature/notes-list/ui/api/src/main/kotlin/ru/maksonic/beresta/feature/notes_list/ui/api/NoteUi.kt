package ru.maksonic.beresta.feature.notes_list.ui.api

import androidx.compose.ui.graphics.Color
import ru.maksonic.beresta.common.ui_theme.colors.ColorContainer
import ru.maksonic.beresta.feature.tags_list.ui.api.NoteTagUi
import ru.maksonic.beresta.feature.wallpaper_picker.domain.wallpaper.BaseWallpaper
import ru.maksonic.beresta.feature.wallpaper_picker.domain.wallpaper.WallpaperColor
import ru.maksonic.beresta.feature.wallpaper_picker.domain.wallpaper.WallpaperGradient
import ru.maksonic.beresta.feature.wallpaper_picker.domain.wallpaper.WallpaperImage
import ru.maksonic.beresta.feature.wallpaper_picker.domain.wallpaper.WallpaperTexture
import java.time.LocalDateTime

/**
 * @Author maksonic on 21.02.2023
 */
data class Style(
    val isPinned: Boolean,
    val markerColorId: Long,
    val markerColor: ColorContainer = ColorContainer.Default,
    val wallpaperTypeId: Int,
    val wallpaperId: Long,
    val wallpaperTintId: Long,
    val wallpaperBackgroundColorId: Long,
    val isTextureStyle: Boolean,
    val isDarkWallpaper: Boolean
) {
    companion object {
        val Initial = Style(
            isPinned = false,
            markerColorId = 0L,
            markerColor = ColorContainer.Default,
            wallpaperTypeId = 0,
            wallpaperId = 0L,
            wallpaperTintId = 0L,
            wallpaperBackgroundColorId = 0L,
            isTextureStyle = false,
            isDarkWallpaper = false
        )
    }
}

data class NoteUi(
    val id: Long,
    val folderId: Long = 2L,
    val key: Long = 0,
    val title: String,
    val message: String,
    val dateCreationRaw: LocalDateTime,
    val dateCreation: String = "",
    val dateLastUpdateRaw: LocalDateTime?,
    val dateMovedToTrashRaw: LocalDateTime?,
    val dateMovedToTrash: String? = "",
    val isHidden: Boolean = false,
    val pinTime: LocalDateTime?,
    val isMovedToTrash: Boolean,
    val isSelected: Boolean = false,
    val style: Style = Style.Initial,
    val wallpaper: BaseWallpaper<Color> = BaseWallpaper.empty(),
    val tagsIds: List<Long> = emptyList(),
    val tags: NoteTagUi.Collection = NoteTagUi.Collection.Empty,
) {

    companion object {
        val Default = NoteUi(
            id = 0L,
            key = 0L,
            title = "",
            message = "",
            dateCreationRaw = LocalDateTime.now(),
            dateCreation = "",
            dateMovedToTrashRaw = null,
            dateMovedToTrash = "",
            dateLastUpdateRaw = null,
            pinTime = null,
            isMovedToTrash = false,
            isSelected = false,
            style = Style.Initial,
            wallpaper = BaseWallpaper.empty(),
            tagsIds = emptyList(),
            tags = NoteTagUi.Collection.Empty
        )

        val Preview = Default.copy(
            title = "Note title preview",
            message = "Note message preview",
            dateCreation = "today - 12:00"
        )
    }

    fun trash() = this.copy(isMovedToTrash = true, dateMovedToTrashRaw = LocalDateTime.now())
    fun restored() = this.copy(isMovedToTrash = false, dateMovedToTrashRaw = null)

    fun addWallpaperParams(wallpaper: BaseWallpaper<Color>): NoteUi =
        when (wallpaper) {
            is WallpaperColor<Color> -> {
                this.copy(
                    style = this.style.copy(
                        wallpaperTypeId = wallpaper.getType().id,
                        wallpaperId = wallpaper.id,
                        isDarkWallpaper = wallpaper.isDark
                    )
                )
            }

            is WallpaperGradient<Color> -> {
                this.copy(
                    style = this.style.copy(
                        wallpaperTypeId = wallpaper.getType().id,
                        wallpaperId = wallpaper.id,
                        isDarkWallpaper = wallpaper.isDark
                    )
                )
            }

            is WallpaperTexture<Color> -> {
                this.copy(
                    style = this.style.copy(
                        wallpaperTypeId = wallpaper.getType().id,
                        wallpaperId = wallpaper.id,
                        wallpaperTintId = wallpaper.tintColor.id,
                        wallpaperBackgroundColorId = wallpaper.backgroundColor.id,
                        isTextureStyle = wallpaper.isTextureStyle,
                        isDarkWallpaper = wallpaper.isDark
                    )
                )
            }

            is WallpaperImage<Color> -> {
                this.copy(
                    style = this.style.copy(
                        wallpaperTypeId = wallpaper.getType().id,
                        wallpaperId = wallpaper.id,
                        isDarkWallpaper = wallpaper.isDark
                    )
                )
            }

            else -> this
        }

    data class Collection(val data: List<NoteUi>) {
        companion object {
            val Empty = Collection(emptyList())
        }
    }
}

fun List<NoteUi>.findNotesByFoldersId(ids: List<Long>) =
    this.filter { note -> ids.any { folderId -> note.folderId == folderId } }

fun NoteUi.isBlank() = this.title.isBlank() && this.message.isBlank()

fun NoteUi.isDefaultId() = this.id == NoteUi.Default.id

fun NoteUi.Collection.unselectAll() =
    this.copy(data = this.data.map { it.copy(isSelected = false) })