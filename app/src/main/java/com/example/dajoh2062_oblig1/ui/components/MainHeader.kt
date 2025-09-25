package com.example.dajoh2062_oblig1.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dajoh2062_oblig1.R

//Hoved-overskrift for startsiden. Stor skrift og bold gjør det lett å lese. Legger også til avstand
// under overskriften mellom andre komponenter.
@Composable
fun MainHeader(modifier: Modifier = Modifier){

    Text(
        text = stringResource(id = R.string.start_header),
        fontSize = 32.sp,
        fontWeight = FontWeight.Bold,
        modifier = modifier.padding(bottom = 16.dp)
    )

}