package com.example.wheeloffortune.ui

data class GameUiState(
    val currentHiddenWord: String = "",
    val currentHiddenWordLength: Int = 0,
    val currentCategoryForHiddenWord: String = "",
    val score: Int = 0,
    val life: Int = 5,
    val isGameOver: Boolean = false,
    val isGameWon: Boolean = false,
    val correctGuessesNum: Int = 0,
    val correctGuesses: String = "",

    val wheelResult: String = "Spin the Wheel",
    val ableToSpin: Boolean = true,

    //UI state for whether the letter buttons are clickable or not
    val isClickable: Boolean = false,


    )