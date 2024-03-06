package com.example.foodapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.neverEqualPolicy
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.foodapp.ui.theme.FoodAppTheme

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
                    FoodApp()
                }
            }
        }
    }
}

// Classes

class PageIndex(var index : Int){
    fun changeIndex(newIndex : Int){
        index = newIndex
    }

    fun voltar(){
        index -= 1
    }

    fun desfazer(){
        index += 1
    }

    fun resetar(){
        index = 0
    }


}

class Ingrediente(var name : String, var descricao : String?) {
}

class Receita(var name : String, var descricao : String, var ingredientes : ArrayList<Ingrediente>?, var tempo : Int) {
}

// App main

@Composable
fun FoodApp(){
    var pageIndex by remember { mutableStateOf(0) }
    when(pageIndex) {
        0 -> MainMenu()
        1 -> BuscarReceitaPage()
        else -> MainMenu()
    }
    Text(text = pageIndex.toString())
}

// App pages

@Composable
fun MainMenu() {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
    ) {
        MenuButton("Buscar Receita")
        MenuButton("Receita")
        MenuButton("Ingrediente")
        MenuButton("Configurações")
    }

}

@Composable
fun BuscarReceitaPage() { // WIP
    Button( onClick = {  } ){
        Text(text = "voltar")
    }
    Text(text = "buscar receitas page")
}

@Composable
fun ReceitaPage() {

}

@Composable
fun IngredientePage() {

}

@Composable
fun ConfigPage() {

}

// Custom Components

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

// App Preview
@Preview(
    showBackground = true,
    showSystemUi = true
    )
@Composable
fun FoodAppPreview() {
    FoodAppTheme {
        FoodApp()
    }
}