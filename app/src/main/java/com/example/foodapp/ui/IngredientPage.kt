@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.foodapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.foodapp.FoodViewModel
import com.example.foodapp.R
import com.example.foodapp.ui.common.MenuButton
import com.example.foodapp.ui.theme.FoodAppTheme



@Composable
fun IngredientPageMenu(
    modifier : Modifier = Modifier
) {
    Box(modifier = modifier){
        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .align(Alignment.Center)
                .height(256.dp)
        ) {
            MenuButton(text = stringResource(R.string.add_button))
            MenuButton(text = "Lista")
            /* TODO add navigation to buttons */
        }

    }
}

@Composable
fun IngredientPageAdd(
    modifier : Modifier = Modifier,
    foodViewModel : FoodViewModel = viewModel()
){
    val uiState by foodViewModel.uiState.collectAsState()
    Box(modifier = modifier){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.align(Alignment.Center).padding(24.dp)
        ) {

            Text(text = stringResource(R.string.ingredient_add_title))

            OutlinedTextField(
                value = uiState.ingredientName,
                onValueChange = { foodViewModel.onIngredientNameChange(it) },
                label = { Text(stringResource(R.string.name_label)) },
                keyboardOptions = KeyboardOptions(),
                singleLine = true
            )

            TextField(
                value = uiState.ingredientDescription,
                onValueChange = { foodViewModel.onIngredientDescriptionChange(it) },
                label = { stringResource(R.string.ingredient_description) },
                keyboardOptions = KeyboardOptions(),
                modifier = Modifier.height(400.dp).fillMaxWidth().padding(8.dp, 12.dp)
            )
            MenuButton(text = stringResource(R.string.add_button), onClick = {  })
        }
    }
}

@Composable
fun IngredientPageList(
    modifier : Modifier = Modifier
) {

}


@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun IngredientPageMenuPreview(){
    FoodAppTheme{
        IngredientPageMenu(modifier = Modifier.fillMaxSize())
    }
}


@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun IngredientPageAddPreview(){
    FoodAppTheme{
        IngredientPageAdd(modifier = Modifier.fillMaxSize())
    }
}


@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun IngredientPageListPreview(){
    FoodAppTheme{
        IngredientPageList(modifier = Modifier)
    }
}