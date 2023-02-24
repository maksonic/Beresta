package ru.maksonic.beresta.feature.edit_note.core.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import ru.maksonic.beresta.feature.edit_note.core.Msg
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
            .systemBarsPadding()
            .fillMaxSize()
            .noRippleClickable { }) {

        TopAppBarNormal(
            title = "",
            backgroundColor = topBarColor,
            backAction = { send(Msg.Ui.OnBackTopBarClicked) },
        )
    }
}