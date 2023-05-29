package ru.maksonic.beresta.ui.widget

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.maksonic.beresta.ui.theme.BerestaTheme
import ru.maksonic.beresta.ui.theme.color.background
import ru.maksonic.beresta.ui.theme.color.tertiaryContainer

/**
 * @Author maksonic on 25.12.2022
 */
@Composable
fun LoadingViewState(modifier: Modifier = Modifier) {
    SurfacePro(color = background) {
        Box(modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator(color = tertiaryContainer, modifier = Modifier.size(60.dp))
        }
    }
}

@Preview
@Composable
private fun LoadingVieStatePreview() {
    BerestaTheme {
        LoadingViewState()
    }
}