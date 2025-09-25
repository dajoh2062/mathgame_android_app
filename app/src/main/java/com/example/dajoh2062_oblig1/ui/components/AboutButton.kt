package com.example.dajoh2062_oblig1.ui.components

import com.example.dajoh2062_oblig1.R
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.dajoh2062_oblig1.ui.theme.Dajoh2062_oblig1Theme

@Composable
fun AboutButton(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Dajoh2062_oblig1Theme {
        OutlinedButton(
            onClick = {navController.navigate("about")},
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),

            ) {
            Text(
                text = stringResource(id = R.string.about),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
    }
}}
