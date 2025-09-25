package com.example.dajoh2062_oblig1.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.dajoh2062_oblig1.R


// Lagre-knapp som brukes i sammenheng med preferanser.
@Composable
fun SaveButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,

) {
    Button(
        onClick = onClick,
        modifier = modifier
            .padding(8.dp)
    ) {
        Text(text = stringResource(R.string.save_pref))
    }
}