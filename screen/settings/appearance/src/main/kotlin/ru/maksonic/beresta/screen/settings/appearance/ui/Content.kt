package ru.maksonic.beresta.screen.settings.appearance.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import org.koin.compose.koinInject
import ru.maksonic.beresta.common.ui_kit.bar.top.TopAppBarCollapsingLarge
import ru.maksonic.beresta.common.ui_kit.sheet.ModalBottomSheetContainer
import ru.maksonic.beresta.common.ui_theme.AppAnimationVelocity
import ru.maksonic.beresta.common.ui_theme.Theme
import ru.maksonic.beresta.common.ui_theme.colors.background
import ru.maksonic.beresta.feature.notes_list.ui.api.card.NotesCardUiApi
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.screen.settings.appearance.core.Model
import ru.maksonic.beresta.screen.settings.appearance.core.Msg
import ru.maksonic.beresta.screen.settings.appearance.ui.widget.MultipleModalBottomSheetContent
import ru.maksonic.beresta.screen.settings.appearance.ui.widget.items.AnimationsSettingItem
import ru.maksonic.beresta.screen.settings.appearance.ui.widget.items.CardSettingItem

/**
 * @Author maksonic on 07.07.2023
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun Content(
    model: Model,
    send: Send,
    modalBottomSheetState: SheetState,
    modifier: Modifier = Modifier,
    notesCardStateStoreUiApi: NotesCardUiApi.CardState = koinInject()
) {
    val scrollState = rememberScrollState()
    val scrollBehavior =
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())
    val currentSheetContent = rememberUpdatedState(model.modalSheet.content)

    Box(
        modifier
            .fillMaxSize()
            .navigationBarsPadding()
    ) {
        val currentVelocityTitle = rememberUpdatedState(
            with(text.settingsAppearance) {
                when (Theme.animVelocity.current) {
                    AppAnimationVelocity.Key.SLOW -> hintAnimSlow
                    AppAnimationVelocity.Key.NORMAL -> hintAnimNormal
                    AppAnimationVelocity.Key.FAST -> hintAnimFast
                    AppAnimationVelocity.Key.VERY_FAST -> hintAnimVeryFast
                    else -> hintAnimDisabled
                }
            }
        )

        Scaffold(
            topBar = {
                TopAppBarCollapsingLarge(
                    scrollBehavior = scrollBehavior,
                    title = text.settingsAppearance.topBarTitle,
                    onBackAction = { send(Msg.Ui.OnTopBarBackPressed) }
                )
            },
            containerColor = background,
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
        ) { paddings ->
            Column(
                modifier
                    .verticalScroll(scrollState)
                    .padding(paddings)
            ) {

                CardSettingItem(
                    send = send,
                    noteCardDate = model.cardDate,
                    noteCardState = notesCardStateStoreUiApi.sharedState,
                )

                AnimationsSettingItem(send, currentVelocityTitle)
            }
        }

        if (model.modalSheet.isVisible) {
            ModalBottomSheetContainer(
                sheetState = modalBottomSheetState,
                onDismissRequest = { send(Msg.Inner.HiddenModalBottomSheet) },
            ) {
                MultipleModalBottomSheetContent(
                    send = send,
                    currentSheetContent = currentSheetContent,
                    noteCardState = notesCardStateStoreUiApi.sharedState,
                    currentVelocityTitle = currentVelocityTitle
                )
            }
        }
    }
}

