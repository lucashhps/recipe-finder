package com.example.foodapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.foodapp.R
import com.example.foodapp.ui.common.MenuButton
import com.example.foodapp.ui.theme.FoodAppTheme

@Composable
fun MainMenuScreen(
    modifier : Modifier = Modifier
) {
    MainMenu(modifier = modifier)
}

@Composable
fun MainMenu(
    modifier : Modifier = Modifier
) {
    Box(modifier = modifier) {
        Column (
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.align(Alignment.Center).height(512.dp)
        ) {
            MenuButton(stringResource(R.string.menu_button_1))
            MenuButton(stringResource(R.string.menu_button_2))
            MenuButton(stringResource(R.string.menu_button_3))
            MenuButton(stringResource(R.string.menu_button_4))
        }
    }

}



@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun MainMenuPreview() {
    FoodAppTheme {
        MainMenuScreen(modifier = Modifier.fillMaxSize())
    }
}