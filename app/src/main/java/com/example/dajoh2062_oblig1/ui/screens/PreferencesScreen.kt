package com.example.dajoh2062_oblig1.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.dajoh2062_oblig1.R
import com.example.dajoh2062_oblig1.ui.components.CancelButton
import com.example.dajoh2062_oblig1.ui.components.PreferencesHeader
import com.example.dajoh2062_oblig1.ui.components.RadioListPreferences
import com.example.dajoh2062_oblig1.ui.components.SaveButton
import com.example.dajoh2062_oblig1.ui.theme.Dajoh2062_oblig1Theme
import com.example.dajoh2062_oblig1.viewmodel.PreferencesViewModel

@Composable
fun PreferencesScreen(navController: NavController, modifier: Modifier = Modifier) {
    val vm: PreferencesViewModel = viewModel()
    // Husker valgt preferanse selv etter rotasjon
    var selected by rememberSaveable { mutableIntStateOf(vm.getCountPreference()) }

    PreferencesScreenContent(
        modifier = modifier,
        options = vm.options,                  // Liste med valg (antall)
        selectedOption = selected,             // Nåværende valgt
        onSelect = { selected = it },          // Når brukeren velger nytt
        onSave = {                             // Lagre og gå tilbake til start
            vm.setCountPreference(selected)
            navController.navigate("start")
        },
        onCancel = { navController.navigate("start") }  // Avbryt uten å lagre
    )
}

@Composable
private fun PreferencesScreenContent(
    modifier: Modifier = Modifier,
    options: List<Int>,
    selectedOption: Int,
    onSelect: (Int) -> Unit,
    onSave: () -> Unit,
    onCancel: () -> Unit
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp) // Avstand mellom elementer
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            PreferencesHeader() // Overskrift/ikon for innstillinger
            Text(text = stringResource(id = R.string.prefs_count_label)) // "Antall oppgaver" tekst

            // Radioknapper for å velge preferanse
            RadioListPreferences(
                list = options,
                selectedOption = selectedOption,
                onSelectedChange = onSelect
            )

            // Knapper for lagre/avbryt
            Row(verticalAlignment = Alignment.CenterVertically) {
                SaveButton(onClick = onSave)
                Spacer(Modifier.width(12.dp))
                CancelButton(onClick = onCancel)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreferencesScreenPreview() {
    Dajoh2062_oblig1Theme {
        // Forhåndsvisning: start med valgt = 10
        var selected by rememberSaveable { mutableIntStateOf(10) }
        PreferencesScreenContent(
            options = listOf(5, 10, 15),
            selectedOption = selected,
            onSelect = { selected = it },
            onSave = {},
            onCancel = {}
        )
    }
}
