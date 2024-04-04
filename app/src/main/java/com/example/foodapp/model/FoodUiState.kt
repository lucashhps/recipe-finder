package com.example.foodapp.model

data class FoodUiState(
    var nameSearch : String = "",
    var timeSearch : String = "",
    var timeSearchActive : Boolean = false,
    var ingredientSearchActive : Boolean = true,
    var searchIngredientList : MutableSet<Ingrediente> = mutableSetOf<Ingrediente>()
)
