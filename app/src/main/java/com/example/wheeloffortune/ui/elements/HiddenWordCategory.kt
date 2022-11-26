package com.example.wheeloffortune.ui.elements

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp

@Composable
fun HiddenWordCategory(
    currentCategoryForHiddenWord: String,
) {
    Text(
        text = currentCategoryForHiddenWord,
        color = Color.Black,
        style = TextStyle(
            fontSize = 28.sp
        ),
    )
}