package com.example.wheeloffortune.ui.screen

import android.app.Activity
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.wheeloffortune.ui.GameViewModel
import kotlin.text.Typography

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

        GameLayout(
            currentCategoryForHiddenWord = gameUiState.currentCategoryForHiddenWord,
            currentHiddenWord = gameUiState.currentHiddenWord,
            currentHiddenWordLength = gameUiState.currentHiddenWordLength,
            onUserGuessChanged = { gameViewModel.updateUserGuess(it) },
            onKeyboardDone = { gameViewModel.checkUserGuess() },
            userGuess = gameViewModel.userGuess,
            isGuessWrong = gameUiState.isGuessedLetterWrong,
            isGuessEmpty = gameUiState.isGuessedLetterEmpty,
            isGuessRight = gameUiState.isGuessedLetterRight,
            correctGuesNum = gameUiState.correctGuesses,
            checkLetters = gameUiState.correctLetters,
            correctCharToWrite = gameUiState.correctCharToWrite

        )

        GameStatus(
            life = gameUiState.life,
            score = gameUiState.score
        )

        //GameButtons()

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {

            Button(
                onClick = { },
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp),

                ) {
                Text("Spin")
            }

            Button(
                onClick = { gameViewModel.checkUserGuess() },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(start = 8.dp),
            ) {
                Text("Submit")
            }
        }

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameLayout(
    result: String = "DKK 1000",
    currentCategoryForHiddenWord: String,
    currentHiddenWord: String,
    currentHiddenWordLength: Int,
    onUserGuessChanged: (String) -> Unit,
    onKeyboardDone: () -> Unit,
    userGuess: String,
    isGuessWrong: Boolean,
    isGuessEmpty: Boolean,
    isGuessRight: Boolean, //to handle visibility for correct guesses
    correctGuesNum: Int,
    correctGuessedLetters: MutableList<Char> = mutableListOf<Char>(),
    checkLetters: String,
    correctCharToWrite: String,
) {

    var correctInput : String by rememberSaveable {
        mutableStateOf(correctCharToWrite)
    }


    Text(
        text = currentCategoryForHiddenWord,
        color = Color.Black,
        style = TextStyle(
            fontSize = 28.sp
        ),
    )



    Row() {

        //Makes a box for each letter in the hidden word.
        for (char in currentHiddenWord) {
            Box(
                modifier = Modifier
                    //.background(color = Color.Gray)
                    .padding(4.dp)
                    .border(BorderStroke(1.dp, color = Color.Black))
                    .size(35.dp, 50.dp),
                contentAlignment = Alignment.Center
            )
            {

                //Checks userGuess against each letter in the hidden word. Raises the alpha levels to visible
                //if there is a match.
                    if (char.toString() == userGuess /*&& char.toString() !in checkLetters */){

                        Text(
                            text = char.toString(),
                            color = Color.Black,
                            modifier = Modifier
                                .alpha(0.5F),
                            style = TextStyle(
                                fontSize = 36.sp))

                        correctGuessedLetters.add(char)



                    /*

    var char2 : String by rememberSaveable {
        mutableStateOf(userGuess)
    }
                        TextField(
                            value =char2,
                            onValueChange = { })
 */

                    }





                /*
                                 for (char in currentHiddenWord){
                                     if(char.toString() == userGuess){
                                         Color.Black
                                     } else {
                                         Color.White
                                     }
                                 },

                                  */
                /*
                for (char in userGuess){
                    if(currentHiddenWord.contains(userGuess, ignoreCase = true)) {
                        if (isVisible) {
                            Text(
                                text = char.toString(),
                                color = Color.Black,
                                modifier = Modifier,
                                style = TextStyle(
                                    fontSize = 36.sp
                                )
                            )

                        }
                    }
                }

                 */

            }
        }
    }




/*
    Text(
        text = currentHiddenWord + currentHiddenWordLength,
        color = Color.Black,
        style = TextStyle(
            fontSize = 36.sp
        ),
    )

 */



    Box(
        modifier = Modifier
            .size(360.dp, 80.dp)
            .background(Color.Green, shape = CircleShape)
    ) {
        Text(
            text = result + " " + correctGuesNum,
            color = Color.Black,
            style = TextStyle(
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.align(Alignment.Center)
        )
    }



    OutlinedTextField(
        value = userGuess,
        singleLine = true,
        modifier = Modifier.size(155.dp, 60.dp),
        onValueChange = onUserGuessChanged,
        label = {
            if (isGuessWrong) {
                Text("Wrong letter - you lost 1 life")
            } else if (isGuessEmpty) {
                Text("You need to enter a letter")
            } else {
                Text("Enter your letter")
            }
        },
        isError = isGuessWrong || isGuessEmpty,
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = { onKeyboardDone() }),

            )
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
fun LetterField() {
    Box(modifier = Modifier.background(color = Color.DarkGray)){
        Text(
            text = "A",
        )
    }
}

/*
@Composable
fun GameButtons() {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {

        Button(
            onClick = { },
            modifier = Modifier
                .weight(1f)
                .padding(end = 8.dp),

            ) {
            Text("Spin")
        }

        Button(
            onClick = { gameViewModel.checkUserGuess()},
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(start = 8.dp),
        ) {
            Text("Submit")
        }
    }

}

 */


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
fun GuessButton(text: String) {

    var pressed by remember { mutableStateOf(false) }
    val color = if (pressed) Color.Gray else Color.Red


    Button(
        onClick = { if(!pressed) {pressed = !pressed} },
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
