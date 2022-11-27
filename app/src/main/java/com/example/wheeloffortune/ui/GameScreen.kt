package com.example.wheeloffortune.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.wheeloffortune.ui.GameViewModel
import com.example.wheeloffortune.ui.elements.*

//The only screen in the app. Shows the entire UI the app has to offer.
@Composable
fun GameScreen(
    gameViewModel: GameViewModel = viewModel()
) {
    val gameUIState by gameViewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {

        //Title for the game.
        GameTitle()

        //Word category.
        HiddenWordCategory(currentCategoryForHiddenWord = gameUIState.currentCategoryForHiddenWord)

        //The hidden word for the user to guess each letter of.
        HiddenWord(
            currentHiddenWord = gameUIState.currentHiddenWord,
            correctGuesses = gameUIState.correctGuesses
        )

        //The wheel to "spin".
        GameWheelButton(
            result = gameUIState.wheelResult,
            readyToSpin = gameUIState.readyToSpin,
            pressed = !gameUIState.readyToSpin
        )

        //Status for the user's life and score.
        GameStatus(
            life = gameUIState.life,
            score = gameUIState.score
        )

        //The layout for the buttons for the user to make their guess.
        UserGuessButtonsLayout()

        //If the user lost the game then the game over dialog alert box opens up with the options
        //to either exit or play again.
        if (gameUIState.isGameOver) {
            GameOverDialog(
                score = gameUIState.score,
                onPlayAgain = { gameViewModel.resetGame() }
            )
        }

        //If the game is won by the user then the game won dialog alert box opens up with the options
        //to either exit or play again.
        if (gameUIState.isGameWon) {
            GameWonDialog(
                score = gameUIState.score,
                life = gameUIState.life,
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
