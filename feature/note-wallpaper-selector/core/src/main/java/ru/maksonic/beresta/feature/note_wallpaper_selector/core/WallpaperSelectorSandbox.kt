package ru.maksonic.beresta.feature.note_wallpaper_selector.core

import ru.maksonic.beresta.elm.Sandbox
import ru.maksonic.beresta.elm.UpdatedModel

/**
 * @Author maksonic on 10.03.2023
 */
private typealias UpdateResult = UpdatedModel<Model, Set<Cmd>, Set<Eff>>

class WallpaperSelectorSandbox(program: WallpaperSelectorProgram) : Sandbox<Model, Msg, Cmd, Eff>(
    initialModel = Model(),
    initialCmd = setOf(Cmd.FetchWallpapers),
    subscriptions = listOf(program)
) {
    override fun update(msg: Msg, model: Model): UpdateResult = when (msg) {
        is Msg.Inner.FetchedWallpapers -> fetchedWallpapers(model, msg)
        is Msg.Ui.SelectWallpaper -> selectWallpaper(model, msg)
        is Msg.Ui.ApplySelectedWallpaper -> applySelectedNoteWallpaper(model)
        is Msg.Inner.ShowedNotSelectedWallpaperToast -> showedToastWhenApplyEmptySelection(model)
        is Msg.Inner.FillWallpaperCategoryTitle -> fillWallpaperCategoryTitle(model, msg)
    }

    private fun fetchedWallpapers(model: Model, msg: Msg.Inner.FetchedWallpapers): UpdateResult =
        UpdatedModel(model.copy(wallpapers = msg.wallpapers))

    private fun selectWallpaper(model: Model, msg: Msg.Ui.SelectWallpaper): UpdateResult {
        return UpdatedModel(model.copy(selectedWallpaper = msg.wallpaper))
    }


    private fun applySelectedNoteWallpaper(model: Model): UpdateResult =
        UpdatedModel(
            model = model,
            effects = setOf(Eff.UpdateSharedWallpaperStateValue(model.selectedWallpaper.resourceId))
        )

    private fun showedToastWhenApplyEmptySelection(model: Model): UpdateResult =
        UpdatedModel(model, effects = setOf(Eff.ShowEmptySelectedWallpaperForApplying))

    private fun fillWallpaperCategoryTitle(
        model: Model,
        msg: Msg.Inner.FillWallpaperCategoryTitle
    ): UpdateResult {
        val updatedTitles = model.wallpapers.copy(
            data = model.wallpapers.data.map { wallpaperCategory ->
                wallpaperCategory.copy(title = msg.titles[wallpaperCategory.id])
            }.toTypedArray()
        )
        return UpdatedModel(model.copy(wallpapers = updatedTitles))
    }
}