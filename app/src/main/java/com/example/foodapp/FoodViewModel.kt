package com.example.foodapp

import com.example.foodapp.model.FoodUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.example.foodapp.data.IngredientList
import kotlinx.coroutines.flow.update

class FoodViewModel {

    var _uiState = MutableStateFlow(FoodUiState())

    val uiState : StateFlow<FoodUiState>
        get() = _uiState.asStateFlow()

    val ingredientList = IngredientList.ingredientList

    // Update UI functions
    fun updateSearchParams(nameSearch : String = _uiState.value.nameSearch, timeSearch : String = _uiState.value.timeSearch){
        _uiState.update { currentState ->
            currentState.copy(nameSearch = nameSearch, timeSearch = timeSearch)
        }
    }
}