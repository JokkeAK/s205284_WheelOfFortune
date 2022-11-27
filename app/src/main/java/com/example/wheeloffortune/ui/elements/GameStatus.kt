package com.example.wheeloffortune.ui.elements

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

//Composable that shows the user's life and score.
@Composable
fun GameStatus(life: Int, score: Int) {

    Box(
        modifier = Modifier
            .size(200.dp, 50.dp),
        contentAlignment = Alignment.Center
    ) {

        Column(
            modifier = Modifier
                .align(Alignment.TopStart),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Lives",
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold,
                style = TextStyle(textDecoration = TextDecoration.Underline),

                )
            Text(
                text = "" + life,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
            )
        }

        Column(
            modifier = Modifier
                .align(Alignment.TopEnd),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Points",
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold,
                style = TextStyle(textDecoration = TextDecoration.Underline),

                )
            Text(
                text = "" + score,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}

