package com.example.heyjomui

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import screens.SubmitResultScreen
import screens.VirtualRunDetailScreen
import screens.VirtualRunsScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "virtualRunsScreen") {
        composable("virtualRunsScreen") { VirtualRunsScreen(navController) }
        composable(
            "virtualRunDetailScreen/{eventId}",
            arguments = listOf(navArgument("eventId") { type = NavType.IntType })
        ) { backStackEntry ->
            val eventId = backStackEntry.arguments?.getInt("eventId") ?: -1
            VirtualRunDetailScreen(navController, eventId)
        }
        composable("SubmitResultScreen") { SubmitResultScreen(navController) }
    }
}