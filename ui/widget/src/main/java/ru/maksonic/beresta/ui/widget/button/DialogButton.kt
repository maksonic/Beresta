package ru.maksonic.beresta.ui.widget.button

import androidx.compose.material.Text
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.maksonic.beresta.ui.theme.color.onSurface
import ru.maksonic.beresta.ui.theme.color.onTertiaryContainer
import ru.maksonic.beresta.ui.theme.color.surfaceVariant
import ru.maksonic.beresta.ui.theme.color.tertiaryContainer
import ru.maksonic.beresta.ui.theme.component.TextDesign

/**
 * @Author maksonic on 22.01.2023
 */
@Composable
fun DialogButton(
    modifier: Modifier = Modifier,
    title: String,
    clickTimeOut: Long = 300,
    isDismiss: Boolean,
    action: () -> Unit,
    ) {

    val scope = rememberCoroutineScope()
    var isEnabled by rememberSaveable {
        mutableStateOf(true)
    }
    val backgroundColor = if (isDismiss) surfaceVariant else tertiaryContainer
    val titleColor = if (isDismiss) onSurface else onTertiaryContainer

    TextButton(
        onClick = {
            scope.launch {
                isEnabled = false
                action.invoke()
                delay(clickTimeOut)
                isEnabled = true
            }
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = titleColor
        ),
        modifier = modifier
    ) {
        Text(text = title, color = titleColor, style = TextDesign.bodyPrimary)
    }
}
