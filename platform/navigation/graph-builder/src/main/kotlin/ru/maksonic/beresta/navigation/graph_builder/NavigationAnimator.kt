package ru.maksonic.beresta.navigation.graph_builder

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.navigation.NavBackStackEntry

/**
 * @Author maksonic on 18.07.2023
 */
internal typealias AnimScope = AnimatedContentTransitionScope<NavBackStackEntry>

