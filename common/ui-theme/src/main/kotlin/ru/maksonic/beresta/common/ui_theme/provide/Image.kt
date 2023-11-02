package ru.maksonic.beresta.common.ui_theme.provide

import androidx.compose.runtime.staticCompositionLocalOf
import ru.maksonic.beresta.common.ui_theme.R.drawable.*

/**
 * @Author maksonic on 08.11.2022
 */
val LocalAppImage = staticCompositionLocalOf<AppImage> {
    error("No images provided")
}

data class AppImage(
    val splashCenterLogo: Int,
    val splashBottomLogo: Int,
    val imageEmptyNotes: Int,
    val imageDeleteNote: Int,
    val imageDeleteNotesList: Int,
    val imageDeleteFolder: Int,
    val imageDeleteFoldersList: Int,
    val imageEmptyTrash: Int
)

internal fun provideImages(isDark: Boolean): AppImage {
    val splashCenterLogo = if (isDark) splash_logo_night else splash_logo
    val splashBottomLogo = if (isDark) bottom_brand_logo_night_raw else bottom_brand_logo_day_raw
    val imageEmptyNotes = if (isDark) empty_notes_night else empty_notes
    val imageDeleteNote = if (isDark) delete_note_night else delete_note
    val imageDeleteNotesList = if (isDark) delete_notes_list_night else delete_notes_list
    val imageDeleteFolder = if (isDark) delete_folder_night else delete_folder
    val imageDeleteFoldersList = if (isDark) delete_folders_list_night else delete_folders_list
    val imageEmptyTrash = if (isDark) emty_trash_night else emty_trash

    return AppImage(
        splashCenterLogo = splashCenterLogo,
        splashBottomLogo = splashBottomLogo,
        imageEmptyNotes = imageEmptyNotes,
        imageDeleteNote = imageDeleteNote,
        imageDeleteNotesList = imageDeleteNotesList,
        imageDeleteFolder = imageDeleteFolder,
        imageDeleteFoldersList = imageDeleteFoldersList,
        imageEmptyTrash = imageEmptyTrash
    )
}