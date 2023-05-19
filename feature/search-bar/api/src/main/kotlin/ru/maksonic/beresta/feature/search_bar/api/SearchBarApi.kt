package ru.maksonic.beresta.feature.search_bar.api

import androidx.compose.runtime.Composable
import ru.maksonic.beresta.core.SharedUiState

/**
 * @Author maksonic on 21.02.2023
 */
interface SearchBarApi {

    interface Ui {
     val searchBarSharedUiState: SharedUiState<TopSearchBarSharedUiState>

     @Composable
        fun Widget()
    }
}