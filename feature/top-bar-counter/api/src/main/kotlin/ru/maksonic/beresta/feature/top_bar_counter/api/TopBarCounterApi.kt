package ru.maksonic.beresta.feature.top_bar_counter.api

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState

/**
 * @Author maksonic on 30.04.2023
 */
interface TopBarCounterApi {
    interface Ui {
        val counterState: MutableState<Int>

        @Composable
        fun Widget(
            onCancelClicked: () -> Unit,
            onSelectAllClicked: () -> Unit,
            onShareSelectedClicked: () -> Unit
        )
    }
}