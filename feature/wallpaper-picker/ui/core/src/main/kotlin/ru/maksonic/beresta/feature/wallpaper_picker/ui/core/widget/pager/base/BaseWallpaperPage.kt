package ru.maksonic.beresta.feature.wallpaper_picker.ui.core.widget.pager.base

import android.content.res.Configuration
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import ru.maksonic.beresta.common.ui_theme.provide.dp16
import ru.maksonic.beresta.common.ui_theme.provide.dp8

/**
 * @Author maksonic on 17.09.2023
 */
@Composable
internal fun BaseWallpaperPage(
    modifier: Modifier = Modifier,
    listContent: LazyGridScope.() -> Unit
) {
    val gridCells = with(LocalConfiguration.current) {
        if (this.orientation == Configuration.ORIENTATION_LANDSCAPE) 8 else 4
    }

    LazyVerticalGrid(
        modifier = modifier.fillMaxSize(),
        columns = GridCells.Fixed(gridCells),
        contentPadding = PaddingValues(start = dp8, end = dp8, top = dp16, bottom = dp16)
    ) {
        listContent()
    }
}