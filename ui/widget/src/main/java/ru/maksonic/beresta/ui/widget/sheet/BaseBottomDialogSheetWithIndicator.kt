package ru.maksonic.beresta.ui.widget.sheet

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.maksonic.beresta.ui.theme.BerestaTheme
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.tertiary
import ru.maksonic.beresta.ui.theme.color.tertiaryContainer
import ru.maksonic.beresta.ui.theme.component.dp16

/**
 * @Author maksonic on 19.02.2023
 */
@Composable
fun BaseBottomDialogSheetWithIndicator(
    isVisibleSheet: Boolean,
    hideSheet: () -> Unit,
    modifier: Modifier = Modifier,
    dialogSheetContent: @Composable () -> Unit,
) {
    BackHandler(isVisibleSheet) {
        hideSheet()
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .systemBarsPadding()
            .padding(start = dp16, end = dp16, bottom = dp16)
            .clip(Theme.shape.cornerExtra)
            .background(tertiaryContainer)
            .padding(start = dp16, end = dp16),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SheetIndicator()
        dialogSheetContent()
    }
}

@Composable
private fun SheetIndicator(modifier: Modifier = Modifier, tint: Color = tertiary) {
    Box(
        modifier
            .fillMaxWidth()
            .height(Theme.widgetSize.minimumTouchTargetSize),
        contentAlignment = Alignment.TopCenter
    ) {
        Box(
            modifier
                .padding(top = dp16)
                .clip(CircleShape)
                .size(32.dp, 4.dp)
                .background(tint)
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun BaseBottomDialogSheetWithIndicatorPreview() {

    BerestaTheme {
        BaseBottomDialogSheetWithIndicator(isVisibleSheet = true, hideSheet = {}) {}
    }
}