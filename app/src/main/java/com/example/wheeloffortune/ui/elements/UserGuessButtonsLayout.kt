package com.example.wheeloffortune.ui.elements

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp


@Composable
fun UserGuessButtonsLayout() {
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        UserGuessButton("A")
        UserGuessButton("B")
        UserGuessButton("C")
        UserGuessButton("D")
        UserGuessButton("E")
        UserGuessButton("F")
        UserGuessButton("G")
        UserGuessButton("H")
    }

    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        UserGuessButton("I")
        UserGuessButton("J")
        UserGuessButton("K")
        UserGuessButton("L")
        UserGuessButton("M")
        UserGuessButton("N")
        UserGuessButton("O")
        UserGuessButton("P")
    }

    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        UserGuessButton("Q")
        UserGuessButton("R")
        UserGuessButton("S")
        UserGuessButton("T")
        UserGuessButton("U")
        UserGuessButton("V")
        UserGuessButton("W")
        UserGuessButton("X")
    }

    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        UserGuessButton("Y")
        UserGuessButton("Z")
    }

}