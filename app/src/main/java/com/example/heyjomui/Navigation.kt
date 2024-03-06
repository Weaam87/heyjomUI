package com.example.heyjomui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import screens.VirtualRunDetailScreen
import screens.VirtualRunsScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "virtualRunDetailScreen") {
        composable("virtualRunsScreen") { VirtualRunsScreen(navController) }
        composable("virtualRunDetailScreen") { VirtualRunDetailScreen(navController) }
    }
}