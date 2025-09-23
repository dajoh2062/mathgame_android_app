package com.example.dajoh2062_oblig1.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dajoh2062_oblig1.ui.screens.AboutScreen
import com.example.dajoh2062_oblig1.ui.screens.PreferencesScreen

import com.example.dajoh2062_oblig1.ui.screens.StartScreen


@Composable
fun MyApp(modifier: Modifier = Modifier){
    val navController = rememberNavController()
    NavigationGraph(navController = navController)
}

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "start")
    {
        composable("start") {
            StartScreen(navController = navController)
        }
        composable("preferences") {
            PreferencesScreen(navController = navController) }
        composable("about") {
            AboutScreen(navController = navController)
        }
    }
}