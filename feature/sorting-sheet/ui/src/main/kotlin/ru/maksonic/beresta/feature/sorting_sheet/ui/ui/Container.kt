package ru.maksonic.beresta.feature.sorting_sheet.ui.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.koin.androidx.compose.koinViewModel
import ru.maksonic.beresta.feature.sorting_sheet.api.ListSortUiState
import ru.maksonic.beresta.feature.sorting_sheet.ui.core.Msg
import ru.maksonic.beresta.feature.sorting_sheet.ui.core.SortingSheetSandbox
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.ui.theme.component.TextDesign
import ru.maksonic.beresta.ui.theme.component.dp16

/**
 * @Author maksonic on 06.07.2023
 */
internal typealias SendMessage = (Msg) -> Unit

@Composable
internal fun Container(
    hideSheet: () -> Unit,
    listSortUiState: State<ListSortUiState>,
    modifier: Modifier = Modifier,
    sandbox: SortingSheetSandbox = koinViewModel()
) {
    Column(
        modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(bottom = dp16), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = text.sortNotesSheet.titleSheet,
            style = TextDesign.topBar,
            modifier = modifier.padding(bottom = dp16)
        )

        Content(
            send = sandbox::send,
            sortState = listSortUiState,
            hideSheet = hideSheet,
            modifier = modifier
        )
    }
}