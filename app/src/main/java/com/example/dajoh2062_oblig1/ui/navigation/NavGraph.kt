package com.example.dajoh2062_oblig1.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dajoh2062_oblig1.ui.screens.AboutScreen
import com.example.dajoh2062_oblig1.ui.screens.GameScreen
import com.example.dajoh2062_oblig1.ui.screens.PreferencesScreen

import com.example.dajoh2062_oblig1.ui.screens.StartScreen

// Navigasjonsgraf for applikasjonen. Inneholder alle skjermbildene og rutene deres.
// Bruker NavHostController til å navigere mellom skjermbildene, som vist i canvas materialet.

@Composable
fun MyApp(){
    val navController = rememberNavController()
    NavigationGraph(navController = navController)
}

@Composable
fun NavigationGraph(navController: NavHostController) {
    // Første siden er start-skjermen.
    NavHost(navController = navController, startDestination = "start")
    {

        // Navigasjonsruter til de fire skjermene i prosjektet.
        composable("start") {
            StartScreen(navController = navController)
        }
        composable("preferences") {
            PreferencesScreen(navController = navController)
        }
        composable("about") {
            AboutScreen(navController = navController)
        }
        composable("game") {
            GameScreen(navController = navController)
        }
    }
}