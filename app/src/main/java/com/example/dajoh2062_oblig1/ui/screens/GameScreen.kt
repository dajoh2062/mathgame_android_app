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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.dajoh2062_oblig1.viewmodel.GameUiState
import com.example.dajoh2062_oblig1.viewmodel.GameViewModel
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
        onSubmit = vm::onSubmitAnswer,
        onClear = vm::clearInput,
        onNext = vm::next,
        onAskQuit = vm::askQuit,
        onDismissQuit = vm::dismissQuit,
        onConfirmQuit = {
            vm.dismissQuit()
            navController.navigate("start") {
                popUpTo(0)
                launchSingleTop = true
            }
        },
        onStartNew = vm::startNewGame,
        onExitToMenu = {
            navController.navigate("start") {
                popUpTo(0)
                launchSingleTop = true
            }
        }
    )
}

@Composable
private fun GameScreenContent(
    ui: GameUiState,
    onDigit: (Int) -> Unit,
    onSubmit: () -> Unit,
    onClear: () -> Unit,
    onNext: () -> Unit,
    onAskQuit: () -> Unit,
    onDismissQuit: () -> Unit,
    onConfirmQuit: () -> Unit,
    onStartNew: () -> Unit,
    onExitToMenu: () -> Unit
) {
    BackHandler (enabled = !ui.finished && !ui.showQuitDialog) { onAskQuit() }

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

            Spacer(Modifier.height(12.dp))


            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Ditt svar: ${ui.currentInput}",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.weight(1f)
                )
                OutlinedButton(
                    onClick = onClear,
                    enabled = !ui.answered && ui.currentInput.isNotEmpty()
                ) { Text("Slett") }
                Spacer(Modifier.width(8.dp))
                Button(
                    onClick = onSubmit,
                    enabled = !ui.answered && ui.currentInput.isNotEmpty()
                ) { Text("Sjekk svar") }
            }

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
                showQuitDialog = false,
                currentInput = "42"
            ),
            onDigit = {},
            onSubmit = {},
            onClear = {},
            onNext = {},
            onAskQuit = {},
            onDismissQuit = {},
            onConfirmQuit = {},
            onStartNew = {},
            onExitToMenu = {}
        )
    }
}
