package com.example.heyjomui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import screens.SubmitResultScreen
import screens.VirtualRunDetailScreen
import screens.VirtualRunsScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "SubmitResultScreen") {
        composable("virtualRunsScreen") { VirtualRunsScreen(navController) }
        composable("virtualRunDetailScreen") { VirtualRunDetailScreen(navController) }
        composable("SubmitResultScreen") { SubmitResultScreen(navController) }
    }
}