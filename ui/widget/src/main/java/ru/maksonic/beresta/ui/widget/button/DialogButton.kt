package ru.maksonic.beresta.ui.widget.button

import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.onPrimary
import ru.maksonic.beresta.ui.theme.color.onTertiary
import ru.maksonic.beresta.ui.theme.color.primary
import ru.maksonic.beresta.ui.theme.color.secondaryContainer
import ru.maksonic.beresta.ui.theme.component.Shape

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
    val disabledElevation = Theme.elevation.Level0
    var isEnabled by rememberSaveable {
        mutableStateOf(true)
    }
    val backgroundColor = if (isDismiss) secondaryContainer else primary
    val titleColor = if (isDismiss) onTertiary else onPrimary

    Button(
        onClick = {
            scope.launch {
                isEnabled = false
                action.invoke()
                delay(clickTimeOut)
                isEnabled = true
            }
        },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = backgroundColor,
            contentColor = onPrimary
        ),
        shape = Shape.cornerNormal,
        elevation = ButtonDefaults.elevation(
            defaultElevation = disabledElevation,
            pressedElevation = disabledElevation,
            disabledElevation = disabledElevation
        ),
        modifier = modifier
    ) {
        Text(text = title, color = titleColor)
    }
}
