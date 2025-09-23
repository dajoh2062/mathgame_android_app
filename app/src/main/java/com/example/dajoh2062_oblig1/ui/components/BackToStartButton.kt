package com.example.dajoh2062_oblig1.ui.components

import androidx.compose.material3.Button
import com.example.dajoh2062_oblig1.R
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController

@Composable
fun BackToStartButton(navController: NavController, modifier: Modifier = Modifier){
    Button(onClick = {navController.navigate("start")}) {
        Text(text = stringResource(id = R.string.go_back_start))
    }

}