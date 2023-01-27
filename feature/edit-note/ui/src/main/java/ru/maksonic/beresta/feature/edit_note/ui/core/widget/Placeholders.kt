package ru.maksonic.beresta.feature.edit_note.ui.core.widget

import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import ru.maksonic.beresta.feature.edit_note.ui.R
import ru.maksonic.beresta.ui.theme.color.secondary
import ru.maksonic.beresta.ui.theme.component.TextDesign

/**
 * @Author maksonic on 27.01.2023
 */
@Composable
internal fun TitlePlaceholder() {
    Text(
        text = stringResource(R.string.placeholder_title),
        style = TextDesign.header.copy(color = secondary)
    )
}

@Composable
internal fun MessagePlaceholder() {
    Text(
        text = stringResource(R.string.placeholder_message),
        style = TextDesign.main.copy(color = secondary)
    )
}