package com.example.foodapp.model

class ReceitaBusca(name : String, descricao : String, ingredientes : List<Ingrediente>, tempo : Int) : Receita(name, descricao, ingredientes, tempo) {

    fun nameMatch(matchName : String) : Boolean  {
        return Regex(name.lowercase()).containsMatchIn(matchName.lowercase())
    }

    fun ingredientMatch(matchIngredients : List<Ingrediente>) : Boolean {
        for(ingredienteReceita in matchIngredients){
            for(ingredienteBusca in ingredientes){
                if(ingredienteBusca.name.equals(ingredienteReceita.name)){
                    break
                } else if(ingredientes.indexOf(ingredienteBusca) == (ingredientes.size - 1)){
                    return false
                    break
                }
            }

        }
        return true
    }
    /* TODO include a variety of ways to search with ingredients
    *  TODO has at least x(y, z...) ingredient(s), don't has x(y, z...) ingredient(s), has at least x(y, z...) ingredient(s) and nothing more (the only way it has currently),
    * */
    fun searchRecipe(receitas : List<Receita>, tempoOn : Boolean = false, ingredienteOn : Boolean = false) : List<Receita> { // falta adicionar função de tempo/ingredientes on/off
        var busca : ArrayList<Receita> = ArrayList<Receita>()
        for(receita in receitas){
            // Filtrar por nome
            if(nameMatch(receita.name)) {
                // Filtrar por tempo
                if((receita.tempo <= tempo) || !tempoOn){ // se tempoOn = true, a comparação define se a receita entra na busca ou não, se tempoOn = false, a receita sempre passa para a proxima fase de filtragem
                    // Filtrar por ingredientes
                    if(ingredienteOn){
                        if(ingredientMatch(receita.ingredientes)){
                            busca.add(receita)
                        }
                    } else{
                        busca.add(receita)
                    }
                }
            }
        }
        return busca
    }
}