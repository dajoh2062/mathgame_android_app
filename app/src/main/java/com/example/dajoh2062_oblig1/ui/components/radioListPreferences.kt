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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dajoh2062_oblig1.R
import com.example.dajoh2062_oblig1.ui.theme.Dajoh2062_oblig1Theme
import com.example.dajoh2062_oblig1.viewmodel.PreferencesViewModel

@Composable
fun RadioListPreferences(modifier: Modifier = Modifier, list: List<Int>, numberOfTasks: Int) {
    val options =list
    var selectedOption by remember { mutableIntStateOf(numberOfTasks) }

    Column(modifier = modifier.padding(16.dp)) {
        options.forEach { n ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            ) {
                RadioButton(
                    selected = (n == selectedOption),
                    onClick = { selectedOption = n }
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = n.toString())
            }
        }
    }
}


@Preview
@Composable
fun RadioListPreferencesPreview(){
    Dajoh2062_oblig1Theme {
        RadioListPreferences(modifier = Modifier, list = listOf(5, 10, 15),5)
    }
}
