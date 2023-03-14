package ru.maksonic.beresta.feature.note_wallpaper_selector.core

import ru.maksonic.beresta.elm.ElmProgram
import ru.maksonic.beresta.feature.note_wallpaper_selector.core.data.WallpaperCategory
import ru.maksonic.beresta.feature.note_wallpaper_selector.core.data.WallpapersRepository

/**
 * @Author maksonic on 10.03.2023
 */
class WallpaperSelectorProgram(
    private val repository: WallpapersRepository
) : ElmProgram<Msg, Cmd> {
    override suspend fun executeProgram(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            is Cmd.FetchWallpapers -> fetchWallpapers(consumer)
        }
    }

    private fun fetchWallpapers(consumer: (Msg) -> Unit) {
        val collection = WallpaperCategory.Collection(repository.wallpapersData)
        consumer(Msg.Inner.FetchedWallpapers(collection))
    }
}