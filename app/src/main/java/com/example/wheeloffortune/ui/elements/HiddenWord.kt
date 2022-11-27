package com.example.wheeloffortune.ui.elements

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

//Composable for the hidden word to be displayed.
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
                //If a letter in the hidden word is also in the string of correctGuesses
                //the letter will be visible (alpha 1F) in the appropriate box.
                if (char in correctGuesses) {
                    Text(
                        text = char.toString(),
                        color = Color.Black,
                        modifier = Modifier
                            .alpha(1F),
                        style = TextStyle(
                            fontSize = 36.sp
                        )
                    )
                } else {
                    //Otherwise the letter will be invisible (alpha 0F) in the appropriate box.
                    Text(
                        text = char.toString(),
                        color = Color.Black,
                        modifier = Modifier
                            .alpha(0F),
                        style = TextStyle(
                            fontSize = 36.sp
                        )
                    )
                }
            }
        }
    }
}