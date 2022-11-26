package com.example.wheeloffortune.ui

data class GameUiState(
    val currentHiddenWord: String = "",
    val currentHiddenWordLength: Int = 0,
    val currentCategoryForHiddenWord: String = "",
    val isGuessedLetterWrong: Boolean = false,
    val isGuessedLetterEmpty: Boolean = false,
    val isGuessedLetterRight: Boolean = false,
    val score: Int = 0,
    val life: Int = 5,
    val isGameOver: Boolean = false,
    val isGameWon: Boolean = false,
    val correctGuessesNum: Int = 0,
    val correctGuesses: String = "",
    val userGuess: String = "",





    )