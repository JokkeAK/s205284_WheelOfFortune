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




    private lateinit var currentCategory: String
    private lateinit var currentWord: String



    private var currentWordLength: Int = 0

    private var updatedCorrectGuesses: Int = 0
    private var updatedScore: Int = 0

    private lateinit var updatedCorrectLetters: String


    var userGuess by mutableStateOf("")
    var correctCharToWrite by mutableStateOf("")


    //private val _charToWrite = MutableStateFlow("")
   // private val charToWrite = _charToWrite.asStateFlow()

    init {
        resetGame()
    }


    //Picks a random category.
    private fun pickRandomCategory(): String {
        currentCategory = allCategories.random()
        return currentCategory
    }

    //Picks a random word based on the current category.
    private fun pickRandomWord(): String {
        when (currentCategory) {
            "Animals" -> currentWord = animalWords.random()
            "Singers" -> currentWord = singersWords.random()
            "Fruit" -> currentWord = fruitWords.random()
            "Vegetables" -> currentWord = vegetablesWords.random()
            else -> {
                currentWord = videoGamesWords.random()
            }
        }
        //Counts the amount of chars in the current word.
        currentWordLength = currentWord.length
        return currentWord
    }

    private fun countRandomWordLength(): Int {

        currentWordLength = currentWord.count()
        /*
        currentWordLength = currentWord.length
        for (char in currentWord){
            if(char.equals(" ")){
                currentWordLength--
            }
        }


        //currentWordLength = currentWord.length

         */
        return currentWordLength
    }





    fun updateUserGuess(guessedLetter: String) {
        //The player can only enter a single letter.
        if (userGuess.isEmpty()) {
            userGuess = guessedLetter
        }
        //If user tries to enter another letter, the former input is deleted.
        else {
            userGuess = ""
        }
    }


    fun checkUserGuess() {

        correctCharToWrite = ""

        if (userGuess.isBlank() || userGuess == " ") {
            // User's guess is either blank or a space. Life and scores is not altered.
            _uiState.update { currentState -> currentState.copy(
                isGuessedLetterEmpty = true,
                isGuessedLetterWrong = false,
                isGuessedLetterRight = false) }


        } else if (currentWord.contains(userGuess, ignoreCase = true)) {
            // User's guess is correct, increase the score
            _uiState.update { currentState -> currentState.copy(
                isGuessedLetterEmpty = false,
                isGuessedLetterWrong = false,
                isGuessedLetterRight = true,
            ) }

            updatedCorrectLetters = _uiState.value.correctLetters.plus(userGuess)

            correctCharToWrite = _uiState.value.correctCharToWrite.plus("a")

            //charToWrite = _uiState.value.correctCharToWrite.plus(userGuess)

            //_charToWrite.value = userGuess

            //Updates the amount of correct guesses the user has had so far in the game.
            updatedCorrectGuesses = _uiState.value.correctGuesses.plus(currentWord.count{it == userGuess.first()})

            //Updates the amount of points a player gets based on the number of correct letters guessed.
            updatedScore = _uiState.value.score.plus(scoreInc*(currentWord.count{it == userGuess.first()}))


            updateGameState(updatedScore, updatedCorrectGuesses, updatedCorrectLetters, correctCharToWrite)

        } else {
            _uiState.update { currentState -> currentState.copy(
                isGuessedLetterEmpty = false,
                isGuessedLetterWrong = true,
                isGuessedLetterRight = false) }

            // User's guess is incorrect, decrease life.
            val updatedLife = _uiState.value.life.minus(1)
            updateGameStateLife(updatedLife)

            // Reset user guess
            updateUserGuess("")
        }
    }


    //Updates the game state based on correct answer.
    private fun updateGameState(updatedScore: Int, updatedCorrectGuesses: Int, updatedCorrectLetters: String, correctCharToWrite: String) {
        if(updatedCorrectGuesses >= currentWordLength){
            //The player wins
            _uiState.update { currentState ->
            currentState.copy(
                    isGameWon = true,
                    score = updatedScore,
                isGuessedLetterWrong = false,
                correctGuesses = updatedCorrectGuesses,
                correctLetters = updatedCorrectLetters,
                correctCharToWrite = correctCharToWrite

            )
                }
        } else {
            //A normal "round"
            _uiState.update { currentState ->
            currentState.copy(
                score = updatedScore,
                isGameWon = false,
                isGuessedLetterWrong = false,
                isGuessedLetterRight = false,
                isGuessedLetterEmpty = false,

                correctGuesses = updatedCorrectGuesses,
                correctLetters = updatedCorrectLetters,
                correctCharToWrite = correctCharToWrite,

            )


            }
        }
    }



/*
                //isGuessedLetterWrong = false,
                //currentCategoryForHiddenWord = pickRandomCategory(),
                //currentHiddenWord = pickRandomWord(),
                //currentHiddenWordLength = currentWordLength,
                score = updatedScore,
                correctGuesses = updatedCorrectGuesses
                //isGameWon = true

 */


    //Updates the game state based on the player's life.
    private fun updateGameStateLife(updatedLife: Int) {
        if (updatedLife == 0) {
            //The player has lost the game
            _uiState.update { currentState ->
                currentState.copy(
                    isGuessedLetterWrong = true,
                    life = updatedLife,
                    isGameOver = true,

                )
            }
        } else {
            //The player still has lives left to continue the game.
                _uiState.update { currentState ->
                    currentState.copy(
                        isGuessedLetterWrong = true,
                        life = updatedLife
                    )
                }
            }
        }



    fun resetGame() {
        _uiState.value = GameUiState(
            currentCategoryForHiddenWord = pickRandomCategory(),
            currentHiddenWord = pickRandomWord(),
            currentHiddenWordLength = currentWordLength
        )
    }


}
