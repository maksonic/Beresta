package ru.maksonic.beresta.ui.widget.sheet

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.ui.theme.color.onSurface
import ru.maksonic.beresta.ui.theme.color.surfaceVariant
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.widget.button.PrimaryButton

/**
 * @Author maksonic on 01.06.2023
 */
@Composable
fun TrashDeleteModalSheetContent(
    hideSheet: () -> Unit,
    onRestoreClicked: () -> Unit,
    onDeleteClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
     Column(modifier.padding(start = dp16, end = dp16)) {
            PrimaryButton(
                action = onRestoreClicked,
                title = text.shared.btnTitleRestore,
                modifier = modifier.fillMaxWidth()
            )
            Spacer(modifier.size(dp16))
            PrimaryButton(
                action = onDeleteClicked,
                title = text.shared.btnTitleDelete,
                modifier = modifier.fillMaxWidth(),
                backgroundColor = surfaceVariant,
                titleColor = onSurface
            )
            Spacer(modifier.size(dp16))
            PrimaryButton(
                action = hideSheet,
                title = text.shared.btnTitleCancel,
                modifier = modifier.fillMaxWidth(),
                backgroundColor = surfaceVariant,
                titleColor = onSurface
            )
            Spacer(modifier.size(dp16))
    }
}