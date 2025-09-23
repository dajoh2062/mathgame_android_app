package com.example.dajoh2062_oblig1.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dajoh2062_oblig1.R
import com.example.dajoh2062_oblig1.ui.theme.Dajoh2062_oblig1Theme

@Composable
fun PreferencesHeader(modifier: Modifier = Modifier){
    Text(
        text = stringResource(id = R.string.preferences),
        fontSize = 32.sp,
        fontWeight = FontWeight.Bold,
        modifier = modifier.padding(bottom = 16.dp)
    )
}

@Preview
@Composable
fun preferencesHeaderPreview(){
    Dajoh2062_oblig1Theme {
        PreferencesHeader()
    }
}