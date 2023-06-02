package ru.maksonic.beresta.ui.widget.sheet

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.maksonic.beresta.ui.theme.BerestaTheme
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.*
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.widget.functional.noRippleClickable

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
            .shadow(Theme.elevation.Level5, shape = Theme.shape.cornerExtra)
            .clip(Theme.shape.cornerExtra)
            .background(secondaryContainer)
            .padding(start = dp16, end = dp16)
            .noRippleClickable {  },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SheetIndicator()
        dialogSheetContent()
    }
}

@Composable
private fun SheetIndicator(modifier: Modifier = Modifier, tint: Color = onSecondaryContainer) {
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