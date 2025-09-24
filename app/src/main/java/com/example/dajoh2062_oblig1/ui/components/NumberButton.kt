package com.example.dajoh2062_oblig1.ui.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun RowScope.NumBtn(n: Int, enabled: Boolean, onClick: (Int) -> Unit) {
    Button(
        onClick = { onClick(n) },
        enabled = enabled,
        modifier = Modifier
            .weight(1f, fill = false)
            .sizeIn(minWidth = 72.dp)
    ) { Text("$n") }
}