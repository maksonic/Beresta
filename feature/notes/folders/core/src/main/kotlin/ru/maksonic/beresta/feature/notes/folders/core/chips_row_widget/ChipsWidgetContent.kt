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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.get
import ru.maksonic.beresta.feature.notes.folders.api.ui.FoldersListApi
import ru.maksonic.beresta.feature.notes.folders.api.ui.NoteFolderUi
import ru.maksonic.beresta.feature.notes.folders.api.ui.isDefaultId
import ru.maksonic.beresta.feature.notes.folders.api.ui.showDialog
import ru.maksonic.beresta.feature.notes.list.api.ui.NotesListApi
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
@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun ChipsWidgetContent(
    chips: NoteFolderUi.Collection,
    notesListApi: NotesListApi.Ui = get(),
    foldersListApi: FoldersListApi.Ui = get(),
    onChipClicked: (id: Long) -> Unit,
    currentSelectedChipId: Long,
    modifier: Modifier = Modifier
) {
    val notesSharedUiState = notesListApi.sharedUiState.state.collectAsStateWithLifecycle()
    val tonal =
        animateDpAsState(
            if (notesSharedUiState.value.isVisibleFirstNoteOffset) Theme.tonal.Level0
            else Theme.tonal.Level4, label = "", animationSpec = tween(Theme.animSpeed.common)
        )

    SurfacePro(tonalElevation = tonal.value, modifier = modifier) { resultColor ->
        val lazyRowState = rememberLazyListState()

        Row(
            Modifier
                .fillMaxWidth()
                .height(Theme.widgetSize.noteChipsContainerHeight)
                .padding(start = dp16),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OverscrollBehavior {
                LazyRow(
                    state = lazyRowState,
                    modifier = Modifier
                        .weight(1f, false)
                        .rowFadingEdge(
                            startEdgeInitialColor = resultColor,
                            isVisibleStartEdge = lazyRowState.canScrollBackward,
                            isVisibleEndEdge = lazyRowState.canScrollForward,
                        ),
                    horizontalArrangement = Arrangement.spacedBy(dp8),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    items(
                        items = chips.data,
                        key = { chip -> chip.id }) { item ->
                        val updated = item.copy(isSelected = item.id == currentSelectedChipId)
                        ChipItem(updated, onChipClicked, Modifier.animateItemPlacement())
                    }
                }
                AddNewFilterButton(
                    onClick = { foldersListApi.sharedUiState.showDialog(true, 0L) },
                    modifier = Modifier.padding(start = dp12, end = dp16)
                )
            }
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
