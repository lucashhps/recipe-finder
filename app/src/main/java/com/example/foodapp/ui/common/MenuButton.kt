package com.example.foodapp.ui.common

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MenuButton(text : String, onClick : () -> Unit = { }){
    Button(
        onClick = onClick,
        modifier = Modifier.height(75.dp).width(200.dp)
    ) {
        Text(
            text = text
        )
    }
}