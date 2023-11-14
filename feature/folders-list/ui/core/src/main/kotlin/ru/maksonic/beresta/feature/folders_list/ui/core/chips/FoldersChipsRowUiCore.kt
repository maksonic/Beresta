package ru.maksonic.beresta.feature.folders_list.ui.core.chips

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.constraintlayout.compose.ConstraintLayout
import ru.maksonic.beresta.common.core.ui.sorting.FilterDataSorter
import ru.maksonic.beresta.common.ui_kit.animation.OverscrollBehavior
import ru.maksonic.beresta.common.ui_kit.button.ButtonCircle
import ru.maksonic.beresta.common.ui_kit.icons.Add
import ru.maksonic.beresta.common.ui_kit.icons.AppIcon
import ru.maksonic.beresta.common.ui_kit.surface.SurfacePro
import ru.maksonic.beresta.common.ui_theme.Theme
import ru.maksonic.beresta.common.ui_theme.provide.AppRipple
import ru.maksonic.beresta.common.ui_theme.provide.dp16
import ru.maksonic.beresta.common.ui_theme.provide.dp24
import ru.maksonic.beresta.common.ui_theme.provide.dp32
import ru.maksonic.beresta.common.ui_theme.provide.dp8
import ru.maksonic.beresta.feature.folders_list.ui.api.FolderUi
import ru.maksonic.beresta.feature.folders_list.ui.api.FoldersChipsRowUiApi
import ru.maksonic.beresta.feature.folders_list.ui.api.FoldersFeature
import ru.maksonic.beresta.platform.elm.core.ElmBaseModel

/**
 * @Author maksonic on 17.10.2023
 */
class FoldersChipsRowUiCore : FoldersChipsRowUiApi {

    @Composable
    override fun Widget(
        state: ElmBaseModel,
        sorter: State<FilterDataSorter<FolderUi>>,
        isColoredBackground: State<Boolean>,
        onAddNewChipClicked: () -> Unit,
        onRetryFetchChipsClicked: () -> Unit,
        onChipClicked: (id: Long) -> Unit,
        modifier: Modifier
    ) {
        Container(
            dataState = state,
            sorter = sorter,
            isColoredBackground = isColoredBackground,
            onAddNewChipClicked = onAddNewChipClicked,
            onRetryFetchChipsClicked = onRetryFetchChipsClicked,
            onChipClicked = onChipClicked,
            modifier = modifier
        )
    }
}

@Composable
private fun Container(
    dataState: ElmBaseModel,
    sorter: State<FilterDataSorter<FolderUi>>,
    isColoredBackground: State<Boolean>,
    onAddNewChipClicked: () -> Unit,
    onRetryFetchChipsClicked: () -> Unit,
    onChipClicked: (id: Long) -> Unit,
    modifier: Modifier
) {
    val tonal = animateDpAsState(
        if (isColoredBackground.value) Theme.tonal.level2 else Theme.tonal.level0,
        tween(Theme.animVelocity.common), label = ""
    )

    SurfacePro(
        tonalElevation = tonal.value,
        modifier = modifier
            .statusBarsPadding()
            .padding(top = Theme.size.topBarSmallHeight)
            .height(Theme.size.noteChipsContainerHeight)
    ) { resultColor ->
        when {
            dataState.isLoading -> ContentLoading()
            dataState.successAfterLoading -> {
                Content(
                    sorter = sorter,
                    addButtonBackgroundColor = resultColor,
                    onAddNewChipClicked = onAddNewChipClicked,
                    onChipClicked = onChipClicked
                )
            }

            dataState.failAfterLoading -> {
                ContentError(
                    backgroundColor = resultColor,
                    onAddNewChipClicked = onAddNewChipClicked,
                    onRetryFetchChipsClicked = onRetryFetchChipsClicked
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun Content(
    sorter: State<FilterDataSorter<FolderUi>>,
    addButtonBackgroundColor: Color,
    onAddNewChipClicked: () -> Unit,
    onChipClicked: (id: Long) -> Unit,
    modifier: Modifier = Modifier,
) {
    val lazyRowState = rememberLazyListState()
    val currentSelectedId = rememberUpdatedState(FoldersFeature.currentSelected)

    LaunchedEffect(currentSelectedId.value) {
        lazyRowState.autoScrollToCurrentChip(
            sorter.value.sortedByFilterList, currentSelectedId.value
        )
    }

    ConstraintLayout(
        modifier
            .fillMaxSize()
            .padding(end = dp32)
    ) {
        val (chipsRow, addBtn) = createRefs()

        OverscrollBehavior {
            CompositionLocalProvider(LocalRippleTheme provides AppRipple) {

                LazyRow(
                    state = lazyRowState,
                    horizontalArrangement = Arrangement.spacedBy(dp8),
                    contentPadding = PaddingValues(start = dp16, end = dp24),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.constrainAs(chipsRow) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                ) {
                    items(
                        items = sorter.value.sortedByFilterList,
                        key = { chip -> chip.id }) { item ->
                        ChipItem(
                            item = item,
                            isSelected = item.id == currentSelectedId.value,
                            onChipClicked = { onChipClicked(item.id) },
                            modifier = modifier.animateItemPlacement()
                        )
                    }
                }
            }
        }

        ButtonCircle(
            backgroundColor = addButtonBackgroundColor,
            onClick = onAddNewChipClicked,
            icon = AppIcon.Add,
            modifier = modifier.constrainAs(addBtn) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(chipsRow.end)
                end.linkTo(chipsRow.end)
            }
        )
    }
}

private suspend fun LazyListState.autoScrollToCurrentChip(chips: List<FolderUi>, id: Long) {
    val currentIndex = chips.indexOf(chips.find { it.id == id })
    val targetIndex = if (currentIndex <= 0) 0 else currentIndex - 1
    this.animateScrollToItem(targetIndex)
}
