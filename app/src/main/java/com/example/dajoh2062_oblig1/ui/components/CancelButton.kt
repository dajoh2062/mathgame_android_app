package com.example.dajoh2062_oblig1.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.dajoh2062_oblig1.R


//Avbryt-knapp som brukes for å avslutte preferanser skjermen uten å lagre eventuelle endringer.
@Composable
fun CancelButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,


) {

    OutlinedButton(
        onClick = onClick,
        modifier = modifier
            .padding(8.dp)
    ) {
        Text(text = stringResource(R.string.quit_pref))
    }
}
