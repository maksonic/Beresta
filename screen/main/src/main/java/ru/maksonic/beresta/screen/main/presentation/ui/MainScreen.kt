package ru.maksonic.beresta.screen.main.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import ru.maksonic.beresta.navigation.router.router.MainScreenRouter

/**
 * @Author maksonic on 15.02.2023
 */
@Composable
fun MainScreen(router: MainScreenRouter) {
    Content(router)
}

@Composable
private fun Content(router: MainScreenRouter, modifier: Modifier = Modifier) {
    Box(
        modifier
            .fillMaxSize()
            .background(Color.Cyan)) {

        Button(onClick = { router.toSettings() }) {

        }
    }
}