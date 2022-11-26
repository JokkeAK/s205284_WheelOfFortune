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

class GameViewModel : ViewModel() {

    // Game UI state
    private val _uiState = MutableStateFlow(GameUiState())
    // Backing property to avoid state updates from other classes
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()

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

    //Re-initializes the game to restart it.
    fun resetGame() {
        _uiState.value = GameUiState(
            currentCategoryForHiddenWord = pickRandomCategory(),
            currentHiddenWord = pickRandomWord(),
            currentHiddenWordLength = currentWord.length,
            life = 5
        )
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

    //Picks a random result for the wheel button
    fun spinWheel() {
        currentResult = allResults.random()

        if (currentResult == "Bankrupt! \nSpin again") {
            _uiState.update { currentState ->
                currentState.copy(
                    wheelResult = currentResult,
                    ableToSpin = true,
                    score = 0,
                    isClickable = false,

                    )
            }
        } else {
            _uiState.update { currentState ->
                currentState.copy(
                    wheelResult = currentResult,
                    ableToSpin = false,
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
                    ableToSpin = true,
                    isClickable = false,
                    wheelResult = "Spin the Wheel"
                )
            }

            //Updates which letters has been correct guesses.
            updatedCorrectGuesses = _uiState.value.correctGuesses.plus(userGuess)

            //Updates the amount of correct guesses the user has had so far in the game.
            updatedCorrectGuessesNum =
                _uiState.value.correctGuessesNum.plus(currentWord.count { it == userGuess.first() })

            //Updates the amount of points a player gets based on the number of correct letters guessed.
            updatedScore =
                _uiState.value.score.plus(currentResult.toInt() * (currentWord.count { it == userGuess.first() }))

        } else {
            //User's guess is incorrect, wheel can be spun, buttons cannot be pressed.
            _uiState.update { currentState ->
                currentState.copy(
                    ableToSpin = true,
                    isClickable = false,
                    wheelResult = "Spin the Wheel"
                )
            }

            //Guess is incorrect, decrease life.
            updatedLife = _uiState.value.life.minus(1)

        }
        //Updates the state based on the user guess.
        updateGameState(updatedScore, updatedCorrectGuessesNum, updatedCorrectGuesses, updatedLife)
    }

    //Updates the game state based on user guess.
    private fun updateGameState(
        updatedScore: Int,
        updatedCorrectGuessesNum: Int,
        updatedCorrectGuesses: String,
        updatedLife: Int
    ) {
        if (updatedLife == 0) {
            //The player has lost the game
            _uiState.update { currentState ->
                currentState.copy(
                    isGameOver = true,

                    )
            }
        } else if (updatedCorrectGuessesNum >= currentWord.length) {
            //The player wins
            _uiState.update { currentState ->
                currentState.copy(
                    isGameWon = true,
                    score = updatedScore,
                    correctGuessesNum = updatedCorrectGuessesNum,
                    correctGuesses = updatedCorrectGuesses

                )
            }
        } else {
            //A normal "round"
            _uiState.update { currentState ->
                currentState.copy(
                    score = updatedScore,
                    isGameWon = false,
                    correctGuessesNum = updatedCorrectGuessesNum,
                    correctGuesses = updatedCorrectGuesses,
                    life = updatedLife
                )
            }
        }
    }
}
