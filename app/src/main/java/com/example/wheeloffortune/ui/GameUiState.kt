package com.example.wheeloffortune.ui

import kotlinx.coroutines.flow.StateFlow

data class GameUiState(
    val currentHiddenWord: String = "",
    val currentHiddenWordLength: Int = 0,
    val currentCategoryForHiddenWord: String = "",
    val isGuessedLetterWrong: Boolean = false,
    val isGuessedLetterEmpty: Boolean = false,
    val isGuessedLetterRight: Boolean = false,
    val correctLetters: String = "",
    val score: Int = 0,
    val life: Int = 5,
    val isGameOver: Boolean = false,
    val isGameWon: Boolean = false,
    val correctGuesses: Int = 0,
    val correctCharToWrite: String = ""

    )