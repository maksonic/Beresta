package ru.maksonic.beresta.feature.notes_list.core.presentation.ui.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import ru.maksonic.beresta.feature.language_selector.api.provider.text
import ru.maksonic.beresta.feature.notes_list.core.R
import ru.maksonic.beresta.ui.theme.component.TextDesign
import ru.maksonic.beresta.ui.theme.component.dp16

/**
 * @Author maksonic on 22.02.2023
 */
@Composable
internal fun EmptyNotesWidgetContent(modifier: Modifier = Modifier) {
    Box(
        modifier
            .fillMaxSize()
            .systemBarsPadding(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.ic_placeholder_empty_notes),
                contentDescription = ""
            )
            Text(
                text = text.shared.hintNoNotes,
                style = TextDesign.title,
                modifier = modifier.padding(top = dp16)
            )
        }
    }
}