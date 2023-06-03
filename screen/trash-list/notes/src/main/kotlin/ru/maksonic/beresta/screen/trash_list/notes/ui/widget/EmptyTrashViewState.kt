package ru.maksonic.beresta.screen.trash_list.notes.ui.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.ui.theme.color.background
import ru.maksonic.beresta.ui.theme.component.TextDesign
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.theme.images.AppImage
import ru.maksonic.beresta.ui.theme.images.EmptyTrash

/**
 * @Author maksonic on 02.06.2023
 */
@Composable
internal fun EmptyTrashViewState(modifier: Modifier) {
    val backgroundColor = background
    Box(
        modifier
            .fillMaxSize()
            .drawBehind { drawRect(backgroundColor) },
        contentAlignment = Alignment.Center
    ) {

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(Modifier.weight(0.2f))
            Image(
                imageVector = AppImage.EmptyTrash,
                contentDescription = "",
                modifier = Modifier
                    .weight(0.15f)
                    .aspectRatio(1f)
            )
            Text(
                text = text.trash.messageEmptyTrashNotesList,
                style = TextDesign.title,
                modifier = Modifier.padding(top = dp16)
            )
            Spacer(Modifier.weight(0.4f))
        }
    }
}