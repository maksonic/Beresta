package ru.maksonic.beresta.feature.top_bar_counter.api

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import ru.maksonic.beresta.core.SharedUiState

/**
 * @Author maksonic on 30.04.2023
 */
interface TopBarCounterApi {
    interface Ui {
        val topBarCounterSharedUiState: SharedUiState<TopBarCounterSharedUiState>

        @Composable
        fun Widget(isShowShareIcon: Boolean)

        @OptIn(ExperimentalMaterial3Api::class)
        @Composable
        fun LargeWidget(
            scrollBehavior: TopAppBarScrollBehavior?,
            isSelectionState: Boolean,
            count: Int,
            idleTitle: String,
            onBackClicked: () -> Unit,
            onCancelSelectStateClicked: () -> Unit,
            onSelectAllItemsClicked: () -> Unit,
        )
    }
}