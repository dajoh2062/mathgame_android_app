package com.example.dajoh2062_oblig1.viewmodel

import com.example.dajoh2062_oblig1.R
import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class GameUiState(
    val questionText: String = "",
    val questionNumber: Int = 0,
    val totalQuestions: Int = 0,
    val score: Int = 0,
    val feedback: String? = null,
    val answered: Boolean = false,
    val finished: Boolean = false,
    val showQuitDialog: Boolean = false
)

class GameViewModel(app: Application) : AndroidViewModel(app) {

    private val prefs: SharedPreferences =
        app.getSharedPreferences("prefs", Context.MODE_PRIVATE)

    private val questions: Array<String> =
        app.resources.getStringArray(R.array.math_questions)

    private val answers: IntArray =
        app.resources.getIntArray(R.array.math_answers)

    private var order: List<Int> = emptyList()
    private var idx = 0
    private var _score = 0

    private val _ui = MutableStateFlow(GameUiState())
    val ui: StateFlow<GameUiState> = _ui

    init { startNewGame() }

    fun startNewGame() {
        val wanted = prefs.getInt("pref_game_len", 5).coerceIn(1, questions.size)
        order = (questions.indices).shuffled().take(wanted)
        idx = 0
        _score = 0
        pushState(feedback = null, answered = false)
    }

    private fun pushState(feedback: String?, answered: Boolean) {
        val finished = idx >= order.size
        if (finished) {
            _ui.value = GameUiState(
                questionText = "",
                questionNumber = order.size,
                totalQuestions = order.size,
                score = _score,
                feedback = "Ferdig! Du fikk $_score av ${order.size}.",
                answered = true,
                finished = true,
                showQuitDialog = false
            )
            return
        }

        val qIndex = order[idx]
        _ui.value = GameUiState(
            questionText = questions[qIndex],
            questionNumber = idx + 1,
            totalQuestions = order.size,
            score = _score,
            feedback = feedback,
            answered = answered,
            finished = false,
            showQuitDialog = false
        )
    }

    fun onDigitPressed(digit: Int) {
        if (_ui.value.finished || _ui.value.answered) return
        val correct = answers[order[idx]]
        val msg =
            if (digit == correct) {
                _score += 1
                "Riktig! Godt jobbet."
            } else {
                "Nesten! Riktig svar er $correct."
            }
        _ui.value = _ui.value.copy(feedback = msg, answered = true, score = _score)
    }

    fun next() {
        if (_ui.value.finished) return
        idx += 1
        pushState(feedback = null, answered = false)
    }

    fun askQuit() { _ui.value = _ui.value.copy(showQuitDialog = true) }
    fun dismissQuit() { _ui.value = _ui.value.copy(showQuitDialog = false) }

    fun confirmQuit(onQuit: () -> Unit) {
        viewModelScope.launch {
            onQuit()
        }
    }
}