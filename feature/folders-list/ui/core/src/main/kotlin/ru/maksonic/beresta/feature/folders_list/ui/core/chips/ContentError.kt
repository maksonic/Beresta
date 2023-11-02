package ru.maksonic.beresta.feature.folders_list.ui.core.chips

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import ru.maksonic.beresta.common.ui_kit.helpers.modifier.rippledClick
import ru.maksonic.beresta.common.ui_theme.Theme
import ru.maksonic.beresta.common.ui_theme.colors.onTertiaryContainer
import ru.maksonic.beresta.common.ui_theme.colors.tertiaryContainer
import ru.maksonic.beresta.common.ui_theme.provide.dp16
import ru.maksonic.beresta.common.ui_theme.typography.TextDesign
import ru.maksonic.beresta.feature.folders_list.ui.core.widget.AddNewChipButton
import ru.maksonic.beresta.language_engine.shell.provider.text

/**
 * @Author maksonic on 04.09.2023
 */
@Composable
internal fun ContentError(
    backgroundColor: Color,
    onAddNewChipClicked: () -> Unit,
    onRetryFetchChipsClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier
            .fillMaxWidth()
            .padding(start = dp16, end = dp16)
            .height(Theme.size.noteChipsContainerHeight)
            .drawBehind { drawRect(backgroundColor) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "text.errorUi.fetchedChipsNotFoundError",
            style = TextDesign.labelSmall,
            textAlign = TextAlign.Start,
        )

        Box(
            modifier
                .height(Theme.size.chipHeight)
                .padding(start = dp16)
                .clip(CircleShape)
                .background(tertiaryContainer)
                .rippledClick(
                    rippleColor = onTertiaryContainer,
                    onClick = onRetryFetchChipsClicked
                ),
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

        AddNewChipButton(
            backgroundColor = backgroundColor,
            onClick = onAddNewChipClicked
        )
    }
}