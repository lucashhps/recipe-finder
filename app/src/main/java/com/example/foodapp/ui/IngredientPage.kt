@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.foodapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.foodapp.FoodViewModel
import com.example.foodapp.R
import com.example.foodapp.ui.common.IngredientListElement
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
            MenuButton(text = stringResource(R.string.list_button))
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
        Text(
            text = stringResource(R.string.ingredient_add_title),
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 24.dp)
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(24.dp)
        ) {

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
                modifier = Modifier
                    .height(400.dp)
                    .fillMaxWidth()
                    .padding(8.dp, 12.dp)
            )
            MenuButton(text = stringResource(R.string.add_button), onClick = { foodViewModel.onAddIngredient() })
        }
    }
}

@Composable
fun IngredientPageList(
    modifier : Modifier = Modifier,
    foodViewModel : FoodViewModel = viewModel(),
    addButton : Boolean = false
) {

    Box(
        modifier = modifier
    ) {
        Text(
            text = "Lista de ingredientes",
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 24.dp)
        )

        LazyColumn(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .width(256.dp)
                .padding(top = 64.dp)
        ) {
            items(foodViewModel.ingredientList) {
                    ingredient ->
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth().padding(bottom = 4.dp)
                ) {
                    IngredientListElement(ingredient)
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.width(68.dp)
                    ){
                        if(addButton){
                            Button(
                                onClick = { /*TODO make edit callback */ },
                                contentPadding = PaddingValues(8.dp),
                                modifier = Modifier.size(32.dp)
                            ) { // button to edit
                                Icon(
                                    painter = painterResource(R.drawable.baseline_add_24),
                                    contentDescription = "edit button"
                                )
                            }
                        }
                        Button(
                            onClick = { /*TODO make edit callback */ },
                            contentPadding = PaddingValues(8.dp),
                            modifier = Modifier.size(32.dp)
                        ) { // button to edit
                            Icon(
                                painter = painterResource(R.drawable.baseline_border_color_24),
                                contentDescription = "edit button"
                            )
                        }
                        Button(
                            onClick = { /*TODO make delete callback */ },
                            contentPadding = PaddingValues(8.dp),
                            modifier = Modifier.size(32.dp)
                        ) {// button to erase
                            Icon(
                                painter = painterResource(R.drawable.baseline_delete_forever_24),
                                contentDescription = "delete button"
                            )
                        }
                    }

                }

            }
        }
    }

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
        IngredientPageList(modifier = Modifier.fillMaxSize(), foodViewModel = FoodViewModel())
    }
}