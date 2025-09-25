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

// Startknapp som starter en ny instans av et spill. Knappen er stor og bold. Knappen samsvarer
// med formatet til de andre knappene på samme side for å gjøre den enkel å lese.
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

