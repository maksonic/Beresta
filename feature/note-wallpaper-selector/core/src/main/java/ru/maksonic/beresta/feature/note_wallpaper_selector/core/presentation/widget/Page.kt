package ru.maksonic.beresta.feature.note_wallpaper_selector.core.presentation.widget

import android.content.res.Configuration
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import ru.maksonic.beresta.feature.note_wallpaper_selector.core.presentation.core.Msg
import ru.maksonic.beresta.feature.note_wallpaper_selector.core.data.NoteWallpaper
import ru.maksonic.beresta.feature.note_wallpaper_selector.core.presentation.SendMessage
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.component.dp4
import ru.maksonic.beresta.ui.theme.component.dp8
import ru.maksonic.beresta.ui.theme.icons.AppIcon
import ru.maksonic.beresta.ui.theme.icons.Done
import ru.maksonic.beresta.ui.widget.button.BoxWithScaleInOutOnClick

/**
 * @Author maksonic on 10.03.2023
 */
@Composable
internal fun Page(
    send: SendMessage,
    selectedNoteWallpaper: NoteWallpaper,
    wallpapers: Array<NoteWallpaper>,
    modifier: Modifier = Modifier
) {
    val configuration = LocalConfiguration.current
    val cellsCount = if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) 6 else 3
    val scrollState = rememberLazyGridState()

    LazyVerticalGrid(
        state = scrollState,
        columns = GridCells.Fixed(cellsCount),
        contentPadding = PaddingValues(dp8),
        modifier = modifier.fillMaxSize()
    ) {
        items(items = wallpapers, key = { item -> item.id }) { item ->
            WallpaperItem(
                wallpaper = item,
                select = { send(Msg.Ui.SelectWallpaper(item)) },
                isSelected = selectedNoteWallpaper == item
            )
        }

        items(cellsCount) {
            Box(
                modifier
                    .fillMaxWidth()
                    .navigationBarsPadding()
                    .height(Theme.widgetSize.bottomMainPanelHeight)
            )
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun WallpaperItem(
    wallpaper: NoteWallpaper,
    select: () -> Unit,
    isSelected: Boolean,
    modifier: Modifier = Modifier
) {

    BoxWithScaleInOutOnClick(onClick = select, onLongClick = select) {
        BoxWithConstraints(
            modifier = modifier
                .aspectRatio(9f / 14f)
                .padding(dp4),
            contentAlignment = Alignment.Center
        ) {
            val selectedIconSize = this.maxWidth / 2
            val checkMarkSize = animateDpAsState(if (isSelected) selectedIconSize else 0.dp)
            val padding = this.maxWidth / 4

            GlideImage(
                model = wallpaper.resourceId,
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = modifier.fillMaxSize()
            )

            Box(
                modifier
                    .padding(padding)
                    .size(checkMarkSize.value)
                    .clip(CircleShape)
                    .background(Color.Black.copy(alpha = 0.5f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = AppIcon.Done,
                    contentDescription = "",
                    tint = Color.White,
                    modifier = modifier.size(checkMarkSize.value)
                )
            }
        }
    }
}