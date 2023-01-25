package ru.maksonic.beresta.feature.notes_list.ui.state

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.flow.MutableStateFlow
import ru.maksonic.beresta.feature.notes_list.api.feature.NotesSharedState
import ru.maksonic.beresta.feature.notes_list.api.feature.isColoredMainTopBar
import ru.maksonic.beresta.feature.notes_list.api.feature.isVisibleMainTopBar
import ru.maksonic.beresta.feature.notes_list.ui.R
import ru.maksonic.beresta.ui.theme.BerestaTheme
import ru.maksonic.beresta.ui.theme.component.TextDesign
import ru.maksonic.beresta.ui.theme.component.dp16

/**
 * @Author maksonic on 26.12.2022
 */

@Preview(showBackground = true)
@Composable
private fun EmptyNotesViewStatePreview() {
    BerestaTheme {
        EmptyNotesViewState(mutableSharedNotesState = MutableStateFlow(NotesSharedState()))
    }
}

@Composable
internal fun EmptyNotesViewState(
    mutableSharedNotesState: MutableStateFlow<NotesSharedState>,
    modifier: Modifier = Modifier
) {
    LaunchedEffect(Unit) {
        mutableSharedNotesState.apply {
            isColoredMainTopBar(true)
            isVisibleMainTopBar(true)
        }
    }
    Box(
        modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.ic_placeholder_empty_notes),
                contentDescription = ""
            )
            Text(
                text = stringResource(id = R.string.title_empty_notes),
                style = TextDesign.title,
                modifier = modifier.padding(top = dp16)
            )
        }
    }
}