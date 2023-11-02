package ru.maksonic.beresta.feature.ui.edit_note.core.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.maksonic.beresta.common.ui_theme.colors.secondaryContainer
import ru.maksonic.beresta.feature.ui.edit_note.core.CurrentSheetContent
import ru.maksonic.beresta.feature.ui.edit_note.core.Model
import ru.maksonic.beresta.feature.ui.edit_note.core.screen.Send

/**
 * @Author maksonic on 15.09.2023
 */
@Composable
internal fun MultipleModalBottomSheetContent(
    model: Model,
    send: Send,
    modifier: Modifier = Modifier,
) {

    Box(modifier.background(secondaryContainer)) {
        when (model.modalSheet.content) {
            CurrentSheetContent.NOTHING -> Box(modifier.size(1.dp))
        }
    }
}