package ru.maksonic.beresta.feature.note_wallpaper_selector.core.presentation.core

import androidx.compose.runtime.Stable
import ru.maksonic.beresta.elm.*
import ru.maksonic.beresta.feature.note_wallpaper_selector.core.data.NoteWallpaper
import ru.maksonic.beresta.feature.note_wallpaper_selector.core.data.WallpaperCategory

/**
 * @Author maksonic on 10.03.2023
 */
@Stable
data class Model constructor(
    val base: BaseModel = BaseModel(isLoading = true),
    val wallpapers: WallpaperCategory.Collection = WallpaperCategory.Collection.Empty,
    val selectedWallpaper: NoteWallpaper = NoteWallpaper(),
) : ElmModel

sealed class Msg : ElmMessage {
    sealed class Ui : Msg() {
        data class SelectWallpaper(val wallpaper: NoteWallpaper): Ui()
        object ApplySelectedWallpaper: Ui()
    }

    sealed class Inner : Msg() {
        data class FetchedWallpapers(val wallpapers: WallpaperCategory.Collection): Inner()
        object ShowedNotSelectedWallpaperToast : Inner()
        data class FillWallpaperCategoryTitle(val titles: Array<String>): Inner()
    }
}

sealed class Cmd : ElmCommand {
    object FetchWallpapers : Cmd()
}

sealed class Eff : ElmEffect {
    object ShowEmptySelectedWallpaperForApplying : Eff()
    data class UpdateSharedWallpaperStateValue(val resourceId: Int): Eff()
}