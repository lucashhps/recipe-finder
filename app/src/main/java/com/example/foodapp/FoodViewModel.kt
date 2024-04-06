package com.example.foodapp

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.foodapp.model.FoodUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.example.foodapp.data.FoodDataSource
import com.example.foodapp.model.Ingrediente
import com.example.foodapp.model.ReceitaBusca
import kotlinx.coroutines.flow.update

const val TAG = "FoodViewModel"

class FoodViewModel : ViewModel() {

    /* TODO make setters to the ui variables to detect invalid input and not allow it */

    private var _uiState = MutableStateFlow(FoodUiState())

    val uiState : StateFlow<FoodUiState>
        get() = _uiState.asStateFlow()

    val ingredientList = FoodDataSource.ingredientList
    val recipeList = FoodDataSource.recipeList

    // General UI search params update function
    fun updateSearchParams(nameSearch : String = _uiState.value.nameSearch, timeSearch : String = _uiState.value.timeSearch, timeSearchActive : Boolean = _uiState.value.timeSearchActive, ingredientSearchActive : Boolean = _uiState.value.ingredientSearchActive, searchIngredientList : MutableSet<Ingrediente> = _uiState.value.searchIngredientList){
        _uiState.update { currentState ->
            currentState.copy(nameSearch = nameSearch, timeSearch = timeSearch, timeSearchActive = timeSearchActive, ingredientSearchActive = ingredientSearchActive, searchIngredientList = searchIngredientList)
        }
    }

    // Callbacks

        // Ingredient Pages Callbacks
    fun onIngredientNameChange(ingredientName : String){
        _uiState.update {
            currentState ->
            currentState.copy(ingredientName = ingredientName)
        }
    }

    fun onIngredientDescriptionChange(ingredientDescription : String){
        _uiState.update {
                currentState ->
            currentState.copy(ingredientDescription = ingredientDescription)
        }
    }

    fun onAddIngredient(){
        /* TODO ADD DATABASE */
    }

        // Recipe Add Page Callbacks
    fun onRecipeNameChange(recipeName : String){
            _uiState.update {
                    currentState ->
                currentState.copy(recipeName = recipeName)
            }
        }

    fun onRecipeDescriptionChange(recipeDescription : String){
        _uiState.update {
                currentState ->
            currentState.copy(recipeDescription = recipeDescription)
        }
    }

    fun onRecipeTimeChange(recipeTime : String){
        _uiState.update {
                currentState ->
            currentState.copy(recipeTime = recipeTime)
        }
    }

    fun onAddRecipe(){
        /* TODO ADD DATABASE */
    }
        // Recipe Search Page Callbacks
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