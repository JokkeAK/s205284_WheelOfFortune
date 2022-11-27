package com.example.wheeloffortune.ui.elements

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
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

//Composable for the category of the hidden word.
@Composable
fun HiddenWordCategory(
    currentCategoryForHiddenWord: String,
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .size(200.dp, 75.dp),
        contentAlignment = Alignment.Center
    ) {

        Column(
            modifier = Modifier
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Category",
                fontSize = 24.sp,
                fontWeight = FontWeight.ExtraBold,
                style = TextStyle(textDecoration = TextDecoration.Underline),
            )

            Text(
                text = currentCategoryForHiddenWord,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
            )
        }

    }
}