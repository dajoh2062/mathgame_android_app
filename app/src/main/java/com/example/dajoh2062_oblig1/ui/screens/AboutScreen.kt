package com.example.dajoh2062_oblig1.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.dajoh2062_oblig1.ui.components.AboutHeader
import com.example.dajoh2062_oblig1.ui.components.AboutText
import com.example.dajoh2062_oblig1.ui.components.BackToStartButton
import com.example.dajoh2062_oblig1.ui.theme.Dajoh2062_oblig1Theme

@Composable
fun AboutScreen(
    navController: NavController, modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Dajoh2062_oblig1Theme {
            Spacer(modifier = Modifier.height(16.dp))
            AboutHeader()
            AboutText()
            Spacer(modifier = Modifier.weight(1f))
            BackToStartButton(navController = navController)
            Spacer(modifier = Modifier.height(16.dp))

        }
    }
}

@Preview(showBackground = true)
@Composable
fun AboutScreenPreview(){
    val navController = rememberNavController()
    Dajoh2062_oblig1Theme {
        AboutScreen(navController = navController, modifier = Modifier)
    }

}