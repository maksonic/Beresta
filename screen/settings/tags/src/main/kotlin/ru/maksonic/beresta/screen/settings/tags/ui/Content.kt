package ru.maksonic.beresta.screen.settings.tags.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import org.koin.compose.koinInject
import ru.maksonic.beresta.common.ui_kit.button.ButtonPrimary
import ru.maksonic.beresta.common.ui_kit.placeholder.PlaceholderEmptyState
import ru.maksonic.beresta.common.ui_kit.placeholder.PlaceholderLoading
import ru.maksonic.beresta.common.ui_kit.sheet.ModalBottomSheetContainer
import ru.maksonic.beresta.common.ui_theme.Theme
import ru.maksonic.beresta.common.ui_theme.colors.background
import ru.maksonic.beresta.common.ui_theme.colors.onBackground
import ru.maksonic.beresta.common.ui_theme.colors.surface
import ru.maksonic.beresta.common.ui_theme.colors.surfaceVariant
import ru.maksonic.beresta.common.ui_theme.provide.dp16
import ru.maksonic.beresta.common.ui_theme.provide.dp8
import ru.maksonic.beresta.feature.ui.add_tag_dialog.api.AddTagDialogUiApi
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.screen.settings.tags.core.Model
import ru.maksonic.beresta.screen.settings.tags.core.Msg
import ru.maksonic.beresta.screen.settings.tags.ui.widget.ModalSheetContent
import ru.maksonic.beresta.screen.settings.tags.ui.widget.TopBar

/**
 * @Author maksonic on 12.11.2023
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun Content(
    model: Model,
    send: Send,
    modalBottomSheetState: SheetState,
    modifier: Modifier = Modifier,
    addTagDialogUiApi: AddTagDialogUiApi = koinInject()
) {
    val scrollBehavior =
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())

    Box(modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
        Scaffold(
            topBar = { TopBar(model, send, scrollBehavior) },
            containerColor = background,
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
        ) { paddings ->
            Box(modifier.padding(paddings), contentAlignment = Alignment.BottomEnd) {
                when {
                    model.base.isLoading -> PlaceholderLoading()
                    model.base.successAfterLoading -> {
                        if (model.tags.data.isNotEmpty()) {
                            SuccessContent(model, send, modifier)
                        } else {
                            PlaceholderEmptyState(message = text.tags.hintEmptyTagList)
                        }
                    }
                }
            }
        }

        ButtonPrimary(
            onClick = { send(Msg.Ui.OnCreateNewTagClicked) },
            title = text.tags.btnTitleCreateNewTag,
            modifier = Modifier
                .fillMaxWidth()
                .navigationBarsPadding()
                .padding(dp16)
        )

        if (model.modalSheet.isVisible) {
            ModalBottomSheetContainer(
                sheetState = modalBottomSheetState,
                onDismissRequest = { send(Msg.Inner.UpdatedModalSheetState(false)) },
            ) {
                ModalSheetContent(
                    currentClickedTagTitle = model.currentClickedTag?.title ?: "",
                    hideSheet = { send(Msg.Ui.HideModalBottomSheet) },
                    onRenameClicked = { send(Msg.Ui.OnModalSheetRenameTagClicked) },
                    onDeleteClicked = { send(Msg.Ui.OnModalSheetDeleteTagClicked) },
                )
            }
        }

        addTagDialogUiApi.Widget(
            sharedState = model.addTagDialogState,
            hideDialog = { send(Msg.Inner.HiddenAddTagDialog) }
        )
    }
}

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
private fun SuccessContent(model: Model, send: Send, modifier: Modifier) {
    FlowRow(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(
                start = dp8,
                end = dp8,
                top = dp8,
                bottom = Theme.size.bottomMainBarHeight
            ),
        horizontalArrangement = Arrangement.spacedBy(dp8),
        verticalArrangement = Arrangement.Center
    ) {
        model.tags.data.forEach { tag ->
            FilterChip(
                selected = tag.isSelected,
                enabled = true,
                onClick = { send(Msg.Ui.OnTagClicked(tag)) },
                label = { Text(tag.title) },
                colors = FilterChipDefaults.filterChipColors(
                    containerColor = surface,
                    labelColor = onBackground,
                    selectedContainerColor = surfaceVariant,
                    selectedLabelColor = onBackground
                )
            )
        }
    }
}