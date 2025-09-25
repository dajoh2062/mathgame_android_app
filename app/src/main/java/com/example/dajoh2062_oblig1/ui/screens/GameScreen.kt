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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.dajoh2062_oblig1.R
import com.example.dajoh2062_oblig1.viewmodel.GameViewModel
import com.example.dajoh2062_oblig1.ui.components.NumberPad

//  Skjermbildet som er selve spillet. Start ett nytt instans når brukeren trykker på "start" på
// hjemskjermen. Funksjonen GameScreenContent er selve skjermbildet, mens GameScreen bare gir
// riktige parametere og funkjsoner. Dette er gjor slik for at previewen skal fungere.
@Composable
fun GameScreen(
    navController: NavController,
    vm: GameViewModel = viewModel(),
    onExitToMenu: () -> Unit = {}
) {
    // Observerer UI-tilstand fra ViewModel (reaktivt oppdatert)
    val uiState = vm.ui.collectAsState(initial = GameViewModel.UiState()).value

    GameScreenContent(
        ui = uiState,
        onDigit = vm::onDigitPressed,      // Når brukeren trykker et tall
        onSubmit = vm::onSubmitAnswer,     // Når brukeren sender inn svaret
        onClear = vm::clearInput,          // Sletter nåværende input
        onNext = vm::next,                 // Går til neste oppgave
        onAskQuit = vm::askQuit,           // Spør om spilleren vil avslutte
        onDismissQuit = vm::dismissQuit,   // Avbryter "quit"-dialog
        onConfirmQuit = {
            vm.dismissQuit()
            navController.navigate("start") {
                popUpTo(0)                 // Tømmer backstack
                launchSingleTop = true     // Unngår dupliserte skjermer
            }
        },
        onStartNew = vm::startNewGame,     // Starter nytt spill
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
    ui: GameViewModel.UiState,
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
    Surface(
        color = MaterialTheme.colorScheme.background
    ) {
        // Håndterer tilbakeknapp (Android) → viser "quit"-dialog hvis ikke ferdig
        BackHandler(enabled = !ui.finished && !ui.showQuitDialog) { onAskQuit() }

        // Dialogboks når brukeren prøver å avslutte
        if (ui.showQuitDialog) {
            AlertDialog(
                onDismissRequest = { onDismissQuit() },
                title = { Text(stringResource(R.string.quit_dialog_title)) },
                text = { Text(stringResource(R.string.quit_dialog_text)) },
                confirmButton = {
                    Button(onClick = onConfirmQuit) {
                        Text(stringResource(R.string.quit))
                    }
                },
                dismissButton = {
                    OutlinedButton(onClick = onDismissQuit) {
                        Text(stringResource(R.string.continue_playing))
                    }
                }
            )
        }

        Surface(Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)
                    .verticalScroll(rememberScrollState()),  // Gjør innholdet scrollbart
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(48.dp))

                // Viser oppgave-nummer og poengsum
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(
                        stringResource(R.string.task) + " ${ui.questionNumber}/${ui.totalQuestions}",
                        style = MaterialTheme.typography.titleLarge
                    )
                    Text(
                        stringResource(R.string.score) + "${ui.score}",
                        style = MaterialTheme.typography.titleLarge
                    )
                }

                Spacer(Modifier.height(32.dp))

                // Ferdigskjerm med sluttresultat
                if (ui.finished) {
                    Text(
                        text = stringResource(R.string.feedback_finished_prefix) +
                                " ${ui.score} " +
                                stringResource(R.string.feedback_finished_suffix) +
                                " ${ui.totalQuestions}.",
                        style = MaterialTheme.typography.headlineSmall,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(Modifier.height(16.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        Button(onClick = onStartNew) { Text(stringResource(R.string.play_again)) }
                        OutlinedButton(onClick = onExitToMenu) { Text(stringResource(R.string.to_menu)) }
                    }
                    return@Surface
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Viser oppgavetekst
                Text(
                    text = ui.questionText,
                    style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(Modifier.height(24.dp))

                // Tallknappene (NumberPad)
                NumberPad(enabled = !ui.answered, onClick = onDigit)

                Spacer(Modifier.height(48.dp))

                // Brukerens svar + knapper for slett og send inn
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(R.string.your_answer) + " ${ui.currentInput}",
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.weight(1f)
                    )
                    OutlinedButton(
                        onClick = onClear,
                        enabled = !ui.answered && ui.currentInput.isNotEmpty()
                    ) {
                        Text(
                            text = stringResource(R.string.delete),
                            fontSize = 16.sp
                        )
                    }
                    Spacer(Modifier.width(8.dp))
                    Button(
                        onClick = onSubmit,
                        enabled = !ui.answered && ui.currentInput.isNotEmpty()
                    ) {
                        Text(
                            text = stringResource(R.string.check_answer),
                            fontSize = 16.sp
                        )
                    }
                }

                Spacer(Modifier.height(16.dp))

                // Tilbakemelding på svaret (riktig/feil)
                if (ui.answered && !ui.finished) {
                    val feedbackText = if (ui.isAnswerCorrect == true) {
                        stringResource(R.string.feedback_correct)
                    } else {
                        stringResource(R.string.feedback_wrong_prefix) + " ${ui.correctAnswer ?: 0}."
                    }
                    Text(feedbackText, style = MaterialTheme.typography.titleMedium)
                    Spacer(Modifier.height(8.dp))
                }

                // Nederste knapper: Avslutt og Neste
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    OutlinedButton(onClick = onAskQuit) {
                        Text(
                            text = stringResource(R.string.quit),
                            fontSize = 20.sp
                        )
                    }
                    Button(onClick = onNext, enabled = ui.answered) {
                        Text(
                            text = stringResource(R.string.next),
                            fontSize = 20.sp
                        )
                    }
                }
            }
        }
    }
}


// Preview composable bare for å lettere utvikle skjermen.
// Bruker bare eksempler for spørsmål, svar osv. Parameterene som er funskjoner er tomme.
@Preview(showBackground = true)
@Composable
fun GameScreenPreview() {
    Dajoh2062_oblig1Theme {
        GameScreenContent(
            ui = GameViewModel.UiState(
                questionText = "Hva er 7 × 6?",
                questionNumber = 1,
                totalQuestions = 5,
                score = 0,
                answered = false,
                finished = false,
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
