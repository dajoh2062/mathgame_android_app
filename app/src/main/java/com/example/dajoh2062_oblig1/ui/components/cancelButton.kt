package com.example.dajoh2062_oblig1.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CancelButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    text: String = "Cancel"

) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier
            .padding(8.dp)
    ) {
        Text(text)
    }
}

@Preview
@Composable
fun CancelButtonPreview() {
    CancelButton(onClick = {})
}