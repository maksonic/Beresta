package ru.maksonic.beresta.feature.notes.folders.core.chips_row_widget

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.koinInject
import ru.maksonic.beresta.feature.notes.folders.api.ui.FoldersListApi
import ru.maksonic.beresta.feature.notes.folders.api.ui.NoteFolderUi
import ru.maksonic.beresta.feature.notes.folders.api.ui.StickyItemsTitleFormatter
import ru.maksonic.beresta.feature.notes.folders.api.ui.isDefaultId
import ru.maksonic.beresta.feature.notes.folders.api.ui.showDialog
import ru.maksonic.beresta.feature.notes.list.api.ui.NotesListApi
import ru.maksonic.beresta.language_engine.shell.provider.AppLanguage
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.onBackground
import ru.maksonic.beresta.ui.theme.color.onSurface
import ru.maksonic.beresta.ui.theme.color.onTertiaryContainer
import ru.maksonic.beresta.ui.theme.color.outline
import ru.maksonic.beresta.ui.theme.color.tertiaryContainer
import ru.maksonic.beresta.ui.theme.component.Shape
import ru.maksonic.beresta.ui.theme.component.dp12
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.theme.component.dp8
import ru.maksonic.beresta.ui.theme.icons.Add
import ru.maksonic.beresta.ui.theme.icons.AppIcon
import ru.maksonic.beresta.ui.theme.icons.PinFilled
import ru.maksonic.beresta.ui.widget.SurfacePro
import ru.maksonic.beresta.ui.widget.button.IconAction
import ru.maksonic.beresta.ui.widget.functional.animation.OverscrollBehavior
import ru.maksonic.beresta.ui.widget.functional.animation.rowFadingEdge

/**
 * @Author maksonic on 01.05.2023
 */
@Composable
internal fun ChipsWidgetContainer(
    chips: NoteFolderUi.Collection,
    notesListApi: NotesListApi.Ui = koinInject(),
    foldersListApi: FoldersListApi.Ui = koinInject(),
    stickyItemsTitleFormatter: StickyItemsTitleFormatter = koinInject(),
    onChipClicked: (id: Long) -> Unit,
    currentSelectedChipId: Long,
    isShowPlaceholder: Boolean,
    currentLanguage: AppLanguage,
    modifier: Modifier = Modifier
) {
    val notesSharedUiState = notesListApi.sharedUiState.state.collectAsStateWithLifecycle()
    val resultStateColor = remember { mutableStateOf(Color.Transparent) }
    val tonal = animateDpAsState(
        if (notesSharedUiState.value.isNotColoredTopBar) Theme.tonal.Level0
        else Theme.tonal.Level2, label = "", animationSpec = tween(Theme.animSpeed.common)
    )
    val chipsOffset = animateDpAsState(
        if (notesSharedUiState.value.isVisibleChipsRow) 0.dp
        else -Theme.widgetSize.topBarNormalHeight, label = ""
    )

    SurfacePro(tonalElevation = tonal.value, modifier = modifier.graphicsLayer {
        translationY = chipsOffset.value.toPx()
    }) { resultColor ->

        LaunchedEffect(resultColor) {
            resultStateColor.value = resultColor
        }

        ChipsWidgetContent(
            chips = chips,
            foldersListApi = foldersListApi,
            onChipClicked = onChipClicked,
            currentSelectedChipId = currentSelectedChipId,
            resultColor = resultStateColor,
            isShowPlaceholder = isShowPlaceholder,
            stickyItemsTitleFormatter = stickyItemsTitleFormatter,
            currentLanguage = currentLanguage,
            modifier = modifier
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ChipsWidgetContent(
    chips: NoteFolderUi.Collection,
    foldersListApi: FoldersListApi.Ui,
    onChipClicked: (id: Long) -> Unit,
    currentSelectedChipId: Long,
    resultColor: State<Color>,
    isShowPlaceholder: Boolean,
    stickyItemsTitleFormatter: StickyItemsTitleFormatter,
    currentLanguage: AppLanguage,
    modifier: Modifier
) {
    Box(modifier.statusBarsPadding()) {
        Row(
            modifier
                .fillMaxWidth()
                .padding(top = Theme.widgetSize.topBarNormalHeight)
                .height(Theme.widgetSize.noteChipsContainerHeight)
                .padding(start = dp16),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val lazyRowState = rememberLazyListState()

            OverscrollBehavior {
                LazyRow(
                    state = lazyRowState,
                    modifier = modifier
                        .weight(1f, false)
                        .rowFadingEdge(
                            startEdgeInitialColor = resultColor.value,
                            isVisibleStartEdge = lazyRowState.canScrollBackward,
                            isVisibleEndEdge = lazyRowState.canScrollForward,
                        ),
                    horizontalArrangement = Arrangement.spacedBy(dp8),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    items(
                        items = stickyItemsTitleFormatter.format(chips.data, currentLanguage),
                        key = { chip -> chip.id }) { item ->
                        val updated =
                            item.copy(isSelected = item.id == currentSelectedChipId)
                        ChipItem(updated, onChipClicked, modifier.animateItemPlacement())
                    }
                }
            }
            AddNewFilterButton(
                onClick = { foldersListApi.sharedUiState.showDialog(true, 0L) },
                modifier = modifier.padding(start = dp12, end = dp16)
            )
        }

        if (isShowPlaceholder) {
            ChipsLoaderWidget()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ChipItem(
    item: NoteFolderUi,
    onChipClicked: (id: Long) -> Unit,
    modifier: Modifier = Modifier
) {
    val pinIconTint = animateColorAsState(
        if (item.isSelected) onTertiaryContainer else onBackground, label = ""
    )

    FilterChip(
        selected = item.isSelected,
        onClick = { onChipClicked(item.id) },
        label = { Text(item.title) },
        trailingIcon = {
            if (item.isPinned && !item.isDefaultId())
                Icon(
                    imageVector = AppIcon.PinFilled,
                    tint = pinIconTint.value,
                    contentDescription = "",
                    modifier = Modifier.size(dp12)
                )
        },
        shape = Shape.cornerRound,
        border = FilterChipDefaults.filterChipBorder(borderColor = outline),
        colors = FilterChipDefaults.filterChipColors(
            selectedContainerColor = tertiaryContainer,
            labelColor = onBackground,
            selectedLabelColor = onTertiaryContainer
        ),
        modifier = modifier
    )
}

@Composable
private fun AddNewFilterButton(onClick: () -> Unit, modifier: Modifier = Modifier) {
    Box(
        modifier
            .size(Theme.widgetSize.filterChipHeight)
            .clip(CircleShape)
            .border(1.dp, onSurface, CircleShape)
    ) {
        IconAction(icon = { AppIcon.Add }, tint = onSurface, action = onClick)
    }
}
