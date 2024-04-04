package com.example.foodapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.foodapp.ui.IngredientPageAdd
import com.example.foodapp.ui.RecipeSearchPage
import com.example.foodapp.ui.theme.FoodAppTheme
import java.util.Locale


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FoodAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    FoodApp(FoodViewModel())
                }
            }
        }
    }
}

// App main

@Composable
fun FoodApp(
    foodViewModel : FoodViewModel
){
    IngredientPageAdd(foodViewModel = foodViewModel, modifier = Modifier)
    //RecipeSearchPage(foodViewModel = foodViewModel, modifier = Modifier.fillMaxSize().padding(24.dp))
}

// Utility

fun String.capitalized() : String{
    return this.replaceFirstChar {
        if(it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
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