package com.example.dajoh2062_oblig1.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.core.content.edit

class PreferencesViewModel(application: Application) : AndroidViewModel(application) {

    val options = listOf(5, 10, 15)

    fun setCountPreference(count: Int) {
        val sharedPreferences = getApplication<Application>().getSharedPreferences("preferences", Context.MODE_PRIVATE)
        sharedPreferences.edit {
            putInt("numberOfTasks", count)
        }
    }

    fun getCountPreference(): Int? {
        val sharedPreferences = getApplication<Application>().getSharedPreferences("preferences", Context.MODE_PRIVATE)
        return sharedPreferences.getInt("numberOfTasks", 5)
    }
}

