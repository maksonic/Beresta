package ru.maksonic.beresta.ui.widget.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.onTertiaryContainer
import ru.maksonic.beresta.ui.theme.color.tertiaryContainer
import ru.maksonic.beresta.ui.theme.color.transparent
import ru.maksonic.beresta.ui.theme.component.Shape
import ru.maksonic.beresta.ui.theme.component.TextDesign

/**
 * @Author maksonic on 15.11.2022
 */
@Composable
fun PrimaryButton(
    action: () -> Unit,
    modifier: Modifier = Modifier,
    title: String,
    titleColor: Color = onTertiaryContainer,
    backgroundColor: Color = tertiaryContainer,
    border: BorderStroke = BorderStroke(0.dp, transparent),
    clickTimeOut: Long = 300
) {

    val scope = rememberCoroutineScope()
    val disabledElevation = Theme.elevation.Level0
    var isEnabled by rememberSaveable {
        mutableStateOf(true)
    }

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
            contentColor = onTertiaryContainer
        ),
        shape = Shape.primaryBtn,
        border = border,
        elevation = ButtonDefaults.elevation(
            defaultElevation = disabledElevation,
            pressedElevation = disabledElevation,
            disabledElevation = disabledElevation
        ),
        modifier = modifier.height(Theme.widgetSize.btnPrimaryHeight)
    ) {
        Text(text = title, color = titleColor, style = TextDesign.title)
    }
}
