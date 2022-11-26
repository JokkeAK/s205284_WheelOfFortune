package com.example.wheeloffortune.ui.elements

import android.app.Activity
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext


@Composable
 fun GameWonDialog(
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
        text = {
            Text(
                if (life > 1) {
                    "You won the game with $score points and you had $life lives left."
                } else {
                    "You won the game with $score points and you had $life life left. "
                },
            )
        },
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