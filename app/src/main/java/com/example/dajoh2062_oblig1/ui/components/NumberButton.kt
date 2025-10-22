package com.example.dajoh2062_oblig1.ui.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Komponent som viser en tallknapp i en Row, der tallet vises som tekst og en funksjon kalles ved klikk.
// RowScope gjør at knappen bare kan brukes innenfor en Row{}

@Composable
fun RowScope.NumBtn(n: Int, enabled: Boolean, onClick: (Int) -> Unit) {
    Button(
        onClick = { onClick(n) },
        enabled = enabled,
        modifier = Modifier
            .weight(1f, fill = false)
            .sizeIn(minWidth = 108.dp, minHeight = 72.dp)
    ) {
        Text(
            text = "$n",
            fontSize = 24.sp
        )

    }
}
