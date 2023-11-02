package ru.maksonic.beresta.common.ui_kit.widget

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.maksonic.beresta.common.ui_kit.button.ButtonPrimary
import ru.maksonic.beresta.common.ui_theme.colors.onSurface
import ru.maksonic.beresta.common.ui_theme.colors.surfaceVariant
import ru.maksonic.beresta.common.ui_theme.provide.dp16
import ru.maksonic.beresta.common.ui_theme.provide.dp8
import ru.maksonic.beresta.language_engine.shell.provider.text

/**
 * @Author maksonic on 09.07.2023
 */
@Composable
fun ModalSheetBottomButtonsRow(
    modifier: Modifier = Modifier,
    leftTitle: String = text.shared.btnTitleByDefault,
    rightTitle: String = text.shared.btnTitleSave,
    onLeftClicked: () -> Unit,
    onRightClicked: () -> Unit
) {
    Row(
        modifier
            .fillMaxWidth()
            .padding(bottom = dp16)
    ) {
        ButtonPrimary(
            onClick = onLeftClicked,
            title = leftTitle,
            backgroundColor = surfaceVariant,
            titleColor = onSurface,
            modifier = modifier
                .weight(1f)
                .padding(start = dp16, end = dp8, top = dp16),

            )

        ButtonPrimary(
            onClick = onRightClicked,
            title = rightTitle,
            modifier = modifier
                .weight(1f)
                .padding(start = dp8, end = dp16, top = dp16)
        )
    }
}

