package ru.maksonic.beresta.common.ui_kit.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.maksonic.beresta.common.ui_theme.colors.onPrimary
import ru.maksonic.beresta.common.ui_theme.colors.onTertiaryContainer
import ru.maksonic.beresta.common.ui_theme.colors.tertiaryContainer
import ru.maksonic.beresta.common.ui_theme.colors.transparent
import ru.maksonic.beresta.common.ui_theme.provide.PrimaryRipple
import ru.maksonic.beresta.common.ui_theme.typography.TextDesign
import ru.maksonic.beresta.common.ui_theme.BerestaTheme
import ru.maksonic.beresta.common.ui_theme.Theme

/**
 * @Author maksonic on 15.11.2022
 */
@Composable
fun ButtonPrimary(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    title: String,
    titleColor: Color = onTertiaryContainer,
    backgroundColor: Color = tertiaryContainer,
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

    CompositionLocalProvider(LocalRippleTheme provides PrimaryRipple) {

        Button(
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
                contentColor = onTertiaryContainer
            ),
            elevation = elevation,
            shape = shape,
            border = border,
            modifier = modifier.height(Theme.size.btnPrimaryHeight)
        ) {
            Text(text = title, color = titleColor, style = TextDesign.labelLarge.copy(onPrimary))
        }
    }
}

@Preview
@Composable
private fun PrimaryButtonPreview() {
    BerestaTheme {
        ButtonPrimary(onClick = {}, title = "Button title")
    }
}