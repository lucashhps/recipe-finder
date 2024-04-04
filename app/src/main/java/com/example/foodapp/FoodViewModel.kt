package com.example.foodapp

import android.util.Log
import com.example.foodapp.model.FoodUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.example.foodapp.data.FoodDataSource
import com.example.foodapp.model.Ingrediente
import com.example.foodapp.model.ReceitaBusca
import kotlinx.coroutines.flow.update

const val TAG = "FoodViewModel"

class FoodViewModel {

    private var _uiState = MutableStateFlow(FoodUiState())

    val uiState : StateFlow<FoodUiState>
        get() = _uiState.asStateFlow()

    val ingredientList = FoodDataSource.ingredientList
    val recipeList = FoodDataSource.recipeList

    // General UI update function
    fun updateSearchParams(nameSearch : String = _uiState.value.nameSearch, timeSearch : String = _uiState.value.timeSearch, timeSearchActive : Boolean = _uiState.value.timeSearchActive, ingredientSearchActive : Boolean = _uiState.value.ingredientSearchActive, searchIngredientList : MutableSet<Ingrediente> = _uiState.value.searchIngredientList){
        _uiState.update { currentState ->
            currentState.copy(nameSearch = nameSearch, timeSearch = timeSearch, timeSearchActive = timeSearchActive, ingredientSearchActive = ingredientSearchActive, searchIngredientList = searchIngredientList)
        }
    }

    // Callbacks
    fun onTimeCheckedChange(activeState : Boolean) {
        updateSearchParams(timeSearchActive = activeState)
    }

    fun onIngredientCheckedChange(activeState : Boolean) {
        updateSearchParams(ingredientSearchActive = activeState)
    }

    fun onIngredientListCheckedChange(ingredient : Ingrediente, activeState : Boolean) {
        // creates a copy of the old set so that it has a different pointer/reference value, thus updating the older reference to the set at the FoodUiState object causing a recomposition of the screen
        // cria uma cópia do set antigo pois o compose não nota a mudança em uma lista para atualizar a UI, só se atualizar a referência
        val searchIngredientList = _uiState.value.searchIngredientList.toMutableSet()

        if(activeState) {
            searchIngredientList.add(ingredient)

        } else {
            searchIngredientList.remove(ingredient)
        }
        updateSearchParams(searchIngredientList = searchIngredientList)
    }

    fun onSearch(){
        try {
            /* TODO make setters to the variables to detect invalid input */
            Log.d("FoodViewModel", "starting search...")
            // aprimorar dps, ex: implementar conversão de tempo de min para ms, etc
            val name = uiState.value.nameSearch
            val ingredientSearchList = uiState.value.searchIngredientList.toList()
            val time = if(uiState.value.timeSearch != "") uiState.value.timeSearch.toInt() else 0 // when timeSearch = "" it becomes 0
            Log.d(TAG, "variables created...")
            val recipeSearch = ReceitaBusca(name, "", ingredientSearchList, time)
            Log.d(TAG, "search object created...")
            recipeSearch.searchRecipe(recipeList, uiState.value.timeSearchActive, uiState.value.ingredientSearchActive)
                .forEach { receita ->
                Log.d(TAG, receita.name)}
        } catch(e : Exception) {
            Log.d(TAG, "error", e)
        }


    }
}