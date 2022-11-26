package com.example.wheeloffortune.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.wheeloffortune.ui.GameViewModel
import com.example.wheeloffortune.ui.elements.*


@Composable
fun GameScreen(
    gameViewModel: GameViewModel = viewModel()
) {
    val gameUiState by gameViewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {

        GameTitle()

        HiddenWordCategory(currentCategoryForHiddenWord = gameUiState.currentCategoryForHiddenWord)

        HiddenWord(
            currentHiddenWord = gameUiState.currentHiddenWord,
            correctGuesses = gameUiState.correctGuesses
        )

        GameWheelButton(result = gameUiState.wheelResult, ableToSpin = gameUiState.ableToSpin)

        GameStatus(
            life = gameUiState.life,
            score = gameUiState.score
        )

        UserGuessButtonsLayout()

        if (gameUiState.isGameOver) {
            GameOverDialog(
                score = gameUiState.score,
                onPlayAgain = { gameViewModel.resetGame() }
            )
        }

        if (gameUiState.isGameWon) {
            GameWonDialog(
                score = gameUiState.score,
                life = gameUiState.life,
                onPlayAgain = { gameViewModel.resetGame() }
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GameScreenPreview() {
    GameScreen()

}
