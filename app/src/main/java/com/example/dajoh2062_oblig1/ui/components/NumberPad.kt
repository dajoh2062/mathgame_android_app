package com.example.dajoh2062_oblig1.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun NumberPad(
    enabled: Boolean,
    onClick: (Int) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            for (n in 1..3) NumBtn(n, enabled, onClick)
        }
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            for (n in 4..6) NumBtn(n, enabled, onClick)
        }
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            for (n in 7..9) NumBtn(n, enabled, onClick)
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            NumBtn(0, enabled, onClick)
        }
    }
}
