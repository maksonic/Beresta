package ru.maksonic.beresta.ui.widget.bar

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.maksonic.beresta.ui.theme.color.onSnack
import ru.maksonic.beresta.ui.theme.color.snack
import ru.maksonic.beresta.ui.theme.component.TextDesign
import ru.maksonic.beresta.ui.theme.component.dp8

/**
 * @Author maksonic on 15.04.2023
 */
@Composable
fun SnackBarInfo(modifier: Modifier = Modifier, message: String) {
    Snackbar(
        containerColor = snack,
        modifier = modifier.padding(start = dp8, end = dp8, bottom = dp8)
    ) {
        Text(text = message, style = TextDesign.bodyPrimary.copy(color = onSnack))
    }
}