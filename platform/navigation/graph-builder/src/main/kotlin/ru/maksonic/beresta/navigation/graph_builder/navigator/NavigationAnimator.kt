package ru.maksonic.beresta.navigation.graph_builder.navigator

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.navigation.NavBackStackEntry

/**
 * @Author maksonic on 18.07.2023
 */
internal typealias AnimScope = AnimatedContentTransitionScope<NavBackStackEntry>

interface NavigationAnimator {

    fun slideIntoLeft(scope: AnimScope, velocity: Int): EnterTransition
    fun slideIntoRight(scope: AnimScope, velocity: Int): EnterTransition
    fun slideOutLeft(scope: AnimScope, velocity: Int): ExitTransition
    fun slideOutRight(scope: AnimScope, velocity: Int): ExitTransition

    fun slideIntoUp(scope: AnimScope, velocity: Int): EnterTransition
    fun slideOutDown(scope: AnimScope, velocity: Int): ExitTransition

    class Core : NavigationAnimator {
        override fun slideIntoLeft(scope: AnimScope, velocity: Int) = scope.slideIntoContainer(
            AnimatedContentTransitionScope.SlideDirection.Left, tween(velocity)
        )

        override fun slideIntoRight(scope: AnimScope, velocity: Int) = scope.slideIntoContainer(
            AnimatedContentTransitionScope.SlideDirection.Right, tween(velocity)
        )

        override fun slideOutLeft(scope: AnimScope, velocity: Int) = scope.slideOutOfContainer(
            AnimatedContentTransitionScope.SlideDirection.Left, tween(velocity)
        )

        override fun slideOutRight(scope: AnimScope, velocity: Int) = scope.slideOutOfContainer(
            AnimatedContentTransitionScope.SlideDirection.Right, tween(velocity)
        )

        override fun slideIntoUp(scope: AnimScope, velocity: Int) = scope.slideIntoContainer(
            AnimatedContentTransitionScope.SlideDirection.Up, tween(velocity)
        )

        override fun slideOutDown(scope: AnimScope, velocity: Int) = scope.slideOutOfContainer(
            AnimatedContentTransitionScope.SlideDirection.Down, tween(velocity)
        )
    }
}