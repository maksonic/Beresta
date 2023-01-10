package ru.maksonic.beresta.feature.tasks_list.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import ru.maksonic.beresta.ui.theme.BerestaTheme
import ru.maksonic.beresta.ui.theme.component.TextDesign
import ru.maksonic.beresta.ui.theme.component.dp16

/**
 * @Author maksonic on 26.12.2022
 */
@Preview(showBackground = true)
@Composable
private fun EmptyTasksViewStatePreview() {
    BerestaTheme {
        EmptyTasksViewState()
    }
}

@Composable
internal fun EmptyTasksViewState(modifier: Modifier = Modifier) {
    Box(
        modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.ic_placeholder_empty_tasks),
                contentDescription = ""
            )
            Text(
                text = stringResource(id = R.string.title_empty_tasks),
                style = TextDesign.title,
                modifier = modifier.padding(top = dp16)
            )
        }
    }
}