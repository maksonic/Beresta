package ru.maksonic.beresta.feature.sorting_sheet.ui.core.sheet

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.koin.androidx.compose.koinViewModel
import ru.maksonic.beresta.feature.sorting_sheet.domain.SortDataKey
import ru.maksonic.beresta.feature.sorting_sheet.ui.core.Msg
import ru.maksonic.beresta.feature.sorting_sheet.ui.core.SortingSheetSandbox

/**
 * @Author maksonic on 16.10.2023
 */
internal typealias SendMessage = (Msg) -> Unit

@Composable
internal fun Container(
    sortDataKey: SortDataKey,
    modifier: Modifier = Modifier,
    sandbox: SortingSheetSandbox = koinViewModel()
) {
    Column(
        modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        Content(
            sortDataKey = sortDataKey,
            send = sandbox::send,
            modifier = modifier
        )
    }
}