package ru.maksonic.beresta.feature.note_wallpaper_selector.core.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.pager.*
import kotlinx.coroutines.flow.*
import org.koin.androidx.compose.koinViewModel
import ru.maksonic.beresta.feature.language_selector.api.provider.text
import ru.maksonic.beresta.feature.note_wallpaper_selector.api.NoteWallpaperSelectorApi
import ru.maksonic.beresta.feature.note_wallpaper_selector.core.Eff
import ru.maksonic.beresta.feature.note_wallpaper_selector.core.Msg
import ru.maksonic.beresta.feature.note_wallpaper_selector.core.WallpaperSelectorSandbox
import ru.maksonic.beresta.feature.note_wallpaper_selector.core.data.NoteWallpaper
import ru.maksonic.beresta.feature.note_wallpaper_selector.core.data.WallpaperCategory
import ru.maksonic.beresta.feature.note_wallpaper_selector.core.presentation.widget.TabLayout
import ru.maksonic.beresta.feature.note_wallpaper_selector.core.presentation.widget.TopBarContainer
import ru.maksonic.beresta.feature.note_wallpaper_selector.core.presentation.widget.WallpaperPager
import ru.maksonic.beresta.ui.theme.color.background
import ru.maksonic.beresta.ui.theme.color.onPrimary
import ru.maksonic.beresta.ui.theme.color.primary
import ru.maksonic.beresta.ui.theme.color.tertiaryContainer
import ru.maksonic.beresta.ui.theme.component.*
import ru.maksonic.beresta.ui.theme.icons.AppIcon
import ru.maksonic.beresta.ui.theme.icons.Restart
import ru.maksonic.beresta.ui.widget.SystemNavigationBar
import ru.maksonic.beresta.ui.widget.SystemStatusBar
import ru.maksonic.beresta.ui.widget.bar.TopAppBarNormal
import ru.maksonic.beresta.ui.widget.functional.HandleEffectsWithLifecycle
import ru.maksonic.beresta.ui.widget.functional.noRippleClickable
import ru.maksonic.beresta.ui.widget.toastShortTime

/**
 * @Author maksonic on 09.03.2023
 */
internal typealias SendMessage = (Msg) -> Unit

class NoteWallpaperSelectorCore : NoteWallpaperSelectorApi {
    private val mutableNoteWallpaper = MutableStateFlow(0)

    override val currentWallpaper: StateFlow<Int>
        get() = mutableNoteWallpaper.asStateFlow()

    @OptIn(ExperimentalComposeUiApi::class)
    @Composable
    override fun BottomSheetContent(hideSheet: () -> Unit) {
        val keyboardController = LocalSoftwareKeyboardController.current

        LaunchedEffect(Unit) {
            keyboardController?.hide()
        }

        Content(hideSheet = hideSheet)
    }

    @OptIn(ExperimentalPagerApi::class)
    @Composable
    private fun Content(
        modifier: Modifier = Modifier,
        sandbox: WallpaperSelectorSandbox = koinViewModel(),
        hideSheet: () -> Unit,
    ) {
        HandleUiEffects(sandbox.effects, mutableNoteWallpaper)

        val model = sandbox.model.collectAsStateWithLifecycle().value
        val pagerState = rememberPagerState()

        Box(
            modifier
                .fillMaxSize()
                .background(background)
                .noRippleClickable { },
            contentAlignment = Alignment.BottomEnd
        ) {
            Column(
                modifier
                    .fillMaxSize()
            ) {
                val systemBarsColor = tertiaryContainer
                SystemStatusBar(backgroundColor = { systemBarsColor })
                TopBarContainer(
                    wallpapers = model.wallpapers,
                    pagerState = { pagerState },
                    hideSheet = hideSheet
                )
                WallpaperPager(
                    send = sandbox::sendMsg,
                    tabData = model.wallpapers,
                    pagerState = { pagerState },
                    selectedNoteWallpaper = model.selectedWallpaper
                )
                SystemNavigationBar(backgroundColor = { systemBarsColor })
            }

            Row(
                modifier
                    .fillMaxWidth()
                    .navigationBarsPadding()
                    .padding(start = dp16, end = dp16, bottom = dp16)
            ) {
                FloatingActionButton(
                    onClick = {
                        if (model.selectedWallpaper.resourceId == 0) {
                            sandbox.sendMsg(Msg.Inner.ShowedNotSelectedWallpaperToast)
                        } else {
                            sandbox.sendMsg(Msg.Ui.ApplySelectedWallpaper)
                            hideSheet()
                        }
                    },
                    containerColor = primary,
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = dp16)
                ) {
                    Text(
                        text = text.editNote.topBarTitleSelectNoteWallpaper,
                        style = TextDesign.title.copy(color = onPrimary)
                    )
                }

                FloatingActionButton(
                    onClick = {
                        sandbox.sendMsg(Msg.Ui.SelectWallpaper(NoteWallpaper()))
                        sandbox.sendMsg(Msg.Ui.ApplySelectedWallpaper)
                        hideSheet()
                    },
                    containerColor = primary
                ) {
                    Icon(
                        imageVector = AppIcon.Restart,
                        contentDescription = "",
                        tint = onPrimary
                    )
                }
            }
        }
    }
}

@Composable
private fun HandleUiEffects(effects: Flow<Eff>, mutableNoteWallpaper: MutableStateFlow<Int>) {
    val context = LocalContext.current
    val emptySelectMsg = text.editNote.noteWallpaperIsNotSelectedWarning

    HandleEffectsWithLifecycle(effects) { eff ->
        when (eff) {
            is Eff.UpdateSharedWallpaperStateValue -> mutableNoteWallpaper.update { eff.resourceId }
            is Eff.ShowEmptySelectedWallpaperForApplying -> context.toastShortTime(emptySelectMsg)
        }
    }
}
