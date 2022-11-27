package com.example.wheeloffortune.ui.elements

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp

//Composable that shows the user's life and score.
@Composable
fun GameStatus(life: Int, score: Int) {
    Text(
        text = "Lives: $life",
        fontSize = 18.sp,
    )
    Text(
        text = "Points: $score",
        fontSize = 18.sp,
    )
}
