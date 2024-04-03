package com.example.foodapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import com.example.foodapp.FoodViewModel
import com.example.foodapp.R
import com.example.foodapp.model.Ingrediente
import com.example.foodapp.ui.common.IngredientBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeSearchPage(
    foodViewModel: FoodViewModel
) { // WIP
    val uiState by foodViewModel.uiState.collectAsState()
    val ingredientList = foodViewModel.ingredientList
    var nameSearch = uiState.nameSearch
    var timeSearch = uiState.timeSearch
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        // Page Name

        Text("Buscar Receita")

        // Name Filter

        OutlinedTextField(
            value = nameSearch,
            onValueChange = { foodViewModel.updateSearchParams(nameSearch = it) },
            label = { Text(stringResource(R.string.name_label)) },
            keyboardOptions = KeyboardOptions(),
            singleLine = true
        )

        // Time Filter

        Row(
            modifier = Modifier
        ) {
            Text(
                text = "Até"
            )
            TextField(
                value = timeSearch,
                onValueChange = { foodViewModel.updateSearchParams(timeSearch = it) },
                label = { stringResource(R.string.time_label) },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number)
            )

            Checkbox(checked = false, onCheckedChange = { })
        }

        // Ingredient Filter

        Column(
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {
            Text(
                text = "Lista de ingredientes"
            )
            for(ingredient in ingredientList){
                IngredientBar(ingredient = ingredient)
            }
        }

        // Search Button

        Button(
            onClick = { }
        ) {
            Text(
                text = stringResource(R.string.search)
            )
        }
    }
}