package ru.maksonic.beresta.navigation.router

import androidx.navigation.NavHostController

/**
 * @Author maksonic on 15.11.2022
 */
abstract class AbstractNavigator {
    lateinit var navController: NavHostController

    fun backPressed() = navController.popBackStack()

    fun navigate(destination: String) {
        if (destination != navController.currentDestination?.route)
            navController.navigate(destination) {
                popUpTo(Destination.Onboarding.route) {
                    inclusive = true
                }
            }
        else return
    }

    fun getStringArgument(key: String): String =
        navController.currentBackStackEntry?.arguments?.getString(key) ?: ""

    fun getLongArgument(key: String): Long =
        navController.currentBackStackEntry?.arguments?.getLong(key) ?: 0
}