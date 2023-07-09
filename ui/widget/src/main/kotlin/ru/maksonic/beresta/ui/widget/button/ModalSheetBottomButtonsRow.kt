package ru.maksonic.beresta.ui.widget.button

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.ui.theme.color.onSurface
import ru.maksonic.beresta.ui.theme.color.surfaceVariant
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.theme.component.dp8

/**
 * @Author maksonic on 09.07.2023
 */
@Composable
fun ModalSheetBottomButtonsRow(
    leftTitle: String = text.shared.btnTitleByDefault,
    rightTitle: String = text.shared.btnTitleSave,
    modifier: Modifier = Modifier,
    onLeftClicked: () -> Unit,
    onRightClicked: () -> Unit
) {
    Row(
        modifier
            .fillMaxWidth()
            .padding(bottom = dp16)
    ) {
        PrimaryButton(
            action = onLeftClicked,
            title = leftTitle,
            backgroundColor = surfaceVariant,
            titleColor = onSurface,
            modifier = modifier
                .weight(1f)
                .padding(start = dp16, end = dp8, top = dp16),

            )

        PrimaryButton(
            action = onRightClicked,
            title = rightTitle,
            modifier = modifier
                .weight(1f)
                .padding(start = dp8, end = dp16, top = dp16)
        )
    }
}

