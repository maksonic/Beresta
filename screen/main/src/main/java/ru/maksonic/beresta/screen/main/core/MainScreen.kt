package ru.maksonic.beresta.screen.main.core

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import org.koin.androidx.compose.koinViewModel
import ru.maksonic.beresta.ui.theme.BerestaTheme

/**
 * @Author maksonic on 15.12.2022
 */
@Preview
@Composable
private fun MainScreenPreview() {
    BerestaTheme {
        MainScreenContent()
    }
}

@Composable
fun MainScreen(systemUiController: SystemUiController, sandbox: MainSandbox = koinViewModel()) {
    MainScreenContent()
}

@Composable
private fun MainScreenContent(modifier: Modifier = Modifier) {
    Column(modifier.fillMaxSize()) {
        
    }
}