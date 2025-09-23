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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dajoh2062_oblig1.ui.theme.Dajoh2062_oblig1Theme

@Composable
fun RadioListPreferences(
    modifier: Modifier = Modifier,
    list: List<Int>,
    selectedOption: Int,
    onSelectedChange: (Int) -> Unit
) {
    Column(modifier = modifier.padding(16.dp)) {
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

@Preview
@Composable
fun RadioListPreferencesPreview() {
    Dajoh2062_oblig1Theme {
        var selected by rememberSaveable { mutableIntStateOf(10) }
        RadioListPreferences(
            list = listOf(5, 10, 15),
            selectedOption = selected,
            onSelectedChange = { selected = it }
        )
    }
}
