package ru.maksonic.beresta.common.ui_kit.button.dialog

import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.maksonic.beresta.common.ui_kit.helpers.PreviewContainer
import ru.maksonic.beresta.common.ui_theme.colors.onTertiaryContainer
import ru.maksonic.beresta.common.ui_theme.colors.tertiaryContainer
import ru.maksonic.beresta.common.ui_theme.typography.TextDesign

/**
 * @Author maksonic on 18.10.2023
 */
@Composable
fun ButtonDialogPrimary(
    modifier: Modifier = Modifier,
    title: String,
    clickTimeOut: Long = 300,
    onClick: () -> Unit,
) {
    val scope = rememberCoroutineScope()
    var isEnabled by rememberSaveable {
        mutableStateOf(true)
    }

    TextButton(
        onClick = {
            scope.launch {
                isEnabled = false
                onClick.invoke()
                delay(clickTimeOut)
                isEnabled = true
            }
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = tertiaryContainer,
            contentColor = onTertiaryContainer
        ),
        modifier = modifier
    ) {
        Text(text = title, color = onTertiaryContainer, style = TextDesign.labelLarge)
    }
}

@Preview
@Composable
private fun ButtonDialogPrimaryPreview() {
    PreviewContainer {
        ButtonDialogPrimary(title = "Button title") {}
    }
}

