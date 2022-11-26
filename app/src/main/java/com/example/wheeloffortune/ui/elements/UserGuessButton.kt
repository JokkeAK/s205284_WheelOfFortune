package com.example.wheeloffortune.ui.elements

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.wheeloffortune.ui.GameViewModel

@Composable
fun UserGuessButton(
    text: String,
    gameViewModel: GameViewModel = viewModel(),
) {

    val gameUiState by gameViewModel.uiState.collectAsState()

    var pressed by remember { mutableStateOf(false) }
    val color = if (pressed) Color.Gray else Color.Red

    if (gameUiState.isGameOver || gameUiState.isGameWon) {
        pressed = false
    }


    Button(
        onClick = {
            if (!pressed && gameUiState.isClickable) {
                gameViewModel.userGuess = text

                run {
                    gameViewModel.checkUserGuess()
                }
                pressed = !pressed

            }
        },
        modifier = Modifier
            .width(width = 35.dp)
            .height(height = 35.dp),
        contentPadding = PaddingValues(0.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = color,
            disabledContainerColor = color
        ),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, Color.Black),
        enabled = !pressed,
        elevation = ButtonDefaults.elevatedButtonElevation(
            defaultElevation = 4.dp,
            pressedElevation = 12.dp,
            disabledElevation = 0.dp
        )

    )
    {
        Text(
            text = text,
            color = Color.White,
            textAlign = TextAlign.Center,
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold
            )
        )
    }
}