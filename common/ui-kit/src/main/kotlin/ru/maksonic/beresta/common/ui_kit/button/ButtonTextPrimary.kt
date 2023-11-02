package ru.maksonic.beresta.common.ui_kit.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.maksonic.beresta.common.ui_theme.colors.tertiaryContainer
import ru.maksonic.beresta.common.ui_theme.colors.transparent
import ru.maksonic.beresta.common.ui_theme.typography.TextDesign

/**
 * @Author maksonic on 14.10.2023
 */
@Composable
fun ButtonTextPrimary(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    title: String,
    titleColor: Color = tertiaryContainer,
    backgroundColor: Color = transparent,
    border: BorderStroke = BorderStroke(0.dp, transparent),
    shape: Shape = CircleShape,
    elevation: ButtonElevation = ButtonDefaults.buttonElevation(
        defaultElevation = 0.dp,
        pressedElevation = 0.dp,
        disabledElevation = 0.dp
    ),
    clickTimeOut: Long = 300
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
            containerColor = backgroundColor,
            contentColor = titleColor
        ),
        elevation = elevation,
        shape = shape,
        border = border,
        modifier = modifier
    ) {
        Text(
            text = title,
            color = titleColor,
            style = TextDesign.labelLarge.copy(tertiaryContainer)
        )
    }
}