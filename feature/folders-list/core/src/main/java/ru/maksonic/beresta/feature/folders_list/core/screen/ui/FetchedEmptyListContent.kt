package ru.maksonic.beresta.feature.folders_list.core.screen.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import ru.maksonic.beresta.feature.folders_list.core.R
import ru.maksonic.beresta.ui.theme.component.TextDesign
import ru.maksonic.beresta.ui.theme.component.dp16

/**
 * @Author maksonic on 03.04.2023
 */
@Composable
internal fun FetchedEmptyListContent(modifier: Modifier = Modifier) {
    Box(
        modifier
            .fillMaxSize()
            .systemBarsPadding(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.empty_folder_image),
                contentDescription = ""
            )
            Text(
                text = "Folders not found",
                style = TextDesign.title,
                modifier = modifier.padding(top = dp16)
            )
        }
    }
}