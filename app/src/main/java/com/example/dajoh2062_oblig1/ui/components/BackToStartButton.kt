package com.example.dajoh2062_oblig1.ui.components

import androidx.compose.material3.Button
import com.example.dajoh2062_oblig1.R
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController

//Knapp som brukes for å navigere tilbake til startskjermen i about-skjermen. Brukeren kan også bruke tilbakepilen
// på telefonen for å navigere til start.
@Composable
fun BackToStartButton(navController: NavController) {
        Button(onClick = { navController.navigate("start") }) {
            Text(text = stringResource(id = R.string.go_back_start))
        }
}