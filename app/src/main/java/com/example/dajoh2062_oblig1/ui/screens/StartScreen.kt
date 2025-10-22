package com.example.dajoh2062_oblig1.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.dajoh2062_oblig1.ui.components.AboutButton
import com.example.dajoh2062_oblig1.ui.components.PreferencesButton
import com.example.dajoh2062_oblig1.ui.components.StartButton
import com.example.dajoh2062_oblig1.ui.components.MainHeader
import com.example.dajoh2062_oblig1.ui.theme.Dajoh2062_oblig1Theme


// Start -skjermbildet. Bruker 4 av de komponentene i components mappen; MainHeader, StartButton,
// PreferencesButton og AboutButton. Tar inn navController og modifier som parametre.
@Composable
fun StartScreen(navController: NavController, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
            Column(
                // kolonnenen fyller hele skjermen så mye som mulig og midtstilles.
                modifier = modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Legger til noen mellomrom mellom hver komponent.
                Spacer(modifier = Modifier.height(16.dp))
                MainHeader()
                Spacer(modifier = Modifier.height(16.dp))
                StartButton(navController = navController)
                Spacer(modifier = Modifier.height(16.dp))
                PreferencesButton(navController = navController)
                Spacer(modifier = Modifier.height(16.dp))
                AboutButton(navController = navController)
            }
        }
    }

// Preview composable bare for å lettere utvikle skjermen.
@Preview(showBackground = true)
@Composable
fun StartScreenPreview(){
    val navController = rememberNavController()
    Dajoh2062_oblig1Theme {
        StartScreen(navController = navController, modifier = Modifier)
    }

}