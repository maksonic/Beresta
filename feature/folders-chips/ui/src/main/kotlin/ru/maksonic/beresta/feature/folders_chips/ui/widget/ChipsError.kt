package ru.maksonic.beresta.feature.folders_chips.ui.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.onTertiaryContainer
import ru.maksonic.beresta.ui.theme.color.tertiaryContainer
import ru.maksonic.beresta.ui.theme.component.TextDesign
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.widget.functional.rippledClick

/**
 * @Author maksonic on 04.09.2023
 */
@Composable
internal fun ChipsError(
    onRetryFetchClicked: () -> Unit,
    onAddNewChipClicked: () -> Unit,
    backgroundColor: State<Color>,
    modifier: Modifier = Modifier
) {
    Row(
        modifier
            .fillMaxWidth()
            .padding(start = dp16, end = dp16)
            .height(Theme.widgetSize.noteChipsContainerHeight)
            .drawBehind { drawRect(backgroundColor.value) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text.errorUi.fetchedChipsNotFoundError,
            style = TextDesign.captionNormal,
            textAlign = TextAlign.Start,
        )

        Box(
            modifier
                .height(Theme.widgetSize.filterChipHeight)
                .padding(start = dp16)
                .clip(CircleShape)
                .background(tertiaryContainer)
                .rippledClick(rippleColor = onTertiaryContainer) { onRetryFetchClicked() },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text.shared.btnTitleRetry,
                color = onTertiaryContainer,
                style = MaterialTheme.typography.labelLarge,
                modifier = modifier.padding(start = dp16, end = dp16)
            )
        }
        Spacer(modifier.weight(1f))

        AddNewChipButton(color = backgroundColor, onClick = onAddNewChipClicked)
    }
}