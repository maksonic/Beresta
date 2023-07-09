package ru.maksonic.beresta.screen.main.ui

import androidx.compose.runtime.Composable
import ru.maksonic.beresta.navigation.router.router.MainScreenRouter
import ru.maksonic.beresta.screen.main.core.Msg

/**
 * @Author maksonic on 19.06.2023
 */
internal typealias SendMessage = (Msg) -> Unit

@Composable
fun MainScreen(router: MainScreenRouter) {
    Container(router)
}