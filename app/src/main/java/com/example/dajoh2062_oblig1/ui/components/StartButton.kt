package com.example.dajoh2062_oblig1.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.dajoh2062_oblig1.R

@Composable
fun StartButton(navController: NavController, modifier: Modifier = Modifier) {
    Button(
        onClick = {navController.navigate("game") },
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),

        ) {
        Text(
            text = stringResource(id = R.string.start),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

