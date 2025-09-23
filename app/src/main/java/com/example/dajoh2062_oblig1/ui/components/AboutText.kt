package com.example.dajoh2062_oblig1.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dajoh2062_oblig1.R

@Composable
fun AboutText(modifier: Modifier = Modifier){
    Text(
        text = stringResource(id = R.string.about_text),
        fontSize = 16.sp,
        modifier = modifier.padding(bottom = 16.dp)
    )
}

