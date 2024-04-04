package com.example.foodapp.data

import com.example.foodapp.model.Ingrediente
import com.example.foodapp.model.Receita

object FoodDataSource {
    // hard coded lists for test
    val ingredientList = listOf(
        Ingrediente("Manteiga", "gordura do leite"),
        Ingrediente("Alho", "arde os olhos mas é bom"),
        Ingrediente("Cebolinha", "perfeito pra por em miojo"),
        Ingrediente("Macarrao", "MASSA"),
        Ingrediente("sal", "iodo"),
        Ingrediente("açucar", "doce")
    )

    val recipeList = listOf(
        Receita("Receita 1", "1", listOf(ingredientList[0], ingredientList[1]), 1234),
        Receita("Receita 2", "2", listOf(ingredientList[0], ingredientList[1], ingredientList[2]), 234000000),
        Receita("Receita 3", "3", listOf(ingredientList[1], ingredientList[2]), 456461143),
        Receita("Receita 4", "4", listOf(ingredientList[4]), 587684),
        Receita("Receita 5", "5", listOf(ingredientList[4], ingredientList[5], ingredientList[1], ingredientList[0]), 100000)
    )


}