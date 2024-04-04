package com.example.foodapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.foodapp.FoodViewModel
import com.example.foodapp.R
import com.example.foodapp.model.Ingrediente
import com.example.foodapp.model.ReceitaBusca
import com.example.foodapp.ui.common.IngredientBar
import com.example.foodapp.ui.theme.FoodAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeSearchPage(
    foodViewModel: FoodViewModel,
    modifier : Modifier = Modifier
) { // WIP
    var searchRecipe : ReceitaBusca
    val uiState by foodViewModel.uiState.collectAsState()
    val ingredientList = foodViewModel.ingredientList
    var nameSearch = uiState.nameSearch
    var timeSearch = uiState.timeSearch
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        // Page Name

        Text(stringResource(R.string.menu_button_1))

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
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.width(224.dp)
        ) {
            Text(text = "Buscar por tempo")
            Checkbox(checked = uiState.timeSearchActive, onCheckedChange = { foodViewModel.onTimeCheckedChange(it) })

        }



        /* TODO put options to choose between minutes, seconds, hours, or choose hour to start and finish */
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.width(224.dp)
        ) {
            Text(
                text = "Até"
            )
            TextField(
                value = timeSearch,
                onValueChange = { foodViewModel.updateSearchParams(timeSearch = it) },
                label = { stringResource(R.string.time_label) },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number),
                modifier = Modifier.padding(start = 8.dp)
            )

        }

        // Ingredient Filter

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.width(224.dp)
        ) {
            Text(
                text = "Buscar por ingredientes"
            )
            Checkbox(checked = uiState.ingredientSearchActive, onCheckedChange = { foodViewModel.onIngredientCheckedChange(it) })
        }

        LazyColumn {

            items(ingredientList) { ingredient ->
                IngredientBar(ingredient = ingredient, foodViewModel, uiState, modifier = Modifier.width(200.dp))
            }
        }

        // Search Button

        /* TODO implement navigation on search */
        Button(
            onClick = { foodViewModel.onSearch() }
        ) {
            Text(
                text = stringResource(R.string.search)
            )
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun RecipeSearchPagePreview(){
    FoodAppTheme {
        RecipeSearchPage(foodViewModel = FoodViewModel(), modifier = Modifier.fillMaxSize().padding(24.dp))
    }
}