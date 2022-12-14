package com.example.wheeloffortune.ui.elements

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

//Composable for the title of the game
@Composable
fun GameTitle() {
    Text(
        text = "Wheel of Fortune",
        color = Color.Black,
        style = TextStyle(
            fontSize = 28.sp,
            fontWeight = FontWeight.ExtraBold
        )
    )
    Divider(
        color = Color.LightGray,
        modifier = Modifier
            .fillMaxWidth()
            .height(3.dp)
    )
}