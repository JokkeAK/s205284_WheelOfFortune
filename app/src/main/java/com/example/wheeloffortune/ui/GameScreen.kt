package com.example.wheeloffortune.ui.screen

import android.app.Activity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.wheeloffortune.ui.GameViewModel




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameScreen(
    modifier: Modifier = Modifier,
    gameViewModel: GameViewModel = viewModel()

) {
    val gameUiState by gameViewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {


        GameTitle()
        WordCategory(currentCategoryForHiddenWord = gameUiState.currentCategoryForHiddenWord)


        HiddenWord(
            currentHiddenWord = gameUiState.currentHiddenWord,
            correctGuesses = gameUiState.correctGuesses
        )

        GameWheelButton(result = gameUiState.wheelResult, ableToSpin = gameUiState.ableToSpin)

        GameStatus(
            life = gameUiState.life,
            score = gameUiState.score
        )


        GuessButtonRows()

        if (gameUiState.isGameOver) {
            GameOverDialog(
                score = gameUiState.score,
                onPlayAgain = { gameViewModel.resetGame() }
            )
        }

        if (gameUiState.isGameWon) {
            GameWonDialog(
                score = gameUiState.score,
                life = gameUiState.life,
                onPlayAgain = { gameViewModel.resetGame() }
            )
        }


    }

}

@Composable
fun GameTitle() {
    Text(
        text = "Wheel of Fortune",
        color = Color.DarkGray,
        style = TextStyle(
            fontSize = 28.sp,
            fontWeight = FontWeight.ExtraBold
        )
    )
}

@Composable
fun WordCategory(
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HiddenWord(
    currentHiddenWord: String,
    correctGuesses: String,
    ) {
    Row() {

        //Makes a box for each letter in the hidden word.
        for (char in currentHiddenWord) {
            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .border(BorderStroke(1.dp, color = Color.Black))
                    .size(35.dp, 50.dp),
                contentAlignment = Alignment.Center,

            )
            {

                    if(char in correctGuesses) {
                        Text(
                            text = char.toString(),
                            color = Color.Black,
                            modifier = Modifier
                                .alpha(1F),
                            style = TextStyle(
                                fontSize = 36.sp
                            )
                        )
                    }else {
                        Text(
                            text = char.toString(),
                            color = Color.Black,
                            modifier = Modifier
                                .alpha(0.5F),
                            style = TextStyle(
                                fontSize = 36.sp
                            )
                        )
                    }
            }
        }
    }
}

@Composable
fun GameWheelButton(
    result: String,
    ableToSpin: Boolean,
    gameViewModel: GameViewModel = viewModel()
) {
    val gameUiState by gameViewModel.uiState.collectAsState()
    var pressed by remember { mutableStateOf(false) }

    Button(
        onClick = { if(!pressed && ableToSpin) {
            run {
                gameViewModel.spinWheel()
            }
        }},
        modifier = Modifier
            .size(360.dp, 80.dp),
        colors= ButtonDefaults.buttonColors(Color.Green),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, Color.Black),
        elevation = ButtonDefaults.elevatedButtonElevation(
            defaultElevation = 10.dp,
            pressedElevation = 10.dp,
            disabledElevation = 10.dp
        )
    )
    {
        Text(
            text = result,
            color = Color.White,
            textAlign = TextAlign.Center,
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold
            )
        )
    }

    /*
    Box(
        modifier = Modifier
            .size(360.dp, 80.dp)
            .background(Color.Green, shape = CircleShape)
    ) {
        Text(
            text = result,
            color = Color.Black,
            style = TextStyle(
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.align(Alignment.Center)
        )
    }

     */


}


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


@Composable
private fun GameWonDialog(
    life: Int,
    score: Int,
    onPlayAgain: () -> Unit,
    modifier: Modifier = Modifier
) {
    val activity = (LocalContext.current as Activity)

    AlertDialog(
        onDismissRequest = {
            // Dismiss the dialog when the user clicks outside the dialog or on the back
            // button. If you want to disable that functionality, simply use an empty
            // onCloseRequest.
        },
        title = { Text("Congratulations - You won!") },
        text = { Text("You won the game with " + score + " points and you had " + life + " lives left.")},
        modifier = modifier,
        dismissButton = {
            TextButton(
                onClick = {
                    activity.finish()
                }
            ) {
                Text("Exit")
            }
        },
        confirmButton = {
            TextButton(onClick = onPlayAgain) {
                Text("Play again")
            }
        }
    )
}

@Composable
private fun GameOverDialog(
    score: Int,
    onPlayAgain: () -> Unit,
    modifier: Modifier = Modifier
) {
    val activity = (LocalContext.current as Activity)

    AlertDialog(
        onDismissRequest = {
            // Dismiss the dialog when the user clicks outside the dialog or on the back
            // button. If you want to disable that functionality, simply use an empty
            // onCloseRequest.
        },
        title = { Text("Too bad - Game over!") },
        text = { Text("You got " + score + " points before you lost all your lives.") },
        modifier = modifier,
        dismissButton = {
            TextButton(
                onClick = {
                    activity.finish()
                }
            ) {
                Text("Exit")
            }
        },
        confirmButton = {
            TextButton(onClick = onPlayAgain) {
                Text("Play again")
            }
        }
    )
}

@Composable
fun GuessButton(
                text: String,
                gameViewModel: GameViewModel = viewModel(),
                ) {

    val gameUiState by gameViewModel.uiState.collectAsState()

    var pressed by remember { mutableStateOf(false) }
    val color = if (pressed) Color.Gray else Color.Red

    if(gameUiState.isGameOver || gameUiState.isGameWon){
        pressed = false
    }



    Button(
        onClick = { if(!pressed && gameUiState.isClickable) {
            gameViewModel.userGuess = text

            run {
                gameViewModel.checkUserGuess()
            }
            pressed = !pressed
        gameUiState.isClickable = false } },
        modifier = Modifier
            .width(width = 35.dp)
            .height(height = 35.dp),
        contentPadding = PaddingValues(0.dp),
        colors= ButtonDefaults.buttonColors(color),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, Color.Black),
        elevation = ButtonDefaults.elevatedButtonElevation(
            defaultElevation = 10.dp,
            pressedElevation = 10.dp,
            disabledElevation = 10.dp
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

@Composable
fun GuessButtonRows() {
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        GuessButton("A")
        GuessButton("B")
        GuessButton("C")
        GuessButton("D")
        GuessButton("E")
        GuessButton("F")
        GuessButton("G")
        GuessButton("H")
    }

    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        GuessButton("I")
        GuessButton("J")
        GuessButton("K")
        GuessButton("L")
        GuessButton("M")
        GuessButton("N")
        GuessButton("O")
        GuessButton("P")
    }

    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        GuessButton("Q")
        GuessButton("R")
        GuessButton("S")
        GuessButton("T")
        GuessButton("U")
        GuessButton("V")
        GuessButton("W")
        GuessButton("X")
    }

    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        GuessButton("Y")
        GuessButton("Z")
    }

}





@Preview(showBackground = true)
@Composable
fun GameScreenPreview() {
    GameScreen()

}
