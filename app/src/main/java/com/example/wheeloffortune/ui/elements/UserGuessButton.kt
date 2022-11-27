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

//Composable for the button that, when pressed, its text is used as the user guess for the hidden word.
@Composable
fun UserGuessButton(
    text: String,
    gameViewModel: GameViewModel = viewModel(),
) {

    val gameUiState by gameViewModel.uiState.collectAsState()

    //Boolean to keep track of the button having been pressed.
    var used by remember { mutableStateOf(false) }

    //Color of the button.
    val color = if (used) Color.Gray else Color.Red

    //Resets the state of the button when the current game is over.
    if (gameUiState.isGameOver || gameUiState.isGameWon) {
        used = false
    }


    Button(
        onClick = {
            //When the button is clicked while it is clickable and has not been used
            //then assign userGuess in gameViewModel to the text of the button, run checkUserGuess() and
            //make "used" true.
            if (!used && gameUiState.isClickable) {
                gameViewModel.userGuess = text
                run {
                    gameViewModel.checkUserGuess()
                }
                used = true

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
        enabled = !used,
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