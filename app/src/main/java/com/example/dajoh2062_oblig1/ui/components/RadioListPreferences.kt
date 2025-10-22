package com.example.dajoh2062_oblig1.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


// En komponent som bruker for-liste for å lage radioKnapper til hver av de forskjellige verdiene,
// som tilfellet vårt blir 5, 10 og 15 i preferencesScreen.kt.
@Composable
fun RadioListPreferences(
    modifier: Modifier = Modifier,
    list: List<Int>,
    selectedOption: Int,
    onSelectedChange: (Int) -> Unit
) {
        // kolonne for å plassere radioknapper i hver rad.
        Column(modifier = modifier.padding(16.dp)) {
            // Listen er gitt når man lager ett instans av komponenten.
            list.forEach { n ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    RadioButton(
                        selected = (n == selectedOption),
                        onClick = { onSelectedChange(n) }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = n.toString())
                }
            }
        }
}
