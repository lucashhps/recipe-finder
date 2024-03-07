package com.example.foodapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.neverEqualPolicy
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
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

open class Receita(var name : String, var descricao : String, var ingredientes : ArrayList<Ingrediente>, var tempo : Int) {
}

class ReceitaBusca(name : String, descricao : String, ingredientes : ArrayList<Ingrediente>, tempo : Int) : Receita(name, descricao, ingredientes, tempo) {

    fun nameMatch(matchName : String) : Boolean  {
        return Regex(name.lowercase()).containsMatchIn(matchName.lowercase())
    }
    fun searchRecipe(receitas : ArrayList<Receita>, tempoOn : Boolean = false, ingredienteOn : Boolean = false) : ArrayList<Receita> { // falta adicionar função de tempo/ingredientes on/off
        var busca : ArrayList<Receita> = ArrayList<Receita>()
        for(receita in receitas){
            var temIngredientes : Boolean = true
            // Filtrar por nome
            if(nameMatch(receita.name)) {
                // Filtrar por tempo
                if((receita.tempo <= tempo) || !tempoOn){
                    // Filtrar por ingredientes
                    if(ingredienteOn){
                        for(ingredienteNecessario in receita.ingredientes){
                            if(!temIngredientes){
                                break
                            }
                            for(ingrediente in ingredientes){
                                if(ingrediente.name.equals(ingredienteNecessario.name)){
                                    break
                                } else if(ingredientes.indexOf(ingrediente) == (ingredientes.size - 1)){
                                    temIngredientes = false
                                    break
                                }
                            }

                        }
                    }


                    if(temIngredientes){
                        busca.add(receita)
                    }
                }
            }
        }
        return busca
    }
}

// App main

@Composable
fun FoodApp(){
    var pageIndex by remember { mutableStateOf(1) }
    var ingredientList by remember { mutableStateOf(ArrayList<Ingrediente>()) }
    ingredientList.add(Ingrediente("Paprika", "Picante"))
    ingredientList.add(Ingrediente("Orégano", "Agridoce"))
    ingredientList.add(Ingrediente("Creme de leite", "Cremoso"))
    ingredientList.add(Ingrediente("Queijo", "Derivado de leite"))
    ingredientList.add(Ingrediente("Presunto", "humm porquinho"))
    ingredientList.add(Ingrediente("Sal", "Iodo"))
    ingredientList.add(Ingrediente("Açucar", "Doce"))
    ingredientList.add(Ingrediente("Pimenta", "Picante"))

    when(pageIndex) {
        0 -> MainMenu()
        1 -> BuscarReceitaPage(ingredientList)
        else -> MainMenu()
    }
    Text(text = pageIndex.toString())
}

// App pages

@Composable
fun MainMenu() {
    Column (
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        MenuButton("Buscar Receita")
        MenuButton("Receita")
        MenuButton("Ingrediente")
        MenuButton("Configurações")
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BuscarReceitaPage(ingredientList : ArrayList<Ingrediente>) { // WIP
    var nameSearch by remember { mutableStateOf("") }
    var timeSearch by remember { mutableStateOf("") }
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
            onValueChange = { nameSearch = it },
            label = { Text("Nome") },
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
                onValueChange = { timeSearch = it },
                label = { "Time" },
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
                text = "Buscar"
            )
        }
    }
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
fun IngredientBar(ingredient : Ingrediente) {
    Row(
        modifier = Modifier
    ) {
        Text(
            text = ingredient.name
        )
        Checkbox(checked = false, onCheckedChange = { })

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
        FoodApp()
    }
}