package ru.maksonic.beresta.ui.widget.bar

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.maksonic.beresta.ui.theme.color.onSnack
import ru.maksonic.beresta.ui.theme.color.onSnackContainer
import ru.maksonic.beresta.ui.theme.color.snack
import ru.maksonic.beresta.ui.theme.component.TextDesign
import ru.maksonic.beresta.ui.theme.component.dp4
import ru.maksonic.beresta.ui.theme.component.dp8
import ru.maksonic.beresta.ui.widget.functional.noRippleClickable

/**
 * @Author maksonic on 10.04.2023
 */
@Composable
fun SnackBarAction(
    modifier: Modifier = Modifier,
    message: String,
    actionTitle: String,
    onClick: () -> Unit
) {
    Snackbar(
        containerColor = snack,
        modifier = modifier.padding(start = dp8, end = dp8, bottom = dp8),
        action = {
            Text(
                text = actionTitle,
                style = TextDesign.captionNormal.copy(color = onSnackContainer),
                modifier = Modifier
                    .padding(dp4)
                    .noRippleClickable { onClick() }
            )
        }) {
        Text(text = message, style = TextDesign.bodyPrimary.copy(color = onSnack))
    }
}