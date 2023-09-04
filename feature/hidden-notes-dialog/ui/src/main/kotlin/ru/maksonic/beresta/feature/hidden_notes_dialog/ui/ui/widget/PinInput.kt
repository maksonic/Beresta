package ru.maksonic.beresta.feature.hidden_notes_dialog.ui.ui.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import ru.maksonic.beresta.feature.hidden_notes_dialog.ui.core.Model
import ru.maksonic.beresta.feature.hidden_notes_dialog.ui.core.Msg
import ru.maksonic.beresta.feature.hidden_notes_dialog.ui.ui.SendMessage
import ru.maksonic.beresta.ui.theme.color.outline
import ru.maksonic.beresta.ui.theme.color.primary
import ru.maksonic.beresta.ui.theme.component.TextDesign
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.widget.functional.gesturesDisabled

/**
 * @Author maksonic on 02.08.2023
 */
@Composable
internal fun PinInput(model: State<Model>, send: SendMessage, modifier: Modifier = Modifier) {
    BasicTextField(
        value = model.value.input,
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
                repeat(6) { index -> PinItem(index, model) }
            }
        }
    )
}

@Composable
private fun PinItem(index: Int, model: State<Model>) {
    val isVisible = rememberUpdatedState(model.value.pinSecure.isVisiblePin)
    val isNotFilled = rememberUpdatedState(model.value.input.length <= index)
    val color = if (isNotFilled.value) outline else primary
    val itemValue = if (isNotFilled.value) "●" else {
        if (isVisible.value) model.value.input[index].toString() else "●"
    }

    Text(
        text = itemValue,
        style = TextDesign.displaySmall.copy(letterSpacing = 18.sp, fontWeight = FontWeight.Bold),
        color = color,
        textAlign = TextAlign.Center
    )
}