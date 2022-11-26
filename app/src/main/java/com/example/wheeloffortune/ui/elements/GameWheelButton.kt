package com.example.wheeloffortune.ui.elements

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.size
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
fun GameWheelButton(
    result: String,
    ableToSpin: Boolean,
    gameViewModel: GameViewModel = viewModel()
) {
    val gameUiState by gameViewModel.uiState.collectAsState()
    var pressed by remember { mutableStateOf(false) }
    val boxColor =
        if (result == "Spin the Wheel") Color.Black else if (result == "Bankrupt! \nSpin again") Color.Red else Color.Green
    val textColor = if (result == "Spin the Wheel") Color.White else Color.Black

    Button(
        onClick = {
            if (!pressed && ableToSpin) {
                run {
                    gameViewModel.spinWheel()
                }
            }
        },
        modifier = Modifier
            .size(360.dp, 80.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = boxColor,
            disabledContainerColor = boxColor
        ),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, Color.Black),
        enabled = result == "Spin the Wheel" || result == "Bankrupt! \nSpin again",
        elevation = ButtonDefaults.elevatedButtonElevation(
            defaultElevation = 6.dp,
            pressedElevation = 18.dp,
            disabledElevation = 0.dp
        )
    )
    {
        Text(
            text = result,
            color = textColor,
            textAlign = TextAlign.Center,
            style = TextStyle(
                fontSize = 26.sp,
                fontWeight = FontWeight.ExtraBold
            )
        )
    }
}