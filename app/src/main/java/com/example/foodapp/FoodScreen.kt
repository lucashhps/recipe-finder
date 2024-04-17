package com.example.foodapp

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.foodapp.ui.theme.FoodAppTheme
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.foodapp.ui.IngredientPageAdd
import com.example.foodapp.ui.IngredientPageList
import com.example.foodapp.ui.IngredientPageMenu
import com.example.foodapp.ui.MainMenuScreen
import com.example.foodapp.ui.RecipePageAdd
import com.example.foodapp.ui.RecipePageList
import com.example.foodapp.ui.RecipePageMenu
import com.example.foodapp.ui.RecipeSearchPage

// App screens

enum class FoodScreens{
    Menu, Search, IngredientMenu,
    RecipeMenu, SearchResults, IngredientAdd,
    IngredientList, RecipeAdd, RecipeList,
    Configuration
}


// App main

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodApp(
    foodViewModel : FoodViewModel,
    navController : NavHostController = rememberNavController()
){
    Scaffold {
        innerPadding ->
        NavHost(
            navController = navController,
            startDestination = FoodScreens.Menu.name,
            modifier = Modifier.padding(innerPadding)
        ){

            composable(
                route = FoodScreens.Menu.name
            ) {
                MainMenuScreen(
                    modifier = Modifier.fillMaxSize(),
                    navController = navController
                )
            }
            
            composable(
                route = FoodScreens.Search.name
            ) {
                RecipeSearchPage(
                    foodViewModel = foodViewModel,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp)
                )
            }

            /*
            composable(
                route = FoodScreens.SearchResults.name
            ) {
                /*TODO make config page*/
            }*/

            try {
                composable(
                    route = FoodScreens.IngredientMenu.name
                ) {

                    IngredientPageMenu(
                        modifier = Modifier.fillMaxSize(),
                        navController = navController
                    )
                }
            } catch (e : Exception) {
                Log.d("FoodScreen", "error", e)
            }


            composable(
                route = FoodScreens.IngredientAdd.name
            ) {
                IngredientPageAdd(
                    modifier = Modifier.fillMaxSize(),
                    foodViewModel = foodViewModel
                )
            }

            composable(
                route = FoodScreens.IngredientList.name
            ) {
                IngredientPageList(
                    modifier = Modifier.fillMaxSize(),
                    foodViewModel = FoodViewModel()
                )
            }

            composable(
                route = FoodScreens.RecipeMenu.name
            ) {
                RecipePageMenu(
                    modifier = Modifier.fillMaxSize(),
                    navController = navController
                )
            }

            composable(
                route = FoodScreens.RecipeAdd.name
            ) {
                RecipePageAdd(
                    modifier = Modifier.fillMaxSize(),
                    foodViewModel = FoodViewModel()
                )
            }

            composable(
                route = FoodScreens.RecipeList.name
            ) {
                RecipePageList(
                    modifier = Modifier.fillMaxSize(),
                    foodViewModel = FoodViewModel()
                )
            }

            composable(
                route = FoodScreens.Configuration.name
            ) {
                /*TODO make config page*/
            }

        }
    }
}

// App Preview
@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun FoodAppPreview() {
    FoodAppTheme {
        FoodApp(FoodViewModel())
    }
}