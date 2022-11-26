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

    private var updatedCorrectGuessesNum: Int = 0
    private var updatedCorrectGuesses: String = ""

    private var updatedScore: Int = 0
    private var updatedUserGuess: String = ""
    private var updatedLetter: String = ""


    private lateinit var updatedCorrectLetters: String


    var userGuess by mutableStateOf("")
    var correctCharToWrite by mutableStateOf("")

    var lettersToReveal: String = ""


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
            userGuess = guessedLetter
    }




    fun checkUserGuess() {

        if (currentWord.contains(userGuess, ignoreCase = true)) {
            // User's guess is correct, increase the score
            _uiState.update { currentState -> currentState.copy(
                isGuessedLetterEmpty = false,
                isGuessedLetterWrong = false,
                isGuessedLetterRight = true,
                //revealedLetter = userGuess
            ) }





            updatedCorrectGuesses = _uiState.value.correctGuesses.plus(userGuess)

            //Updates the amount of correct guesses the user has had so far in the game.
            updatedCorrectGuessesNum = _uiState.value.correctGuessesNum.plus(currentWord.count{it == userGuess.first()})

            //Updates the amount of points a player gets based on the number of correct letters guessed.
            updatedScore = _uiState.value.score.plus(scoreInc*(currentWord.count{it == userGuess.first()}))

            updatedUserGuess = _uiState.value.userGuess.plus(userGuess)

            updateGameState(updatedScore, updatedCorrectGuessesNum, updatedUserGuess, updatedLetter, updatedCorrectGuesses)


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
    private fun updateGameState(updatedScore: Int, updatedCorrectGuessesNum: Int, updatedUserGuess: String, updatedLetter: String, updatedCorrectGuesses: String) {
        if(updatedCorrectGuessesNum >= currentWordLength){
            //The player wins
            _uiState.update { currentState ->
            currentState.copy(
                    isGameWon = true,
                    score = updatedScore,
                isGuessedLetterWrong = false,
                correctGuessesNum = updatedCorrectGuessesNum,
                userGuess = updatedUserGuess,
                correctGuesses = updatedCorrectGuesses

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
                correctGuessesNum = updatedCorrectGuessesNum,
                userGuess = updatedUserGuess,
                correctGuesses = updatedCorrectGuesses


            )


            }
        }
    }


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
            currentHiddenWordLength = currentWordLength,

        )
        updateUserGuess("")
        lettersToReveal = ""

    }


}
