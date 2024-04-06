package com.example.foodapp.model

data class FoodUiState(
    /* variables for recipe search */
    var nameSearch : String = "",
    var timeSearch : String = "",
    var timeSearchActive : Boolean = false,
    var ingredientSearchActive : Boolean = true,
    var searchIngredientList : MutableSet<Ingrediente> = mutableSetOf<Ingrediente>(),
    /* variables for ingredient pages (add, list) */
    var ingredientName : String = "",
    var ingredientDescription : String = "",
    var recipeName : String = "",
    var recipeDescription : String = "",
    var recipeIngredientList : MutableSet<Ingrediente> = mutableSetOf<Ingrediente>(),
    var recipeTime : String = ""
)
