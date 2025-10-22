package com.example.dajoh2062_oblig1.viewmodel

import android.app.Application
import android.content.Context
import androidx.core.content.edit
import androidx.lifecycle.AndroidViewModel

// Viewmodel for skjermbildet PreferenceScreen.kt.
// Dette er for å separere funksjonalitet fra UI.
class PreferencesViewModel(application: Application) : AndroidViewModel(application) {

    // Mulige valg (antall oppgaver). Brukes i radioknapper.
    val options = listOf(5, 10, 15)

    // Lagre valgt antall i SharedPreferences
    fun setCountPreference(count: Int) {
        val sharedPreferences = getApplication<Application>()
            .getSharedPreferences("preferences", Context.MODE_PRIVATE)

        sharedPreferences.edit {
            putInt("numberOfTasks", count) // nøkkel = "numberOfTasks"
        }
    }

    // Hente lagret antall (default = 5)
    fun getCountPreference(): Int {
        val sharedPreferences = getApplication<Application>()
            .getSharedPreferences("preferences", Context.MODE_PRIVATE)

        return sharedPreferences.getInt("numberOfTasks", 5)
    }
}

