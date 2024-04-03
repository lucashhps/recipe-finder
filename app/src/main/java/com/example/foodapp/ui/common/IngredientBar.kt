package com.example.foodapp.ui.common

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.foodapp.model.Ingrediente


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