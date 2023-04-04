package ru.maksonic.beresta.feature.note_wallpaper_selector.core.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.pager.*
import kotlinx.coroutines.flow.*
import org.koin.androidx.compose.koinViewModel
import ru.maksonic.beresta.feature.language_selector.api.provider.text
import ru.maksonic.beresta.feature.note_wallpaper_selector.api.NoteWallpaperSelectorApi
import ru.maksonic.beresta.feature.note_wallpaper_selector.core.data.NoteWallpaper
import ru.maksonic.beresta.feature.note_wallpaper_selector.core.presentation.core.Eff
import ru.maksonic.beresta.feature.note_wallpaper_selector.core.presentation.core.Msg
import ru.maksonic.beresta.feature.note_wallpaper_selector.core.presentation.core.WallpaperSelectorSandbox
import ru.maksonic.beresta.feature.note_wallpaper_selector.core.presentation.widget.TopBarContainer
import ru.maksonic.beresta.feature.note_wallpaper_selector.core.presentation.widget.WallpaperPager
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.*
import ru.maksonic.beresta.ui.theme.component.*
import ru.maksonic.beresta.ui.theme.icons.AppIcon
import ru.maksonic.beresta.ui.theme.icons.Restart
import ru.maksonic.beresta.ui.widget.button.PrimaryButton
import ru.maksonic.beresta.ui.widget.functional.HandleEffectsWithLifecycle
import ru.maksonic.beresta.ui.widget.functional.noRippleClickable
import ru.maksonic.beresta.ui.widget.toastShortTime

/**
 * @Author maksonic on 09.03.2023
 */
internal typealias SendMessage = (Msg) -> Unit

class NoteWallpaperSelectorUiCore : NoteWallpaperSelectorApi {
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

    @OptIn(ExperimentalPagerApi::class, ExperimentalMaterial3Api::class)
    @Composable
    private fun Content(
        modifier: Modifier = Modifier,
        sandbox: WallpaperSelectorSandbox = koinViewModel(),
        hideSheet: () -> Unit,
    ) {
        HandleUiEffects(sandbox.effects, mutableNoteWallpaper)

        val model = sandbox.model.collectAsStateWithLifecycle().value
        val pagerState = rememberPagerState()
        val scrollBehavior =
            TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

        Scaffold(
            topBar = {
                TopBarContainer(
                    scrollBehavior = scrollBehavior,
                    wallpapers = model.wallpapers,
                    pagerState = { pagerState },
                    hideSheet = hideSheet
                )
            },
            containerColor = background,
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
        ) { paddings ->

            Box(
                modifier.fillMaxSize().noRippleClickable {  },
                contentAlignment = Alignment.BottomEnd
            ) {
                WallpaperPager(
                    send = sandbox::send,
                    tabData = model.wallpapers,
                    pagerState = { pagerState },
                    selectedNoteWallpaper = model.selectedWallpaper,
                    modifier = modifier.padding(paddingValues = paddings)
                )

                Row(
                    modifier
                        .fillMaxWidth()
                        .navigationBarsPadding()
                        .padding(start = dp16, end = dp16, bottom = dp16)
                ) {
                    PrimaryButton(
                        action = {
                            if (model.selectedWallpaper.resourceId == 0) {
                                sandbox.send(Msg.Inner.ShowedNotSelectedWallpaperToast)
                            } else {
                                sandbox.send(Msg.Ui.ApplySelectedWallpaper)
                                hideSheet()
                            }
                        },
                        title = text.editNote.topBarTitleSelectNoteWallpaper,
                        elevation = androidx.compose.material.ButtonDefaults.elevation(
                            defaultElevation = Theme.elevation.Level3
                        ),
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = dp16)
                    )

                    FloatingActionButton(
                        onClick = {
                            sandbox.send(Msg.Ui.SelectWallpaper(NoteWallpaper()))
                            sandbox.send(Msg.Ui.ApplySelectedWallpaper)
                            hideSheet()
                        },
                        containerColor = inversePrimary
                    ) {
                        Icon(
                            imageVector = AppIcon.Restart,
                            contentDescription = "",
                            tint = onTertiary
                        )
                    }
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