package ru.maksonic.beresta.feature.note_wallpaper_selector.core

import androidx.activity.compose.BackHandler
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.maksonic.beresta.feature.language_selector.api.provider.text
import ru.maksonic.beresta.feature.note_wallpaper_selector.api.NoteWallpaperSelectorApi
import ru.maksonic.beresta.ui.theme.color.background
import ru.maksonic.beresta.ui.widget.SystemNavigationBar
import ru.maksonic.beresta.ui.widget.SystemStatusBar
import ru.maksonic.beresta.ui.widget.bar.TopAppBarNormal
import ru.maksonic.beresta.ui.widget.functional.noRippleClickable

/**
 * @Author maksonic on 09.03.2023
 */
class NoteWallpaperSelectorCore : NoteWallpaperSelectorApi {
    private val mutableNoteWallpaper = MutableStateFlow(Color.White)

    override val currentWallpaper: StateFlow<Color>
        get() = mutableNoteWallpaper.asStateFlow()

    @Composable
    override fun BottomSheetContent(isVisibleSheet: Boolean, hideSheet: () -> Unit) {

        BackHandler(isVisibleSheet) {
            hideSheet()
        }

        Content(hideSheet)
    }
}

@Composable
private fun Content(hideSheet: () -> Unit, modifier: Modifier = Modifier) {
    val scrollState = rememberLazyListState()

    Box(
        modifier
            .fillMaxSize()
            .background(background)
            .noRippleClickable { }
    ) {
        SystemBarsContainer(modifier)
        TopBarContainer(hideSheet)
    }
}

@Composable
fun TopBarContainer(hideSheet: () -> Unit) {
    val topBarColor = background
    TopAppBarNormal(
        title = text.editNote.topBarTitleSelectNoteWallpaper,
        backgroundColor = { topBarColor },
        backAction = { hideSheet() },
        modifier = Modifier.statusBarsPadding()
    )

}

@Composable
fun SystemBarsContainer(modifier: Modifier) {
    Column(modifier.fillMaxSize()) {
        val color = background
        SystemStatusBar(backgroundColor = { color })
        Spacer(modifier.weight(1f))
        SystemNavigationBar(backgroundColor = { color })
    }
}
