package com.example.dajoh2062_oblig1.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.dajoh2062_oblig1.R
import com.example.dajoh2062_oblig1.ui.components.AboutButton
import com.example.dajoh2062_oblig1.ui.components.PreferencesButton
import com.example.dajoh2062_oblig1.ui.components.StartButton
import com.example.dajoh2062_oblig1.ui.theme.Dajoh2062_oblig1Theme

@Composable
fun PrefernecesScreen(navController: NavController, modifier: Modifier = Modifier) {
    Surface(modifier, color = MaterialTheme.colorScheme.background) {
    }
}

@Preview(showBackground = true)
@Composable
fun PreferencesScreenPreview(){
    val navController = rememberNavController()
    Dajoh2062_oblig1Theme {
        PrefernecesScreen(navController = navController, modifier = Modifier)
    }

}