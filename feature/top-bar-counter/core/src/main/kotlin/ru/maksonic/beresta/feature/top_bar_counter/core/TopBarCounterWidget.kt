package ru.maksonic.beresta.feature.top_bar_counter.core

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import ru.maksonic.beresta.feature.top_bar_counter.api.TopBarCounterApi
import ru.maksonic.beresta.feature.top_bar_counter.api.TopBarCounterSharedUiState

/**
 * @Author maksonic on 30.04.2023
 */
class TopBarCounterWidget : TopBarCounterApi.Ui {
    override val topBarCounterSharedUiState = TopBarCounterSharedUiState.Initial

    @Composable
    override fun Widget(isShowShareIcon: Boolean) {
        NormalTopBarCounterContainer(topBarCounterSharedUiState, isShowShareIcon)
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun LargeWidget(
        scrollBehavior: TopAppBarScrollBehavior?,
        isSelectionState: Boolean,
        count: Int,
        idleTitle: String,
        onBackClicked: () -> Unit,
        onCancelSelectStateClicked: () -> Unit,
        onSelectAllItemsClicked: () -> Unit,
    ) {
        LargeTopBarCounterContainer(
            scrollBehavior = scrollBehavior,
            isSelectionState = isSelectionState,
            count = count,
            idleTitle = idleTitle,
            onBackClicked = onBackClicked,
            onCancelSelectStateClicked = onCancelSelectStateClicked,
            onSelectAllItemsClicked = onSelectAllItemsClicked
        )
    }
}
