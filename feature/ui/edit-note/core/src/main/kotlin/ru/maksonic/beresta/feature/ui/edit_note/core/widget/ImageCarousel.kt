package ru.maksonic.beresta.feature.ui.edit_note.core.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.imageLoader
import coil.request.CachePolicy
import coil.request.ImageRequest
import org.burnoutcrew.reorderable.ItemPosition
import org.burnoutcrew.reorderable.ReorderableItem
import org.burnoutcrew.reorderable.detectReorderAfterLongPress
import org.burnoutcrew.reorderable.rememberReorderableLazyGridState
import org.burnoutcrew.reorderable.reorderable
import ru.maksonic.beresta.common.ui_kit.surface.SurfacePro
import ru.maksonic.beresta.common.ui_theme.Theme
import ru.maksonic.beresta.common.ui_theme.colors.primary
import ru.maksonic.beresta.common.ui_theme.provide.dp4
import ru.maksonic.beresta.common.ui_theme.provide.dp8
import ru.maksonic.beresta.common.ui_theme.typography.TextDesign
import ru.maksonic.beresta.feature.notes_list.ui.api.NoteImageUi

/**
 * @Author maksonic on 23.11.2023
 */
@Composable
internal fun ImagesCarousel(
    images: NoteImageUi.Collection,
    onPositionChanged: (ItemPosition, ItemPosition) -> Unit,
    modifier: Modifier
) {
    val state = rememberReorderableLazyGridState(onMove = { from, to ->
        onPositionChanged(from, to)
    })

    Box(
        modifier
            .navigationBarsPadding()
            .padding(start = dp8, end = dp8, bottom = Theme.size.bottomMainBarHeight.plus(dp8))
            .clip(Theme.shape.cornerNormal)
            .background(Color.White.copy(0.5f))
    ) {
        LazyVerticalGrid(
            state = state.gridState,
            columns = GridCells.Fixed(3),
            userScrollEnabled = false,
            contentPadding = PaddingValues(dp8),
            modifier = Modifier.reorderable(state),
        ) {

            itemsIndexed(images.data, key = { _, item -> item.id }) { index, imageItem ->
                ReorderableItem(state, key = imageItem.id) { isDragging ->
                    val elevation = if (isDragging) 8.dp else 0.dp

                    SurfacePro(
                        shadowElevation = elevation,
                        shape = Theme.shape.cornerNormal,
                        modifier = Modifier.padding(dp4)
                    ) {
                        if (imageItem.id == 0L) {
                            Box {
                                Item(
                                    item = imageItem,
                                    modifier = Modifier.detectReorderAfterLongPress(state)
                                )
                                OverFlowItem()
                            }
                        } else {
                            Item(
                                item = imageItem,
                                modifier = Modifier.detectReorderAfterLongPress(state)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun OverFlowItem() {
    Box(
        Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .padding(dp4)
            .clip(Theme.shape.cornerSmall)
            .background(Color.White.copy(0.8f))
            .border(2.dp, primary, Theme.shape.cornerSmall),
        contentAlignment = Alignment.Center
    ) {
        Text("10+", style = TextDesign.displaySmall.copy(primary))
    }
}

@Composable
private fun LastItem(modifier: Modifier) {
    Box(
        modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .padding(dp4)
            .clip(Theme.shape.cornerSmall)
            .background(Color.Magenta),
        contentAlignment = Alignment.Center
    ) {
        Text("10+", style = TextDesign.titleLarge)
    }
}

@Composable
internal fun Item(item: NoteImageUi, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val builder = ImageRequest.Builder(context)
        .data(item.resId)
        .crossfade(true)
        .memoryCachePolicy(CachePolicy.ENABLED)
        .diskCachePolicy(CachePolicy.ENABLED)
        .memoryCacheKey("${item.id} image_note_memory_cache_key")
        .diskCacheKey("${item.id} image_note_disk_key")

    AsyncImage(
        model = builder.build(),
        contentDescription = "",
        contentScale = ContentScale.Crop,
        imageLoader = context.imageLoader,
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .padding(dp4)
            .clip(Theme.shape.cornerSmall)
    )
}