package com.example.wheeloffortune.ui.elements

import android.app.Activity
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp

//Composable for the alert dialog box when a user has won.
//The user can exit the app with this or restart the game.
@Composable
fun GameWonDialog(
    life: Int,
    score: Int,
    onPlayAgain: () -> Unit
) {
    val activity = (LocalContext.current as Activity)

    AlertDialog(
        onDismissRequest = {},
        title = {
            Text(
                "Congratulations - You won!",
                style = TextStyle(
                    fontSize = 22.sp
                )
            )
        },
        text = {
            Text(
                if (life > 1) {
                    "You won the game with $score points and you had $life lives left."
                } else {
                    "You won the game with $score points and you had $life life left. "
                },
            )
        },
        modifier = Modifier,
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