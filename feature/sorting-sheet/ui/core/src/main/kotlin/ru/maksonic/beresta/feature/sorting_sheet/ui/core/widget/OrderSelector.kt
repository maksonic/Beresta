package ru.maksonic.beresta.feature.sorting_sheet.ui.core.widget

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.DraggableAnchors
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.anchoredDraggable
import androidx.compose.foundation.gestures.animateTo
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import ru.maksonic.beresta.common.core.ui.sorting.Order
import ru.maksonic.beresta.common.core.ui.sorting.isAscending
import ru.maksonic.beresta.common.ui_kit.helpers.PreviewContainer
import ru.maksonic.beresta.common.ui_kit.helpers.modifier.noRippleClick
import ru.maksonic.beresta.common.ui_kit.icons.AppIcon
import ru.maksonic.beresta.common.ui_kit.icons.sort.Ascending
import ru.maksonic.beresta.common.ui_kit.surface.SurfacePro
import ru.maksonic.beresta.common.ui_theme.Theme
import ru.maksonic.beresta.common.ui_theme.colors.onTertiaryContainer
import ru.maksonic.beresta.common.ui_theme.colors.outline
import ru.maksonic.beresta.common.ui_theme.colors.tertiaryContainer
import ru.maksonic.beresta.common.ui_theme.provide.dp16
import ru.maksonic.beresta.common.ui_theme.provide.dp8
import ru.maksonic.beresta.common.ui_theme.typography.TextDesign
import ru.maksonic.beresta.feature.sorting_sheet.domain.SortDataKey
import ru.maksonic.beresta.feature.sorting_sheet.ui.api.LocalListNotesSortState
import ru.maksonic.beresta.feature.sorting_sheet.ui.api.NotesSortUi
import ru.maksonic.beresta.feature.sorting_sheet.ui.api.listFoldersSortState
import ru.maksonic.beresta.feature.sorting_sheet.ui.api.listHiddenNotesSortState
import ru.maksonic.beresta.feature.sorting_sheet.ui.api.listNotesSortState
import ru.maksonic.beresta.language_engine.shell.provider.text
import kotlin.math.roundToInt

/**
 * @Author maksonic on 06.07.2023
 */
private enum class DragAnchors(val fraction: Float) {
    Ascending(0f),
    Descending(1f),
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun OrderSelector(
    sortDataKey: SortDataKey,
    onOrderClicked: (Order) -> Unit,
    modifier: Modifier = Modifier
) {
    val currentSortOrder = when (sortDataKey) {
        SortDataKey.NOTES -> listNotesSortState.order
        SortDataKey.HIDDEN_NOTES -> listHiddenNotesSortState.order
        SortDataKey.FOLDERS -> listFoldersSortState.order
    }

    val density = LocalDensity.current
    val scope = rememberCoroutineScope()
    val positionalThreshold = { distance: Float -> distance * 0.5f }
    val velocityThreshold = { with(density) { 100.dp.toPx() } }
    val animationSpec = tween<Float>()
    val sliderPosition = if (currentSortOrder == Order.ASCENDING) DragAnchors.Ascending
    else DragAnchors.Descending
    val swipeableSheetState = rememberSaveable(
        density,
        saver = AnchoredDraggableState.Saver(
            animationSpec = animationSpec,
            positionalThreshold = positionalThreshold,
            velocityThreshold = velocityThreshold,
        )
    ) {
        AnchoredDraggableState(
            initialValue = sliderPosition,
            positionalThreshold = positionalThreshold,
            velocityThreshold = velocityThreshold,
            animationSpec = animationSpec,
        )
    }

    LaunchedEffect(swipeableSheetState.progress) {
        val isDescendingTargetState = swipeableSheetState.targetValue == DragAnchors.Descending
        val isAscendingTargetState = swipeableSheetState.targetValue == DragAnchors.Ascending

        if (isDescendingTargetState) onOrderClicked(Order.DESCENDING)
        if (isDescendingTargetState) onOrderClicked(Order.DESCENDING)
        if (isAscendingTargetState) onOrderClicked(Order.ASCENDING)
        if (isAscendingTargetState) onOrderClicked(Order.ASCENDING)
    }

    SurfacePro(
        tonalElevation = Theme.tonal.level5,
        shape = Theme.shape.cornerRound,
        modifier = Modifier
            .padding(dp16)
            .height(Theme.size.minimumTouchTargetSize)
            .fillMaxWidth()
    ) {
        BoxWithConstraints {
            val contentWidth = this.maxWidth

            Row {
                Box(
                    modifier
                        .weight(1f)
                        .height(Theme.size.minimumTouchTargetSize)
                        .noRippleClick {
                            scope.launch {
                                swipeableSheetState.animateTo(DragAnchors.Ascending)
                            }
                        }
                )
                Box(
                    modifier
                        .weight(1f)
                        .height(Theme.size.minimumTouchTargetSize)
                        .noRippleClick {
                            scope.launch {
                                swipeableSheetState.animateTo(DragAnchors.Descending)
                            }
                        }
                )
            }

            Box(
                modifier
                    .onSizeChanged { layoutSize ->
                        val dragEndPoint = layoutSize.width
                        swipeableSheetState.updateAnchors(
                            DraggableAnchors {
                                DragAnchors
                                    .values()
                                    .forEach { anchor ->
                                        anchor at dragEndPoint * anchor.fraction
                                    }
                            }
                        )
                    }
            ) {
                DraggableContent(
                    modifier = Modifier
                        .width(contentWidth / 2)
                        .offset {
                            IntOffset(
                                x = swipeableSheetState
                                    .requireOffset()
                                    .roundToInt(),
                                y = 0,
                            )
                        }
                        .anchoredDraggable(swipeableSheetState, Orientation.Horizontal),
                )
            }

            Row(modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Item(
                    order = Order.ASCENDING,
                    isSelected = currentSortOrder == Order.ASCENDING,
                    title = text.sortingSheet.hintSortByAscending,
                    modifier = modifier.weight(1f),
                )

                Item(
                    order = Order.DESCENDING,
                    isSelected = currentSortOrder == Order.DESCENDING,
                    title = text.sortingSheet.hintSortByDescending,
                    modifier = modifier.weight(1f),
                )
            }
        }
    }
}

@Composable
private fun DraggableContent(modifier: Modifier) {
    Box(
        modifier = modifier
            .height(Theme.size.minimumTouchTargetSize)
            .clip(CircleShape)
            .background(tertiaryContainer)
    )
}

@Composable
private fun Item(
    order: Order,
    isSelected: Boolean,
    title: String,
    modifier: Modifier,
) {
    val fontWeight = if (isSelected) FontWeight.Medium else FontWeight.Normal
    val color = animateColorAsState(
        if (isSelected) onTertiaryContainer else outline.copy(0.95f), label = ""
    )
    val iconModifier = if (!order.isAscending) Modifier
        .rotate(180f)
        .scale(-1f, 1f) else Modifier

    Row(
        modifier
            .fillMaxWidth()
            .height(Theme.size.minimumTouchTargetSize),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(
            AppIcon.Ascending, "", tint = color.value, modifier = Modifier
                .padding(end = dp8)
                .then(iconModifier)
        )

        Text(text = title, style = TextDesign.bodyMedium.copy(color.value, fontWeight = fontWeight))
    }
}

@Preview
@Composable
private fun OrderSelectorPreview() {
    PreviewContainer {
        CompositionLocalProvider(value = LocalListNotesSortState provides NotesSortUi.Default) {
            OrderSelector(sortDataKey = SortDataKey.NOTES, onOrderClicked = {})
        }
    }
}