package ru.maksonic.beresta.feature.hidden_notes_dialog.ui.core.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import ru.maksonic.beresta.common.ui_kit.helpers.modifier.gesturesDisabled
import ru.maksonic.beresta.common.ui_theme.colors.outline
import ru.maksonic.beresta.common.ui_theme.colors.primary
import ru.maksonic.beresta.common.ui_theme.provide.dp16
import ru.maksonic.beresta.common.ui_theme.typography.TextDesign
import ru.maksonic.beresta.feature.hidden_notes_dialog.ui.core.core.Model
import ru.maksonic.beresta.feature.hidden_notes_dialog.ui.core.core.Msg
import ru.maksonic.beresta.feature.hidden_notes_dialog.ui.core.Send

/**
 * @Author maksonic on 02.08.2023
 */
@Composable
internal fun PinInput(model: Model, send: Send, modifier: Modifier = Modifier) {
    BasicTextField(
        value = model.input,
        enabled = false,
        readOnly = true,
        onValueChange = { send(Msg.Inner.UpdatedInput(it.toInt())) },
        singleLine = true,
        modifier = modifier
            .padding(dp16)
            .gesturesDisabled(true),
        decorationBox = {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                repeat(5) { index -> PinItem(index, model) }
            }
        }
    )
}

@Composable
private fun PinItem(index: Int, model: Model) {
    val isNotFilled = rememberUpdatedState(model.input.length <= index)
    val color = if (isNotFilled.value) outline else primary
    val itemValue = if (isNotFilled.value) "●" else {
        if (model.pinPrivacy.isVisibleWhenInputProcess) model.input[index].toString() else "●"
    }

    Text(
        text = itemValue,
        style = TextDesign.headlineMedium.copy(letterSpacing = 18.sp, fontWeight = FontWeight.Bold),
        color = color,
        textAlign = TextAlign.Center
    )
}