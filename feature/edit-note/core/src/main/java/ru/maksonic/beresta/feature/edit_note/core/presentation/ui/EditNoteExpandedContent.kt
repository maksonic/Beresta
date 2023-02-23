package ru.maksonic.beresta.feature.edit_note.core.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import ru.maksonic.beresta.feature.edit_note.core.Msg
import ru.maksonic.beresta.ui.theme.color.transparent
import ru.maksonic.beresta.ui.widget.SystemStatusBar
import ru.maksonic.beresta.ui.widget.bar.TopAppBarNormal
import ru.maksonic.beresta.ui.widget.functional.noRippleClickable

/**
 * @Author maksonic on 23.02.2023
 */
@Composable
internal fun EditNoteExpandedContent(
    send: SendMessage,
    topBarColor: () -> Color,
    modifier: Modifier = Modifier
) {
    Column(
        modifier
            .fillMaxSize()
            .noRippleClickable { }) {
        val collapsedBackground = transparent

        SystemStatusBar(backgroundColor = { collapsedBackground })

        TopAppBarNormal(
            title = "",
            backgroundColor = topBarColor,
            backAction = { send(Msg.Ui.OnBackTopBarClicked) },
        )
    }
}