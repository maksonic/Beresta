package ru.maksonic.beresta.screen.settings.tags.ui.widget

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import ru.maksonic.beresta.common.ui_kit.icons.AppIcon
import ru.maksonic.beresta.common.ui_kit.icons.Close
import ru.maksonic.beresta.common.ui_kit.icons.Delete
import ru.maksonic.beresta.common.ui_kit.icons.Edit
import ru.maksonic.beresta.common.ui_kit.sheet.ModalSheetItem
import ru.maksonic.beresta.common.ui_theme.provide.dp16
import ru.maksonic.beresta.common.ui_theme.provide.dp24
import ru.maksonic.beresta.common.ui_theme.typography.TextDesign
import ru.maksonic.beresta.language_engine.shell.provider.text

/**
 * @Author maksonic on 13.11.2023
 */
@Composable
internal fun ModalSheetContent(
    currentClickedTagTitle: String,
    hideSheet: () -> Unit,
    onRenameClicked: () -> Unit,
    onDeleteClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        if (currentClickedTagTitle.isNotBlank()) {
            Text(
                text = currentClickedTagTitle,
                style = TextDesign.headlineSmall.copy(textAlign = TextAlign.Center),
                modifier = modifier
                    .fillMaxWidth()
                    .padding(dp16)
            )
        }
        ModalSheetItem(AppIcon.Edit, text.shared.btnTitleEdit, onRenameClicked)
        ModalSheetItem(AppIcon.Delete, text.shared.btnTitleDelete, onDeleteClicked)
        ModalSheetItem(AppIcon.Close, text.shared.btnTitleClose, hideSheet)
        Spacer(modifier.size(dp24))
    }
}