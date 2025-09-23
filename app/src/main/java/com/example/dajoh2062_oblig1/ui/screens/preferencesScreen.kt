package com.example.dajoh2062_oblig1.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.dajoh2062_oblig1.R
import com.example.dajoh2062_oblig1.ui.components.AboutButton
import com.example.dajoh2062_oblig1.ui.components.CancelButton
import com.example.dajoh2062_oblig1.ui.components.PreferencesButton
import com.example.dajoh2062_oblig1.ui.components.StartButton
import com.example.dajoh2062_oblig1.ui.components.PreferencesHeader
import com.example.dajoh2062_oblig1.ui.components.RadioListPreferences
import com.example.dajoh2062_oblig1.ui.components.SaveButton
import com.example.dajoh2062_oblig1.ui.theme.Dajoh2062_oblig1Theme
import com.example.dajoh2062_oblig1.viewmodel.PreferencesViewModel

@Composable
fun PreferenecesScreen(navController: NavController, modifier: Modifier = Modifier) {
    val preferencesViewModel: PreferencesViewModel = viewModel()


    var selectedOption by rememberSaveable { mutableIntStateOf(preferencesViewModel.getCountPreference()!!) }
    val options = preferencesViewModel.options

    Surface(modifier, color = MaterialTheme.colorScheme.background) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            PreferencesHeader()
            Text(text = stringResource(id = R.string.prefs_count_label))
            RadioListPreferences(modifier=Modifier, options, selectedOption)
            Row {
                SaveButton(onClick = {preferencesViewModel.setCountPreference(selectedOption)})
                CancelButton(onClick = {navController.navigate("start")})
            }


        }

    }
}

@Preview(showBackground = true)
@Composable
fun PreferencesScreenPreview(){
    val navController = rememberNavController()
    Dajoh2062_oblig1Theme {
        PreferenecesScreen(navController = navController, modifier = Modifier)
    }

}