package com.example.dajoh2062_oblig1.ui.screens

import com.example.dajoh2062_oblig1.R
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.dajoh2062_oblig1.ui.components.AboutButton
import com.example.dajoh2062_oblig1.ui.components.PreferencesButton
import com.example.dajoh2062_oblig1.ui.components.StartButton
import com.example.dajoh2062_oblig1.ui.components.mainHeader
import com.example.dajoh2062_oblig1.ui.theme.Dajoh2062_oblig1Theme

@Composable
fun StartScreen(navController: NavController, modifier: Modifier = Modifier) {
    Surface(modifier, color = MaterialTheme.colorScheme.background) {
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            mainHeader()
            Spacer(modifier = Modifier.height(16.dp))
            StartButton()
            Spacer(modifier = Modifier.height(16.dp))
            PreferencesButton(navController = navController, modifier = Modifier)
            Spacer(modifier = Modifier.height(16.dp))
            AboutButton()

        }
    }
}

@Preview(showBackground = true)
@Composable
fun StartScreenPreview(){
    val navController = rememberNavController()
    Dajoh2062_oblig1Theme {
        StartScreen(navController = navController, modifier = Modifier)
    }

}