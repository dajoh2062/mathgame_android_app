package com.example.dajoh2062_oblig1.viewmodel

import com.example.dajoh2062_oblig1.R
import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class GameViewModel(app: Application) : AndroidViewModel(app) {

    data class UiState(
        val questionText: String = "",
        val questionNumber: Int = 0,
        val totalQuestions: Int = 0,
        val score: Int = 0,
        val answered: Boolean = false,
        val finished: Boolean = false,
        val correctAnswer: Int? = null,
        val isAnswerCorrect: Boolean? = null,
        val showQuitDialog: Boolean = false,
        val currentInput: String = ""
    )

    private val prefs = app.getSharedPreferences("preferences", Context.MODE_PRIVATE)
    private val questions: Array<String> = app.resources.getStringArray(R.array.math_questions)
    private val answers: IntArray = app.resources.getIntArray(R.array.math_answers)

    private var order: List<Int> = emptyList()
    private var idx = 0
    private var _score = 0
    private var input: String = ""

    private val _ui = MutableStateFlow(UiState())
    val ui: StateFlow<UiState> = _ui

    init { startNewGame() }

    fun startNewGame() {
        val wanted = prefs.getInt("numberOfTasks", 5).coerceIn(1, questions.size)
        order = (questions.indices).shuffled().take(wanted)
        idx = 0
        _score = 0
        input = ""
        pushState(answered = false)
    }

    private fun pushState(answered: Boolean) {
        val finished = idx >= order.size
        if (finished) {
            _ui.value = UiState(
                questionText = "",
                questionNumber = order.size,
                totalQuestions = order.size,
                score = _score,
                answered = true,
                finished = true,
                showQuitDialog = false,
                currentInput = input
            )
            return
        }

        val qIndex = order[idx]
        _ui.value = UiState(
            questionText = questions[qIndex],
            questionNumber = idx + 1,
            totalQuestions = order.size,
            score = _score,
            answered = answered,
            finished = false,
            showQuitDialog = false,
            currentInput = input
        )
    }

    fun onDigitPressed(digit: Int) {
        if (_ui.value.finished || _ui.value.answered) return
        if (input.length >= 8) return
        input += digit.toString()
        _ui.value = _ui.value.copy(currentInput = input)
    }

    fun clearInput() {
        if (_ui.value.finished || _ui.value.answered) return
        input = ""
        _ui.value = _ui.value.copy(currentInput = input)
    }

    fun onSubmitAnswer() {
        if (_ui.value.finished || _ui.value.answered) return
        val given = input.toIntOrNull()
        val correct = answers[order[idx]]
        val isCorrect = (given != null && given == correct)
        if (isCorrect) _score++
        _ui.value = _ui.value.copy(
            answered = true,
            score = _score,
            isAnswerCorrect = isCorrect,
            correctAnswer = correct
        )
    }

    fun next() {
        if (_ui.value.finished) return
        idx += 1
        input = ""
        pushState(answered = false)
    }

    fun askQuit() { _ui.value = _ui.value.copy(showQuitDialog = true) }
    fun dismissQuit() { _ui.value = _ui.value.copy(showQuitDialog = false) }
}
