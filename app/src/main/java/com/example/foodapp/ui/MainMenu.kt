package com.example.foodapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.foodapp.R

@Composable
fun MainMenu() {
    Column (
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        MenuButton(stringResource(R.string.menu_button_1))
        MenuButton(stringResource(R.string.menu_button_2))
        MenuButton(stringResource(R.string.menu_button_3))
        MenuButton(stringResource(R.string.menu_button_4))
    }
}

@Composable
fun MenuButton(text : String){
    Button(
        onClick = {  }
    ) {
        Text(
            text = text
        )
    }
}