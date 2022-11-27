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

//Composable for the alert dialog box when a user has lost.
//The user can exit the app with this or restart the game.
@Composable
fun GameOverDialog(
    score: Int,
    onPlayAgain: () -> Unit
) {
    val activity = (LocalContext.current as Activity)

    AlertDialog(
        onDismissRequest = {},
        title = {
            Text(
                "Too bad - Game over!",
                style = TextStyle(
                    fontSize = 22.sp
                )
            )
        },
        text = { Text("You got " + score + " points before you lost all your lives.") },
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
