package com.example.wheeloffortune.ui

//Data class that represents the game UI state
data class GameUIState(
    //UI state for the hidden word.
    val currentHiddenWord: String = "",
    //UI state for the length of the hidden word.
    val currentHiddenWordLength: Int = 0,
    //UI state for the category of the hidden word.
    val currentCategoryForHiddenWord: String = "",
    //UI state for the life of the user.
    val life: Int = 5,
    //UI state for the score of the user.
    val score: Int = 0,
    //UI state for whether the game has been lost.
    val isGameOver: Boolean = false,
    //UI state for whether the game has been won.
    val isGameWon: Boolean = false,
    //UI state for the amount of times there have been a correct guess.
    val correctGuessesNum: Int = 0,
    //UI state for the letters that have been correct guesses.
    val correctGuesses: String = "",
    //UI state for the wheel result.
    val wheelResult: String = "Spin the Wheel",
    //UI state for whether the wheel can be spun.
    val readyToSpin: Boolean = true,
    //UI state for whether the letter buttons are clickable or not.
    val isClickable: Boolean = false,
)