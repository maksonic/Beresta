package ru.maksonic.beresta.feature.ui.search_bar.core.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ru.maksonic.beresta.common.ui_kit.icons.AppIcon
import ru.maksonic.beresta.common.ui_kit.icons.Colors
import ru.maksonic.beresta.common.ui_theme.provide.dp16
import ru.maksonic.beresta.common.ui_theme.typography.TextDesign
import ru.maksonic.beresta.language_engine.shell.provider.text

/**
 * @Author maksonic on 09.06.2023
 */
@Composable
internal fun EmptySearchResult(modifier: Modifier) {
    Column(
        modifier
            .fillMaxWidth()
            .systemBarsPadding()
        ,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.weight(0.2f))
        Image(
            imageVector = AppIcon.Colors,
            contentDescription = "",
            modifier = modifier
                .weight(0.2f)
                .aspectRatio(1f)
        )
        Text(
            text = text.shared.hintNothingFound,
            style = TextDesign.headlineSmall,
            modifier = modifier.padding(top = dp16)
        )
        Spacer(modifier.weight(0.4f))
    }
}