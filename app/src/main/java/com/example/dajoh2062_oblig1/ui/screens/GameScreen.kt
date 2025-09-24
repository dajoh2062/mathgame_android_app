package com.example.dajoh2062_oblig1.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.dajoh2062_oblig1.ui.theme.Dajoh2062_oblig1Theme
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.dajoh2062_oblig1.viewmodel.GameUiState
import com.example.dajoh2062_oblig1.viewmodel.GameViewModel
import com.example.dajoh2062_oblig1.ui.components.NumBtn
import com.example.dajoh2062_oblig1.ui.components.NumberPad

@Composable
fun GameScreen(
    navController: NavController,
    vm: GameViewModel = viewModel(),
    onExitToMenu: () -> Unit = {}

) {
    val uiState = vm.ui.collectAsState(initial = GameUiState()).value

    GameScreenContent(
        ui = uiState,
        onDigit = vm::onDigitPressed,
        onNext = vm::next,
        onAskQuit = vm::askQuit,
        onDismissQuit = vm::dismissQuit,
        onConfirmQuit = { vm.confirmQuit(onExitToMenu) },
        onStartNew = vm::startNewGame,
        onExitToMenu = onExitToMenu
    )
}

@Composable
private fun GameScreenContent(
    ui: GameUiState,
    onDigit: (Int) -> Unit,
    onNext: () -> Unit,
    onAskQuit: () -> Unit,
    onDismissQuit: () -> Unit,
    onConfirmQuit: () -> Unit,
    onStartNew: () -> Unit,
    onExitToMenu: () -> Unit
) {
    BackHandler { onAskQuit() }

    if (ui.showQuitDialog) {
        AlertDialog(
            onDismissRequest = { onDismissQuit() },
            title = { Text("Avslutte spillet?") },
            text = { Text("Hvis du avslutter nå, mister du fremgangen i denne runden.") },
            confirmButton = { Button(onClick = onConfirmQuit) { Text("Avslutt") } },
            dismissButton = { OutlinedButton(onClick = onDismissQuit) { Text("Fortsett") } }
        )
    }

    Surface(Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("Oppgave ${ui.questionNumber}/${ui.totalQuestions}",
                    style = MaterialTheme.typography.titleMedium)
                Text("Poeng: ${ui.score}", style = MaterialTheme.typography.titleMedium)
            }

            Spacer(Modifier.height(24.dp))

            if (ui.finished) {
                Text(
                    text = ui.feedback.orEmpty(),
                    style = MaterialTheme.typography.headlineSmall,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(16.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    Button(onClick = onStartNew) { Text("Spill igjen") }
                    OutlinedButton(onClick = onExitToMenu) { Text("Til meny") }
                }
                return@Surface
            }

            Text(
                text = ui.questionText,
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(24.dp))

            NumberPad(enabled = !ui.answered, onClick = onDigit)

            Spacer(Modifier.height(16.dp))

            ui.feedback?.let {
                Text(it, style = MaterialTheme.typography.titleMedium)
                Spacer(Modifier.height(8.dp))
            }
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                OutlinedButton(onClick = onAskQuit) { Text("Avslutt") }
                Button(onClick = onNext, enabled = ui.answered) { Text("Neste") }
            }
        }
    }
}






@Preview(showBackground = true)
@Composable
fun GameScreenPreview() {
    Dajoh2062_oblig1Theme {
        GameScreenContent(
            ui = GameUiState(
                questionText = "Hva er 7 × 6?",
                questionNumber = 1,
                totalQuestions = 5,
                score = 0,
                feedback = null,
                answered = false,
                finished = false,
                showQuitDialog = false
            ),
            onDigit = {},
            onNext = {},
            onAskQuit = {},
            onDismissQuit = {},
            onConfirmQuit = {},
            onStartNew = {},
            onExitToMenu = {}
        )
    }
}