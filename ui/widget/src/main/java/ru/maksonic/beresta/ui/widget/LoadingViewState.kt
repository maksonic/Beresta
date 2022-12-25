package ru.maksonic.beresta.ui.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ru.maksonic.beresta.ui.theme.BerestaTheme
import ru.maksonic.beresta.ui.theme.color.background

/**
 * @Author maksonic on 25.12.2022
 */
@Preview
@Composable
private fun LoadingVieStatePreview() {
    BerestaTheme {
        LoadingViewState()
    }
}

@Composable
fun LoadingViewState(modifier: Modifier = Modifier) {
    Box(
        modifier
            .background(background)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}