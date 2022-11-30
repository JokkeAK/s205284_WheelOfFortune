package com.example.wheeloffortune.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.wheeloffortune.data.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

//ViewModel containing the app data and methods to process the data
class GameViewModel : ViewModel() {

    // Game UI state
    private val _uiState = MutableStateFlow(GameUIState())

    // Backing property to avoid state updates from other classes
    val uiState: StateFlow<GameUIState> = _uiState.asStateFlow()

    //The current category for which an available word is chosen
    private lateinit var currentCategory: String

    //The current word that is hidden
    private lateinit var currentWord: String

    //The current result of the wheel
    private var currentResult: String = " "

    //The amount of times the user has guessed correctly
    private var updatedCorrectGuessesNum: Int = 0

    //The correct guesses held in a string
    private var updatedCorrectGuesses: String = ""

    //The amount of points the user has.
    private var updatedScore: Int = 0

    //The amount of life the user has.
    private var updatedLife: Int = 5

    //The user guess that is based on which button has been pressed.
    var userGuess by mutableStateOf("")

    //Initializes the game as if it was reset.
    init {
        resetGame()
    }

    //Re-initializes the game to restart it with the appropriate values.
    fun resetGame() {
        _uiState.value = GameUIState(
            currentCategoryForHiddenWord = pickRandomCategory(),
            currentHiddenWord = pickRandomWord(),
            currentHiddenWordLength = currentWord.length,
        )
        updatedCorrectGuessesNum = 0
        updatedScore = 0
        updatedCorrectGuesses = ""
    }

    //Picks a random word category.
    private fun pickRandomCategory(): String {
        currentCategory = allCategories.random()
        return currentCategory
    }


    //Picks a random word available in the current category.
    private fun pickRandomWord(): String {
        currentWord = when (currentCategory) {
            "Animals" -> animalWords.random()
            "Singers" -> singersWords.random()
            "Fruit" -> fruitWords.random()
            "Vegetables" -> vegetablesWords.random()
            else -> {
                videoGamesWords.random()
            }
        }
        return currentWord
    }

    //Picks a random result for the wheel button and updates the result.
    fun spinWheel() {
        currentResult = allResults.random()

        //If result is bankrupts the wheel can be spun again, score is set to 0,
        //letter buttons cannot be clicked.
        if (currentResult == "Bankrupt! \nSpin again") {
            _uiState.update { currentState ->
                currentState.copy(
                    wheelResult = currentResult,
                    readyToSpin = true,
                    score = 0,
                    isClickable = false,

                    )
            }
        } else {
            //Else the wheel result is the current result, wheel cannot be spun,
            //letter buttons can be clicked.
            _uiState.update { currentState ->
                currentState.copy(
                    wheelResult = currentResult,
                    readyToSpin = false,
                    isClickable = true

                )
            }
        }
    }

    //Checks if the letter of the user guess is in the currently hidden word.
    fun checkUserGuess() {
        if (currentWord.contains(userGuess, ignoreCase = true)) {
            // User's guess is correct, wheel can be spun, buttons cannot be pressed.
            _uiState.update { currentState ->
                currentState.copy(
                    readyToSpin = true,
                    isClickable = false,
                    wheelResult = "Spin the Wheel"
                )
            }
            //Guess is correct and the player is awarded points accordingly.
            updatedScore =
                _uiState.value.score.plus(currentResult.toInt() * (currentWord.count { it == userGuess.first() }))

            //Guess is correct, life does not change.
            updatedLife = _uiState.value.life

            //Guess is correct and the amount of correct guesses is by the amount of times the guessed letter exists in the hidden word.
            updatedCorrectGuessesNum =
                _uiState.value.correctGuessesNum.plus(currentWord.count { it == userGuess.first() })

            //Guess is correct and the correct letter is added to the correctGuesses string.
            updatedCorrectGuesses = _uiState.value.correctGuesses.plus(userGuess)

        } else {
            //User's guess is incorrect, wheel can be spun, buttons cannot be pressed.
            _uiState.update { currentState ->
                currentState.copy(
                    readyToSpin = true,
                    isClickable = false,
                    wheelResult = "Spin the Wheel"
                )
            }
            //Guess is incorrect, no change to points.
            updatedScore = _uiState.value.score

            //Guess is incorrect, decrease life.
            updatedLife = _uiState.value.life.minus(1)
        }

        updateGameState(updatedScore, updatedLife, updatedCorrectGuessesNum, updatedCorrectGuesses)
    }

    //Updates the game state based on user guess.
    private fun updateGameState(
        updatedScore: Int,
        updatedLife: Int,
        updatedCorrectGuessesNum: Int,
        updatedCorrectGuesses: String,
    ) {
        if (updatedLife == 0) {
            //The player has lost the game
            _uiState.update { currentState ->
                currentState.copy(
                    isGameOver = true,
                    life = updatedLife,
                )
            }
        } else if (updatedCorrectGuessesNum >= currentWord.length) {
            //The player wins
            _uiState.update { currentState ->
                currentState.copy(
                    isGameWon = true,
                    score = updatedScore,
                    correctGuessesNum = updatedCorrectGuessesNum,
                    correctGuesses = updatedCorrectGuesses,
                )
            }
        } else {
            //A normal "round"
            _uiState.update { currentState ->
                currentState.copy(
                    score = updatedScore,
                    life = updatedLife,
                    correctGuessesNum = updatedCorrectGuessesNum,
                    correctGuesses = updatedCorrectGuesses,
                )
            }
        }
    }
}
